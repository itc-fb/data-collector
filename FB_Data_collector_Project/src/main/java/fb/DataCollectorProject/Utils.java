package fb.DataCollectorProject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static WebDriver driver;
    private static String operationSystem = System.getProperty("os.name");

    static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    static void doTimeOuts(WebDriver dr) {
        dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    static void getUrl(WebDriver dr) {
        dr.get(Constants.BASE_URL);
    }

    static void initializeDriver() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        if (operationSystem.contains("Win")) {
            String windowsChromeDriverPath = "src\\main\\resources\\driver\\win\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", windowsChromeDriverPath);
        } else if (operationSystem.contains("Linux")) {
            String linuxChromeDriverPath = "src/main/resources/driver/linux/chromedriver";
            System.setProperty("webdriver.chrome.driver", linuxChromeDriverPath);
        }
        driver = new ChromeDriver(ops);
    }

    public static void scrollByLocation(int location) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + location + ")");
    }

    public static void waitByMls(int mls) throws InterruptedException {
        Thread.sleep(mls);
    }

    public static void moveToElement(WebElement el) {
        Actions actions = new Actions(Utils.driver);
        actions.moveToElement(el).perform();
    }

    static void closeDriver(WebDriver dr) {
        dr.quit();
    }

}
