package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewAccountPage extends PageObject {

    public CreateNewAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPassword;

    @FindBy(css = "button[title='Create an Account']")
    private WebElement submitBtn;

    public CustomerAccountPage createAnAccount(String password) {
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
        submitBtn.click();
        return new CustomerAccountPage(driver);
    }

}
