package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Main {
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
        Utils.doTimeOuts(driver, 30);
        Utils.getUrl(driver,Constants.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToMainPage(Constants.INPUT_LOGIN, Constants.INPUT_PASSWORD);
        MainPage mainPage = new MainPage(driver);
        mainPage.userProfileButtonClick();
        Thread.sleep(5000);
        System.out.println(driver.getTitle());
    }


}
