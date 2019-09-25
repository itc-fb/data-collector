package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends LoginPage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#u_0_a > div:nth-child(1) > div:nth-child(1) > div > a")
    private WebElement userProfileButton;

    public MainPage userProfileButtonClick(){
        userProfileButton.click();
        return this;
    }
}
