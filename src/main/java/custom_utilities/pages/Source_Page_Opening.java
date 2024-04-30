package custom_utilities.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Source_Page_Opening {
    public WebDriver driver;



    public Source_Page_Opening(WebDriver driver) {
        this.driver = driver;
    }

    public void page_Opening(){
        /*
        * page opening in web and validation
        */

        driver.get("https://the-internet.herokuapp.com/?ref=hackernoon.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement heading = driver.findElement(By.xpath("//h1[text()='Welcome to the-internet']"));
        wait.until(ExpectedConditions.visibilityOf(heading));
        String text_taken = heading.getText();

        Assert.assertEquals("Welcome to the-internet",text_taken,"web link not working");
    }

    public void click(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement heading = driver.findElement(By.xpath("//ul//li//a[text()='A/B Testing']"));
        wait.until(ExpectedConditions.visibilityOf(heading));
        heading.click();

        driver.navigate().back();
    }

    public void mouse_click(){
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement heading = driver.findElement(By.xpath("//ul//li//a[text()='Add/Remove Elements']"));
        wait.until(ExpectedConditions.elementToBeClickable(heading));
        action.doubleClick(heading).perform();

        WebElement add_element_click = driver.findElement(By.xpath("//div[@class='example']//button[text()='Add Element']"));
        wait.until(ExpectedConditions.elementToBeClickable(add_element_click));
        action.doubleClick(add_element_click).build().perform();

        List <WebElement> webElements = driver.findElements(By.xpath("//div[@class='example']//button[text()='Delete']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        Integer before = webElements.size();

        WebElement delete_element_click = driver.findElement(By.xpath("//div[@class='example']//button[text()='Delete']"));
        wait.until(ExpectedConditions.elementToBeClickable(delete_element_click));
        action.doubleClick(delete_element_click).build().perform();

        List <WebElement> webElementsafter = driver.findElements(By.xpath("//div[@class='example']//button[text()='Delete']"));
        Integer after = webElementsafter.size();

        Assert.assertNotEquals(before, after, "test failed - because of feature issue");

        driver.navigate().back();

    }




}
