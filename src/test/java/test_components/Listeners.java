package test_components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.Utilities;

import java.io.File;
import java.io.IOException;

public class Listeners extends Utilities implements ITestListener {
    ExtentReports extent = ExtentReport.getReportObject();
    ExtentTest test;
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       test.log(Status.PASS, "Test is successfully passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        String testcaseName = result.getMethod().getMethodName();

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        //attach screenshot to report
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String pathName = System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
        File destinationFile = new File(pathName);
        try {
            FileUtils.copyFile(srcFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        test.addScreenCaptureFromPath(pathName, testcaseName);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
