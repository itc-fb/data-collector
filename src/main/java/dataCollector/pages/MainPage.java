package dataCollector.pages;

import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriverWait wait = new WebDriverWait(Utils.driver, 3);

    public void userProfileButtonClick(){
        WebElement userProfileButton;
        userProfileButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.USER_PROFILE_BUTTON)));
        userProfileButton.click();
    }
}