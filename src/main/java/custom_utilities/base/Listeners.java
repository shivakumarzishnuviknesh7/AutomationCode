package custom_utilities.base;

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

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReportNg.getReportObject();
    ExtentTest test;
    WebDriver driver;

    public String getScreenshot(String testCaseName , WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//ScreenShots_for_failure_cases//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//ScreenShots_for_failure_cases//" + testCaseName + ".png";
    }

    @Override
    public void onTestStart(ITestResult result) {
        //before running any test the code control will reach this block
        //creation of extent report will be initiated here
        test = extent.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //if any test runs success the code control will immediately come here
        test.log(Status.PASS,"success");

        System.out.println(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //if any test fails the code control will immediately come here
        //here we write screen shoot code
        //when test fail with help of throwable we can see the error message in the status report
        //Screenshot

        System.out.println(result.getName());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        test.fail(result.getThrowable());
        String filepaths = null;

        try {
            filepaths = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filepaths,result.getMethod().getMethodName() );

        //test.log(Status.FAIL,"failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        //once entire execution is done the code control will reach this block
        extent.flush();
    }


}

