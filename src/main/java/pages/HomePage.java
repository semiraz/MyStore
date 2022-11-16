package pages;

import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends PageObject {

    @FindBy(id = "search")
    private WebElement searchBar;

    @FindBy(css = "button[title='Search']")
    private WebElement submitSearch;

    @FindBy(css = ".logo")
    private WebElement logo;

    @FindBy(css = ".blocks-promo")
    private WebElement promoBlock;

//    @FindBy(css = ".block-promo-wrapper a")
//    private List<WebElement> promoLinks;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToHomepage() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public boolean verifyTitle(String title) {
        return driver.getTitle().contains(title);
    }

    public boolean isLogoPresent() {
        return logo.isDisplayed();
    }

//    public void findLinks() throws InterruptedException {
//        int size = promoBlock.findElements(By.tagName("a")).size();
//        List<WebElement> links = promoBlock.findElements(By.tagName("a"));
//        System.out.println(size);
//        for(WebElement link : links) {
//            String clickOnLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
//            link.sendKeys(clickOnLink);
//        }
//        Thread.sleep(2000);
//        String title;
//        Set<String> windows = driver.getWindowHandles();
//        Iterator<String> it = windows.iterator();;
//        while (it.hasNext()) {
//            driver.switchTo().window(it.next());
//            System.out.println(driver.getTitle());
//            title = driver.getTitle();
//        }
//    }

    public void enterSearchedWord(String productName) {
        searchBar.sendKeys(productName, Keys.ENTER);
    }
}