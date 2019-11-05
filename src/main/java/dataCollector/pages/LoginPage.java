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
    private static WebDriverWait wait = new WebDriverWait(Utils.driver, 10);

    private static void loginAndPasswordInput(String inputData, String locator){
        WebElement input;
        input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        input.sendKeys(inputData);
    }

    private static void submitButtonClick(){
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

    public static void logIn(String login, String password){
        Utils.maximizeWindow(Utils.driver);
        Utils.getUrl(Utils.driver);
        loginAndPasswordInput(login, Constants.LOGIN_FIELD_LOCATOR_BY_CSS);
        loginAndPasswordInput(password, Constants.PASSWORD_FIELD_LOCATOR_BY_CSS);
        submitButtonClick();
    }

}
