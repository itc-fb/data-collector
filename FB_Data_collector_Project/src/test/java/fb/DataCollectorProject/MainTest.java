package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MainTest {
    private static WebDriver driver;


    @BeforeClass
    static public void beforeC() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ke1io\\Downloads\\111111111111\\chromedriver.exe");
        driver = new ChromeDriver(ops);
    }


//    @AfterClass
//    static public void afterC() {
//        driver.quit();
//    }


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
        System.out.println(driver.getTitle());
    }


}
