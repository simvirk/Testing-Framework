package framework.pages;

import framework.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {

    WebDriver driver;


    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id="msg")
    private WebElement message;

    @FindBy(id="source_type")
    private WebElement source;

    @FindBy(name = "Submit")
    private WebElement submit;


    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);}
    public ContactUsPage firstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;}
    public ContactUsPage lastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;}

    public ContactUsPage email(String email){
        this.email.sendKeys(email);
    return this;}

    public ContactUsPage company(String company){
        this.company.sendKeys(company);
        return this;}

    public ContactUsPage phone(String phone){
        this.phone.sendKeys(phone);
        return this;}

    public ContactUsPage message(String message){
        this.message.sendKeys(message);
        return this;}

    public ContactUsPage source(String source){
        this.source.sendKeys(source);
        return this;
    }



    //public ContactUsPage clickApplyNow(){
     //   this.applyNow.click();
     //   return new ContactUsPage(driver);
}

