package framework.drivermanager;

import framework.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class DriverManager {
    private static DriverManager manager;
    private static Logger logger = LogManager.getLogger(DriverManager.class);
    private WebDriver driver;


    private DriverManager() {
        String browser = Config.getProperty("browser.name");
        logger.debug("Got browser name [ {} ] from framework.properties", browser);
        if (browser.equalsIgnoreCase(BrowserTypes.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(BrowserTypes.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase(BrowserTypes.SAFARI)) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase(BrowserTypes.OPERA)) {
            WebDriverManager.operadriver().setup();
            driver = new SafariDriver();
        } else if(browser.equalsIgnoreCase(BrowserTypes.REMOTE)){
            InternetExplorerOptions browserOptions = new InternetExplorerOptions();
            browserOptions.setPlatformName("Windows 7");
            browserOptions.setBrowserVersion("11");
            Map<String, Object> sauceOptions = new HashMap<>();

            browserOptions.setCapability("sauce:options",sauceOptions);
            String url = Config.getProperty("remote.url");

            try {
                driver = new RemoteWebDriver(new URL(url), browserOptions);
            } catch (MalformedURLException e){
                throw new RuntimeException(e);
            }
        }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
    public static WebDriver getDriver() {
        if (manager == null) {
            manager = new DriverManager();
        }
        return manager.driver;
    }
    public static void closeSession() { //if somebody accidently quits the driver
        if (manager != null) {
            try {
                manager.driver.quit();
            } catch (Exception ex) {
            }
            manager = null;
        }
    }
}
