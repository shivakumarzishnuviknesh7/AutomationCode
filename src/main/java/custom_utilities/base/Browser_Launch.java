package custom_utilities.base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.fail;

public class Browser_Launch {

    public Properties prop;
    public Actions action;
    public WebDriver driver;


    @BeforeClass
    public void browser_open() throws MalformedURLException {

        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "resources" + File.separator + "Id_Password.properties");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prop = new Properties();
        try {
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String Browser = System.getProperty("Browser")!=null ? System.getProperty("Browser") :prop.getProperty("Browser"); // From properties file, the Browser name will be fetched.


        if (Browser.equalsIgnoreCase("Chrome")) {


            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

            /*
             * Grid code to run
             */
			/*
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			DesiredCapabilities capabilities = new DesiredCapabilities();
		    options.merge(capabilities);
			String completeURL ="http://192.168.49.24:4444";
	        driver = new RemoteWebDriver(new URL(completeURL), options);
*/
        }
        else if (Browser.equalsIgnoreCase("Edge")) {

            //edge browser
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        else if (Browser.equalsIgnoreCase("Firefox")) {

            //firefox browser
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        else if (Browser.equalsIgnoreCase("headless")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        }
        else {
            fail("User mentioned browser is incorrect..... \nPlease choose Chrome or Firefox or Edge of Headless");
        }

    }

    @AfterClass
    public void Close_Window() {
        driver.quit();
    }

}
