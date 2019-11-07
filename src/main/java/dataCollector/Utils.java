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

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {
    public static WebDriver driver;

    public static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    public static void getUrl(WebDriver dr) {
        dr.get(Constants.BASE_URL);
    }

    static void initDriver(String browser) {
        switch (browser) {    //TO DO
            case Constants.CHROME:
                driver = new ChromeDriverFactory().initChromeDriver();
                break;
            default:
                System.out.println("---------Please enter correct browser name---------");
                break;
        }
    }

    public static void scrollToLocationWithWait(int location, int waitAfter){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + location + ")");
        waitByMls(waitAfter);
    }

    public static void scrollToLocationWithWait(int location){
        scrollToLocationWithWait(location, 5000);
    }

    public static void waitByMls(int mls) {
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static String checkImageType(WebElement container) {
        try {
            return container.findElement(By.cssSelector(Constants.FEED_POST_IMAGE_LOCATOR_FIRST_BY_CSS)).getAttribute(Constants.IMG_ATTRIBUTE_SRC);

        } catch (NoSuchElementException | StaleElementReferenceException e) {

            try {
                return container.findElement(By.cssSelector(Constants.FEED_POST_IMAGE_LOCATOR_SECOND_BY_CSS)).getAttribute(Constants.IMG_ATTRIBUTE_SRC);

            } catch (NoSuchElementException | StaleElementReferenceException er) {
                return null;
            }
        }
    }

    public static String executeDate(int date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT, Locale.ENGLISH);
        LocalDateTime newDate = LocalDateTime.now().minusDays((int)Math.floor(date/Constants.POST_COUNT));
        return dateFormat.format(newDate);
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

    public static String getElementInnerTextByTagNameByCss(WebElement parent, String tagName, String attribute){
        try{
            return parent.findElement(By.tagName(tagName)).getAttribute(attribute);        }
        catch( NoSuchElementException | StaleElementReferenceException e ){
            return null;
        }
    }

    static void writeToJson(String login, ArrayList feed,ArrayList friendList, ArrayList placeList, ArrayList videoList, ArrayList postList, ArrayList photoList) throws IOException {
        Map<String, Object> userData = new HashMap<>();
        userData.put(JsonKeys.FRIENDS, friendList);
        userData.put(JsonKeys.PHOTOS, photoList);
        userData.put(JsonKeys.PLACES, placeList);
        userData.put(JsonKeys.POSTS, postList);
        userData.put(JsonKeys.VIDEOS, videoList);
        userData.put(JsonKeys.FEED, feed);
        ObjectMapper data = new ObjectMapper();
        data.enable(SerializationFeature.INDENT_OUTPUT);
        data.writeValue(new File("usersGeneratedData/"+login+".json"), userData);
    }

    static void closeDriver(WebDriver dr) {
        dr.quit();
    }
}
