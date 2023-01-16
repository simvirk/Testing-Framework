package framework.testcases;

import framework.data.ContactProvider;
import framework.drivermanager.DriverManager;
import framework.config.Config;
import framework.config.configDemo;
import framework.listeners.ScreenshotListener;
import framework.pages.ContactUsPage;
import framework.pages.Employers;
import framework.pages.NavBar;
import framework.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.module.Configuration;
import java.time.Duration;

@Listeners({ScreenshotListener.class})


public class ConfigTest {

    WebDriver driver;

    @BeforeSuite
    public void testSetup(){
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(enabled = false)
    public void testName() throws InterruptedException {
      //  String name = configDemo.getProperty("browser.name");
      // Assert.assertEquals(name,"safari");
        // WebDriver driver = DriverManager.getDriver();
        driver.get(configDemo.getProperty("app.url"));
        Thread.sleep(5000);
        //DriverManager.closeSession();

    }

    @Test(enabled = false)
    public void test2(){
        String property = Config.getProperty("report");
        Assert.assertEquals(property,"AutomationReport.html");
    }

    @Test(enabled = false)
    public void test3() throws InterruptedException {
        String name = Config.getProperty("browser.name");
        // Assert.assertEquals(name,"chrome");

        WebDriver driver = DriverManager.getDriver();
        driver.get(Config.getProperty("app.url"));
        Utils.captureScreenshot(driver, "test1", true);
        Thread.sleep(5000);
        DriverManager.closeSession();

    }


    @Test(enabled = false)
    public void browserTest() throws InterruptedException{
       WebDriver driver = DriverManager.getDriver();
        driver.get(Config.getProperty("app.url"));
        Thread.sleep(5000);
        DriverManager.closeSession();
    }

    @Test(enabled = false)
    public void browserTest2() throws InterruptedException{
        driver.get(Config.getProperty("app.url"));
        NavBar navBar = new NavBar(driver);
        ContactUsPage contactUsPage = navBar.clickApplyNow();
        contactUsPage.firstName("Sim").lastName("Virk").email("abc@xyz").company("pragra").phone("12333").message("xyz").source("xyz");
        Thread.sleep(5000);

    }

    @Test
    public void browserTest3() throws InterruptedException{
        driver.get(Config.getProperty("app.url"));
        Employers employers = new Employers(driver);
        ContactUsPage contactUsPage = employers.clickButton();
        Thread.sleep(2000);
    }

    @Test (enabled = false)//(dataProvider =  "contactProvider", dataProviderClass = ContactProvider.class)   //// to inject the data into my test case
    public void testExcel(Object id, String fname, String lname, String phone, String email){
        System.out.println(fname + " " + lname);
    }

    @Test(enabled = false) //(dataProvider =  "guestProvider", dataProviderClass = ContactProvider.class)
    public void testExcelGuest(String name){
        System.out.println(name + " " + name);
    }



    @AfterSuite()
    public void tearDown(){

        DriverManager.closeSession();
    }
}
