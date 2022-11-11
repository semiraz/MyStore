package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageObject {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement searchedTitle;

    @FindBy(xpath = "//img[@alt='Josie Yoga Jacket']")
    private WebElement productImage;

    @FindBy(css = ".ajax_add_to_cart_button")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public boolean verifySearchedText(String productName) {
         String searchedText = searchedTitle.getText().split(":")[1].trim();
         return searchedText.equalsIgnoreCase("'" + productName + "'");
    }

    public void clickOnProductImg() {
        productImage.click();
    }
}