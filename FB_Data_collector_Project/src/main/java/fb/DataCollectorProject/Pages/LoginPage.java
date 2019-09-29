package fb.DataCollectorProject.Pages;

import fb.DataCollectorProject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = Constants.LOGIN_FIELD_LOCATOR_BY_ID)
    private WebElement loginFieldLocator;


    @FindBy(id = Constants.PASSWORD_FIELD_LOCATOR_BY_ID)
    private WebElement passwordFieldLocator;

    @FindBy(id = Constants.SUBMIT_BUTTON_LOCATOR_BY_ID)
    private WebElement submitButtonLocator;


    private void loginFieldInput(){
        loginFieldLocator.sendKeys(Constants.INPUT_LOGIN);
    }

    private void passwordFieldInput(){
        passwordFieldLocator.sendKeys(Constants.INPUT_PASSWORD);
    }

    private void submitButtonClick(){
        submitButtonLocator.click();
    }

    public void goToMainPage(){
        loginFieldInput();
        passwordFieldInput();
        submitButtonClick();
    }
}
