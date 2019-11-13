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
    /**
     * Web driver.
     */
    public static WebDriver driver;

    /**
     * Maximize browser window.
     */
    public static void maximizeWindow(WebDriver dr) {
        dr.manage().window().maximize();
    }

    /**
     * Open page by url.
     */
    public static void getUrl(WebDriver dr) {
        dr.get(Constants.BASE_URL);
    }

    /**
     * Initialize driver.
     */
    static void initDriver() {
        driver = new ChromeDriverFactory().initChromeDriver();
    }

    /**
     * Scroll to given location and wait by given time.
     */
    public static void scrollToLocationWithWait(int location, int waitAfter){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + location + ")");
        waitByMls(waitAfter);
    }

    /**
     * Scroll to given location and wait by default time.
     */
    public static void scrollToLocationWithWait(int location){
        scrollToLocationWithWait(location, 5000);
    }

    /**
     * Wait by given time.
     */
    public static void waitByMls(int mls) {
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check the type of feed list image locator.
     */
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

    /**
     * Get date for feed post.
     */
    public static String executeDate(int date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT, Locale.ENGLISH);
        LocalDateTime newDate = LocalDateTime.now().minusDays((int)Math.floor(date/Constants.POST_COUNT));
        return dateFormat.format(newDate);
    }

    /**
     * Get the element attribute value by his parent element.
     */
    public static String getElementAttributeValueByParentByCss(WebElement parent, String selector, String attribute) {
        try {
            return parent.findElement(By.cssSelector(selector)).getAttribute(attribute);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    /**
     * Get the element inner text  by his parent element.
     */
    public static String getElementInnerTextByParentByCss(WebElement parent, String selector) {
        try {
            return parent.findElement(By.cssSelector(selector)).getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    /**
     * Get the element attribute value.
     */
    public static String getElementAttributeValueByCss(String selector, String attribute) {
        try {
            return driver.findElement(By.cssSelector(selector)).getAttribute(attribute);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    /**
     * Get the element inner text.
     */
    public static String getElementInnerTextByCss(String selector) {
        try {
            return driver.findElement(By.cssSelector(selector)).getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return null;
        }
    }

    /**
     * Get the element inner text by tag name.
     */
    public static String getElementInnerTextByTagNameByCss(WebElement parent, String tagName, String attribute){
        try{
            return parent.findElement(By.tagName(tagName)).getAttribute(attribute);        }
        catch( NoSuchElementException | StaleElementReferenceException e ){
            return null;
        }
    }

    /**
     * Write all collected data to JSON file.
     */
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

    /**
     * Quit driver.
     */
    static void closeDriver(WebDriver dr) {
        dr.quit();
    }
}