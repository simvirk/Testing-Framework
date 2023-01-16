package framework.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumWaitTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        //driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testName() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"start\"]/button")).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"finish\"]/h4")));
   // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"finish\"]/h4")));

        String text = element.getText();
        Assert.assertEquals(text, "Hello World!");

    }

    @Test
    public void testName2() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button")).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"message\"]")));
        String text = element.getText();
        Assert.assertEquals(text, "It's gone!");

        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(element1));

        element1.sendKeys("Learning Selenium");

        WebElement element3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"message\"]")));
        String text1 = element3.getText();
       Assert.assertEquals(text1,"It's enabled!" );

       driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).click();



    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}

