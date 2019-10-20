package dataCollector.pages;

import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriverWait wait = new WebDriverWait(Utils.driver, 10);

    private void loginFieldInput(){
        WebElement loginFieldLocator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.LOGIN_FIELD_LOCATOR_BY_CSS)));
        loginFieldLocator.sendKeys(Constants.INPUT_LOGIN);
    }

    private void passwordFieldInput(){
        WebElement passwordFieldLocator;
        passwordFieldLocator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.PASSWORD_FIELD_LOCATOR_BY_CSS)));
        passwordFieldLocator.sendKeys(Constants.INPUT_PASSWORD);
    }

    private void submitButtonClick(){
        WebElement submitButtonLocator;
        try{submitButtonLocator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.SUBMIT_INPUT_BUTTON_LOCATOR_BY_CSS)));
        submitButtonLocator.click();
        }catch (TimeoutException | NoSuchElementException e){
            submitButtonLocator = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.SUBMIT_BUTTON_LOCATOR_BY_CSS)));
            submitButtonLocator.click();
        }
    }

    public void goToMainPage(){
        loginFieldInput();
        passwordFieldInput();
        submitButtonClick();
    }
}
