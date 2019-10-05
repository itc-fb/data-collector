package fb.DataCollectorProject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static WebDriver driver;
    private static String operationSystem = System.getProperty("os.name");

    static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    public static void doTimeOuts(WebDriver dr, int seconds) {
        dr.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
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

    public static String getElementAttributeValueByParentByCss(WebElement parent, String selector, String attribute){
        try{
            return parent.findElement(By.cssSelector(selector)).getAttribute(attribute);
        }catch (NoSuchElementException |StaleElementReferenceException e){
            return "not exist";
        }
    }

    public static String getElementInnerTextByParentByCss(WebElement parent, String selector){
        try{
            return parent.findElement(By.cssSelector(selector)).getText();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return "not exist";
        }
    }

    public static String getElementAttributeValueByCss(String selector, String attribute){
        try{
            return driver.findElement(By.cssSelector(selector)).getAttribute(attribute);
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return "not exist";
        }
    }

    public static String getElementInnerTextByCss(String selector){
        try{
            return driver.findElement(By.cssSelector(selector)).getText();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return "not exist";
        }
    }

    static void closeDriver(WebDriver dr) {
        dr.quit();
    }



}
