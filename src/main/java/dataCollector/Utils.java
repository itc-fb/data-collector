package dataCollector;

import dataCollector.drivers.ChromeDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Utils {
    public static WebDriver driver;

    static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    static void getUrl(WebDriver dr) {
        dr.get(Constants.BASE_URL);
    }

    static void initDriver() {
        driver = new ChromeDriverFactory().initChromeDriver();
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
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return null;
        }
    }

    public static String getElementInnerTextByParentByCss(WebElement parent, String selector){
        try{
            return parent.findElement(By.cssSelector(selector)).getText();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return null;
        }
    }

    public static String getElementAttributeValueByCss(String selector, String attribute){
        try{
            return driver.findElement(By.cssSelector(selector)).getAttribute(attribute);
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return null;
        }
    }

    public static String getElementInnerTextByCss(String selector){
        try{
            return driver.findElement(By.cssSelector(selector)).getText();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return null;
        }
    }

    static void closeDriver(WebDriver dr) {
        dr.quit();
    }
}
