package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends PageObject {
    public SuccessPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement thankYouNote;

    @FindBy(css = "div[class='checkout-success'] p span")
    private WebElement orderId;

    @FindBy(css = "a[class='action primary'] span")
    private WebElement creteAnAccountBtn;

    public boolean verifySuccessOrder(String thankYouText) {
        return thankYouNote.getText().equalsIgnoreCase(thankYouText);
    }

    public int getOrderId() {
        return Integer.parseInt(orderId.getText());
    }

    public void goToCreateAnAccount() {
        creteAnAccountBtn.click();
    }

}