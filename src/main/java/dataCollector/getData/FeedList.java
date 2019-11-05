package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedList {
    public FeedList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private ArrayList<Map> getFeed() throws InterruptedException, IOException {
        int checkLocation;
        int location = 0;
        WebDriverWait wait = new WebDriverWait(Utils.driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) Utils.driver;
        js.executeScript("scrollBy(0, 500)");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS)));
        WebElement allFeed = Utils.driver.findElement(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS));
        int currentDate = 0;
        ArrayList<Map> feedList = new ArrayList<>();
        while (true) {
            List<WebElement> feedListLocator = allFeed.findElements(By.cssSelector(Constants.FEED_LIST_LOCATOR_BY_CSS));
            checkLocation = location;
            Thread.sleep(7000);
            location = feedListLocator.get(feedListLocator.size() - 1).getLocation().y;
            if (location == checkLocation || feedListLocator.size() > 10) {
                for (WebElement feed : feedListLocator) {
                    currentDate++;
                    Map<String, String> post = new HashMap<>();
                    try {
                        WebElement postMessageContainer = feed.findElement(By.cssSelector(Constants.FEED_POST_MESSAGE_LOCATOR_BY_CSS));
                        String postMessage = Utils.getElementInnerTextByTagNameByCss(postMessageContainer, Constants.TAG_NAME_P, Constants.ATTRIBUTE_INNER_TEXT);
                        String postImageUrl = Utils.checkImageType(feed);
                        String postTitle = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_TITLE_LOCATOR_BY_CSS, Constants.ATTRIBUTE_INNER_TEXT);
                        String postDate = Utils.executeDate(currentDate);
                        String postSharedLink = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_SHARED_LINK_BY_CSS, Constants.A_ATTRIBUTE_HREF);
                        String postSharedText = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_SHARED_TEXT_BY_CSS, Constants.ATTRIBUTE_INNER_TEXT);
                        post.put(JsonKeys.MESSAGE, postMessage);
                        post.put(JsonKeys.POST_IMAGE_URL, postImageUrl);
                        post.put(JsonKeys.TITLE, postTitle);
                        post.put(JsonKeys.SHARED_POST_LINK, postSharedLink);
                        post.put(JsonKeys.POST_TEXT, postSharedText);
                        post.put(JsonKeys.POST_DATE, postDate);
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        post.put(JsonKeys.MESSAGE, null);
                    }
                    if (!feedList.contains(post)) {
                        feedList.add(post);
                    }
                }
                break;
            }
            js.executeScript("scrollBy(0, " + location + ")");
            Thread.sleep(2000);
        }
        return feedList;
    }
    public ArrayList<Map> getFeedList() throws IOException, InterruptedException {
        return getFeed();
    }



}


