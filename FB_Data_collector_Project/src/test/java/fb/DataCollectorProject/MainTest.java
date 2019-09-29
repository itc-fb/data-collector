package fb.DataCollectorProject;

import fb.DataCollectorProject.Pages.LoginPage;
import fb.DataCollectorProject.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MainTest {
    private static WebDriver driver;

    @BeforeClass
    static public void beforeC() {
        Utils.initializeDriver();
        driver = Utils.driver;
    }

    @AfterClass
    static public void afterC() {
        Utils.closeDriver(driver);
    }

    @Test
    public void testMethod() throws InterruptedException { //
        Utils.maximizeWindow(driver);
        Utils.doTimeOuts(driver);
        Utils.getUrl(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToMainPage();
        MainPage mainPage = new MainPage(driver);
        mainPage.userProfileButtonClick();
        Thread.sleep(5000);
        System.out.println(System.getProperty("os.name"));
    }

}
