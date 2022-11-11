import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected String lower = "abcdefghijklmnopqrstuvwxyz";

    public WebDriver initializeDriver() throws IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

    public String randomEmail() {
        return RandomStringUtils.random(10, upper + lower ) + "@gmail.com";
    }

    public String randomPassword() {
        String character = "!@#$%^&*-_=+|;:,<.>/?";
        return RandomStringUtils.random(15, upper + character+ lower);
    }

}