package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Employers {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"gatsby-focus-wrapper\"]/div[2]/main/div/div[1]/a")
    private WebElement button;

    public Employers(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage clickButton(){
        this.button.click();
        return new ContactUsPage(driver);
    }





}
