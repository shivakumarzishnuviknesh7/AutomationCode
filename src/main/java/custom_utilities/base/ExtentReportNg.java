package custom_utilities.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNg {
    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"\\Practice_Report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("result");
        reporter.config().setDocumentTitle("Practice_Report");

        reporter.config().setTheme(Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Zishnu Viknesh");
        //extent.createTest(path);
        return extent;

    }
}
