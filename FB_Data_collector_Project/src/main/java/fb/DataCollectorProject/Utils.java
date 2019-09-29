package fb.DataCollectorProject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

class Utils {
    static WebDriver driver;
    private static String operationSystem = System.getProperty("os.name");

    static void  maximizeWindow(WebDriver dr){
                dr.manage().window().maximize();
    }
    static void doTimeOuts(WebDriver dr){
        dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    static void getUrl(WebDriver dr){
        dr.get(Constants.BASE_URL);
    }

    static void initializeDriver(){
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        if (operationSystem.contains("Win")) {
            String windowsChromeDriverPath = "src\\main\\resources\\driver\\win\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", windowsChromeDriverPath);
        } else if(operationSystem.contains("Linux")) {
            String linuxChromeDriverPath = "src/main/resources/driver/linux/chromedriver";
            System.setProperty("webdriver.chrome.driver", linuxChromeDriverPath);
        }
        driver = new ChromeDriver(ops);
    }

    static void closeDriver(WebDriver dr){
        dr.quit();
    }
}
