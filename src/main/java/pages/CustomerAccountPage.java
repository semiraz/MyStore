package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerAccountPage extends PageObject {

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[data-bind*='(message.text)']")
    private WebElement thankYouNote;

    @FindBy(css = "div[class='box box-information'] p")
    private WebElement customerName;

    @FindBy(css = "td[class='col id']")
    private WebElement orderId;

    @FindBy(css = "span[class='price']")
    private WebElement totalPrice;

    @FindBy(css = "div[class='panel header'] button[type='button']")
    private WebElement customerNameButton;

    @FindBy(xpath = "(//a[normalize-space()='Sign Out'])[1]")
    private WebElement signOutBtn;

    public boolean verifyThankYouNote(String note) {
        return this.thankYouNote.getText().equalsIgnoreCase(note);
    }

    public boolean verifyOrder(int orderId, double orderTotal) {
        int orderInt = Integer.parseInt(this.orderId.getText());
        double orderTotalDbl = Double.parseDouble(this.totalPrice.getText().split("\\$")[1]);
        return orderInt == orderId && orderTotalDbl == orderTotal;
    }

    public void signOut() {
        customerNameButton.click();
        signOutBtn.click();
    }
}