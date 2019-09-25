package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


        private WebDriver driver;

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }


    @FindBy(id = Constants.LOGIN_FIELD_LOCATOR_BY_ID)
    private WebElement loginFieldLocator;


    @FindBy(id = Constants.PASSWORD_FIELD_LOCATOR_BY_ID)
    private WebElement passwordFieldLocator;

    @FindBy(id = Constants.SUBMIT_BUTTON_LOCATOR_BY_ID)
    private WebElement submitButtonLocator;



    public LoginPage loginFieldInput(String login){
        loginFieldLocator.sendKeys(login);
        return this;
    }

    public LoginPage passwordFieldInput(String password){
        passwordFieldLocator.sendKeys(password);
        return this;
    }


    public LoginPage submitButtonClick(){
        submitButtonLocator.click();
        return this;
    }


    public MainPage goToMainPage(String log, String pass){
        loginFieldInput(log);
        passwordFieldInput(pass);
        submitButtonClick();
        return new MainPage(driver);
    }



}
