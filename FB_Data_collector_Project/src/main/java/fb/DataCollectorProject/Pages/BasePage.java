package fb.DataCollectorProject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class BasePage {
    BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
