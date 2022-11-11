package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ShippingPage extends PageObject {
    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customer-email")
    private WebElement emailAddress;

    @FindBy(xpath = "//div[@class='control _with-tooltip']//input[@id='customer-email']")
    private WebElement loadingEmail;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement city;
    @FindBy(xpath = "//*[@name='street[0]']")
    private WebElement streetAddress;

    @FindBy(xpath = "//select[@name='region_id']")
    private WebElement state;


    @FindBy(xpath = "//*[@name='postcode']")
    private WebElement zipCode;

    @FindBy(xpath = "//*[@name='country_id']")
    private WebElement country;

    @FindBy(xpath = "//*[@name='telephone']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//tbody/tr/td[1]/input")
    private List<WebElement> shippingMethods;

    @FindBy(css = ".button.action.button.action.continue.primary")
    private WebElement submitBtn;

    public void enterEmail(String email) {
        waitForWebElementToAppear(emailAddress);
        emailAddress.sendKeys(email, Keys.ENTER);
    }
    public boolean isLoading() {
        waitForWebElementToAppear(loadingEmail);
        return loadingEmail.isDisplayed();
    }

public void fillShippingAddressForm(ShippingForm shippingForm) {

    this.firstName.sendKeys(shippingForm.getFirstName());
    this.lastName.sendKeys(shippingForm.getLastName());
    this.streetAddress.sendKeys(shippingForm.getAddress());
    this.city.sendKeys(shippingForm.getCity());
    this.state.sendKeys(shippingForm.getState(), Keys.ENTER);
    this.zipCode.sendKeys(shippingForm.getZipCode());
    this.phoneNumber.sendKeys(shippingForm.getPhoneNumber());
}

    public void chooseShippingMethods(String method) {
        Objects.requireNonNull(shippingMethods.stream().filter(s -> s.getAttribute("value").equalsIgnoreCase(method))
                .findFirst()
                .orElse(null)).click();
    }

    public PaymentPage submit() {
        submitBtn.click();
        return new PaymentPage(driver);
    }
}