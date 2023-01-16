package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {

    private WebDriver driver;
                                                  //Find by annotation comes from page factory
    @FindBy(xpath = "//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[1]/a/svg[1]")
    private WebElement logo;

    @FindBy(xpath = "//*[@id=\"headlessui-popover-button-:R5t9:\"]/span")
    private WebElement applicant;

    @FindBy(xpath = "//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/div/a")
    private WebElement applyNow;

    public NavBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage clickApplyNow(){
        this.applyNow.click();
        return new ContactUsPage(driver);
    }



}
