package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageObject {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//strong//span[@class='price']")
    private WebElement totalPrice;

    @FindBy(xpath = "//span[text()='Item in Cart']")
    private WebElement showMiniCartBtn;

    @FindBy(css = ".product-item-name")
    private WebElement itemName;

    @FindBy(css = ".action.primary.checkout")
    private WebElement checkoutBtn;

    @FindBy(css = ".opc-block-summary")
    private WebElement summaryBlock;

    @FindBy(id = "billing-address-same-as-shipping-checkmo")
    private WebElement checkAddressBtn;

    public boolean verifyCorrectProductAndPriceIsOrdered (double price, String productName) {
        showMiniCartBtn.click();
        waitForWebElementToAppear(itemName);

        String priceString = totalPrice.getText().split("\\$")[1];
        double priceD = Double.parseDouble(priceString);
        return (priceD == price) && (itemName.getText().equalsIgnoreCase(productName));
    }

    public SuccessPage submitTheOrder() {
        if (!checkAddressBtn.isSelected()) {
            checkAddressBtn.click();
            checkoutBtn.click();
        } else {
            checkoutBtn.click();

        }
        return new SuccessPage(driver);
    }
}