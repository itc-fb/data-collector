package dataCollector.pages;

import dataCollector.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.USER_PROFILE_BUTTON)
    private WebElement userProfileButton;

    public void userProfileButtonClick(){
        userProfileButton.click();
    }
}
