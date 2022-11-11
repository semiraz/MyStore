package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends PageObject {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".swatch-option.text")
    private List<WebElement> sizeBox;

    @FindBy(css = ".swatch-option.color")
    private List<WebElement> colorBox;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(css = ".action.action.showcart")
    private WebElement cartIcon;

    @FindBy(id = "top-cart-btn-checkout")
    private WebElement proceedToCheckoutBtn;

    @FindBy(css = ".loading-mask")
    private WebElement loadingCart;

    @FindBy(xpath = "//div[@class='details-qty qty']/input")
    private WebElement quantity;

    @FindBy(css = "*[data-ui-id='page-title-wrapper']")
    private WebElement actualProductName;

    @FindBy(css = "div[class='product-info-price'] span[class='price']")
    private WebElement actualTotalPrice;

    public void addToCart(String size, String color) {
        for (WebElement size1 : sizeBox) {
            if (size1.getText().equalsIgnoreCase(size)) {
                size1.click();
            }
        }
        for (WebElement color1 : colorBox) {
            if (color1.getAttribute("option-label").equalsIgnoreCase(color)) {
                color1.click();
            }
        }

        addToCartButton.click();
    }

    public boolean verifyAddButtonIsChangedToAdded() {
        waitForTextToBePresentInElement(addToCartButton, "Added");
        return addToCartButton.getText().equalsIgnoreCase("added");
    }

    public void openCartPopup() throws InterruptedException {
        waitForElementToDisappear(loadingCart);
        cartIcon.click();
    }

    public boolean verifyIsQtyCorrect(String qty) {
        return quantity.getAttribute("data-item-qty").equalsIgnoreCase(qty);
    }

    public void clickOnProceedToCheckout() {
        proceedToCheckoutBtn.click();
    }

    public String getActualProductName() {
        return actualProductName.getText();
    }

    public double getActualPrice() {
        return Double.parseDouble(actualTotalPrice.getText().split("\\$")[1]);
    }

}