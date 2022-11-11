package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(id = "search")
    private WebElement searchBar;

    @FindBy(css = "button[title='Search']")
    private WebElement submitSearch;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToHomepage() {
        driver.get("https://magento.softwaretestingboard.com/");
    }
    
    public void enterSearchedWord(String productName) {
        searchBar.sendKeys(productName, Keys.ENTER);
    }
}