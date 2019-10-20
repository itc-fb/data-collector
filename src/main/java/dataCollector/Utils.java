package dataCollector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dataCollector.drivers.ChromeDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static WebDriver driver;

    static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    static void getUrl(WebDriver dr) {
        dr.get(Constants.BASE_URL);
    }

    static void initDriver(String browser) {
        switch (browser) {    //TO DO
            case "chrome":
                driver = new ChromeDriverFactory().initChromeDriver();
                break;
            default:
                System.out.println("---------Please enter correct browser name---------");
                break;
        }
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

    public static String getElementAttributeValueByParentByCss(WebElement parent, String selector, String attribute) {
        try {
            return parent.findElement(By.cssSelector(selector)).getAttribute(attribute);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    public static String getElementInnerTextByParentByCss(WebElement parent, String selector) {
        try {
            return parent.findElement(By.cssSelector(selector)).getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    public static String getElementAttributeValueByCss(String selector, String attribute) {
        try {
            return driver.findElement(By.cssSelector(selector)).getAttribute(attribute);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    public static String getElementInnerTextByCss(String selector) {
        try {
            return driver.findElement(By.cssSelector(selector)).getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    static void writeToJson(ArrayList friendList, ArrayList placeList, ArrayList videoList, ArrayList postList, ArrayList photoList) throws IOException {
        Map<String, Object> userData = new HashMap<>();
        userData.put("friends", friendList);
        userData.put("photos", photoList);
        userData.put("places", placeList);
        userData.put("posts", postList);
        userData.put("videos", videoList);
        userData.put("feed", null);
        ObjectMapper data = new ObjectMapper();
        data.enable(SerializationFeature.INDENT_OUTPUT);
        data.writeValue(new File("generatedUserData.json"), userData);
    }

    static void closeDriver(WebDriver dr) {
        dr.quit();
    }
}
