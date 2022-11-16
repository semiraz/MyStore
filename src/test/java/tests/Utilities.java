package tests;

import org.apache.commons.lang3.RandomStringUtils;

public class Utilities {

    protected static String title = "Home Page - Magento eCommerce";
    protected static String productName = "blue jacket";
    protected static String thankYouText = "Thank you for your purchase!";
    protected static String accountIsCreated = "Thank you for registering with Fake Online Clothing Store.";
    protected static String shippingMethod = "tablerate_bestway";
    protected static String quantity = "1";
    protected static String actualProductName;
    protected static double actualPrice;
    protected static int orderId;
    protected static String size = "M";
    protected static String color= "blue";
    protected static String filePath = "src/test/java/data/shippingFormDetails.json";

    protected static String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static String lower = "abcdefghijklmnopqrstuvwxyz";
    protected static String character = "!@#$%^&*-_=+|;:,<.>/?";
    protected static String number = "1234567890";
    protected static String emailAddress = randomEmail();
    protected static String password = randomPassword();

    public static String randomEmail() {
        return RandomStringUtils.random(10, lower) + "@gmail.com";
    }

    public static String randomPassword() {
        return RandomStringUtils.random(15, upper + character + lower + number);
    }

    public static String pageLoadFail(String value) {
        return "Failed to load page " + value;
    }

    public static String textError(String text) {
        return text + " is not correct";
    }
}