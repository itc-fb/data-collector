package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedList {
    /**
     * Initialize page factory elements.
     * */
    public FeedList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Finds one or more feed posts locators, to check if they are exist.
     */
    @FindBy(css = Constants.FEED_LIST_LOCATOR_BY_CSS)
    private List<WebElement> check;

    /**
     * Scrolls on feed posts page.
     * Scrolls to the end of page or if the count of posts equals 1000.
     * After, start collecting of posts data.
     * Then add data to an array list and return it.
     */
    public ArrayList<Map> getFeedList() throws InterruptedException {
        ArrayList<Map> feedList = new ArrayList<>();
        try {
            int checkLocation;
            int location = 0;
            WebDriverWait wait = new WebDriverWait(Utils.driver, 15);
            JavascriptExecutor js = (JavascriptExecutor) Utils.driver;
            js.executeScript("scrollBy(0, 500)");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS)));
            WebElement allFeed = Utils.driver.findElement(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS));
            int currentDate = 0;
            if(check.size() > 0) {
                while (true) {
                    List<WebElement> feedListLocator = allFeed.findElements(By.cssSelector(Constants.FEED_LIST_LOCATOR_BY_CSS));
                    checkLocation = location;
                    Utils.waitByMls(7000);
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
                    Utils.scrollToLocationWithWait(location);
                    Utils.waitByMls(2000);
                }
            }
            return feedList;
        } catch (TimeoutException | NoSuchElementException e) {
            return feedList;
        }
    }
}