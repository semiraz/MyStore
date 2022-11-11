import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class SmokeTest extends BaseTest{

    private String emailAddress = randomEmail();

    private String password = randomPassword();
    private String actualProductName;
    private double actualPrice;
    private int orderId;
    private String productName = "blue jacket";
    private String thankYouText = "Thank you for your purchase!";
    private String accountIsCreated = "Thank you for registering with Fake Online Clothing Store.";

    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
    }

    @Test
    public void smokeTest() throws InterruptedException, IOException {

        //open homepage and enter searched word
        HomePage homePage = new HomePage(driver);
        homePage.goToHomepage();
        homePage.enterSearchedWord(productName);

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.verifySearchedText(productName));
        searchPage.clickOnProductImg();

        //add to cart the searched product and verify correct quantity
        ProductPage productPage = new ProductPage(driver);
        actualProductName = productPage.getActualProductName();
        actualPrice = productPage.getActualPrice();

        productPage.addToCart("M", "Blue");
        Assert.assertTrue(productPage.verifyAddButtonIsChangedToAdded());
        productPage.openCartPopup();
        Assert.assertTrue(productPage.verifyIsQtyCorrect("1"));

        productPage.clickOnProceedToCheckout();

        //fill the shipping form with valid data
        ShippingPage shippingPage = new ShippingPage(driver);
        shippingPage.enterEmail(emailAddress);
        Assert.assertTrue(shippingPage.isLoading());

//        shippingPage.fillShippingAddressForm(getDetailsMap("src/test/java/data/shippingFormDetails.json"));
        shippingPage.fillShippingAddressForm(DataReader.readShippingForm("src/test/java/data/shippingFormDetails.json"));

        //choose shipping method
        shippingPage.chooseShippingMethods("tablerate_bestway");

        //verify if user ordered correct product
        PaymentPage paymentPage = shippingPage.submit(); //shippingPage.submit();
        Thread.sleep(4000);
        Assert.assertTrue(paymentPage.verifyCorrectProductAndPriceIsOrdered(actualPrice, actualProductName));

        SuccessPage successPage = paymentPage.submitTheOrder();
        Thread.sleep(3000);

        Assert.assertTrue(successPage.verifySuccessOrder(thankYouText));
        orderId = successPage.getOrderId();

        //create new account to be able to verify order details
        CreateNewAccountPage createNewAccountPage = successPage.goToCreateAnAccount();

        CustomerAccountPage customerAccountPage = createNewAccountPage.createAnAccount(password);
        customerAccountPage.verifyThankYouNote(accountIsCreated);
        customerAccountPage.verifyOrder(orderId, actualPrice);

        //sign out
        customerAccountPage.signOut();
    }

    @AfterTest
    public void quitDriver() throws IOException {
        driver.quit();
    }
}
