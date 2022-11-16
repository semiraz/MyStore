package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import test_components.DataReader;

import java.io.IOException;

import static tests.Utilities.*;

public class SmokeTest extends BaseTest {

    protected HomePage homePage;
    protected SearchPage searchPage;
    protected ProductPage productPage;
    protected ShippingPage shippingPage;
    protected PaymentPage paymentPage;
    protected CreateNewAccountPage createNewAccountPage;
    protected CustomerAccountPage customerAccountPage;
    protected SuccessPage successPage;

    @BeforeTest
    public void setUp() {
        driver = initializeDriver();

        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        shippingPage = new ShippingPage(driver);
        paymentPage = new PaymentPage(driver);
        createNewAccountPage = new CreateNewAccountPage(driver);
        customerAccountPage = new CustomerAccountPage(driver);
        successPage = new SuccessPage(driver);
    }

    @Test
    public void smokeTest() throws InterruptedException, IOException {

        //open homepage:
        homePage.goToHomepage();

        //verify title of the homepage:
        Assert.assertTrue(homePage.verifyTitle(Utilities.title), Utilities.textError("title"));
        Assert.assertTrue(homePage.isLogoPresent(), Utilities.pageLoadFail("Homepage"));

        homePage.enterSearchedWord(Utilities.productName);

        Assert.assertTrue(searchPage.verifySearchedText(Utilities.productName), Utilities.pageLoadFail("Search Page"));
        searchPage.clickOnProductImg();

        //add to cart the searched product and verify correct quantity
        actualProductName = productPage.getActualProductName();
        actualPrice = productPage.getActualPrice();

        productPage.addToCart(Utilities.size, Utilities.color);
        Assert.assertTrue(productPage.verifyAddButtonIsChangedToAdded(), Utilities.textError("button add to cart"));
        productPage.openCartPopup();
        Assert.assertTrue(productPage.verifyIsQtyCorrect(Utilities.quantity), Utilities.textError("quantity"));

        productPage.clickOnProceedToCheckout();

        //fill the shipping form with valid data
        shippingPage.enterEmail(emailAddress);
        Assert.assertTrue(shippingPage.isLoading(), Utilities.pageLoadFail("Shipping Page"));

        shippingPage.fillShippingAddressForm(DataReader.readShippingForm(filePath));

        //choose shipping method
        shippingPage.chooseShippingMethods(Utilities.shippingMethod);

        //verify if user ordered correct product
        shippingPage.submit();
        Thread.sleep(4000);
        Assert.assertTrue(paymentPage.verifyCorrectProductAndPriceIsOrdered(actualPrice, actualProductName), Utilities.textError("price or product name"));

        paymentPage.submitTheOrder();
        Thread.sleep(3000);

        Assert.assertTrue(successPage.verifySuccessOrder(Utilities.thankYouText), Utilities.textError("success message"));
        orderId = successPage.getOrderId();

        //create a new account to be able to verify order details
        successPage.goToCreateAnAccount();

        createNewAccountPage.createAnAccount(password);
        Assert.assertTrue(customerAccountPage.verifyThankYouNote(Utilities.accountIsCreated), Utilities.pageLoadFail("Customer Account Page"));
        Assert.assertTrue(customerAccountPage.verifyOrder(orderId, actualPrice), Utilities.textError("order id and total price"));

        //sign out
        customerAccountPage.signOut();
    }

    @AfterTest
    public void quitDriver() throws IOException {
        driver.quit();
    }
}