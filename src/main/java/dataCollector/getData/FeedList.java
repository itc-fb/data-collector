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
import java.sql.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedList {
    public FeedList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    private String checkImageType(WebElement container, String attribute) {
        try {
            return container.findElement(By.cssSelector(Constants.FEED_POST_IMAGE_LOCATOR_FIRST_BY_CSS)).getAttribute(attribute);

        } catch (NoSuchElementException | StaleElementReferenceException e) {

            try {
                return container.findElement(By.cssSelector(Constants.FEED_POST_IMAGE_LOCATOR_SECOND_BY_CSS)).getAttribute(attribute);

            } catch (NoSuchElementException | StaleElementReferenceException er) {
                return null;
            }
        }
    }

    private static String executeCorrectDate(String date) {
        date = date.replaceAll("[=·]", "");
        String[] numbers = date.split("[^0-9]");
//        String dateSplit = date.replaceAll(" ", "");
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            result.append(number);
        }
        if (result.toString().equals("")) {
            return null;
        }
        int hour = Integer.parseInt(result.toString());
        Timestamp timestamp = new Timestamp(new Date().getTime());
        if (result.length() <= 2 && hour <= 23) {
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(timestamp);
            newDate.add(Calendar.HOUR, -hour);
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Timestamp(newDate.getTime().getTime()));
        } else if (result.length() <= 2) {
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(timestamp);
            newDate.add(Calendar.HOUR, -1);
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Timestamp(newDate.getTime().getTime()));
        }
        return date;
    }


    public ArrayList<Map> getFeed() throws InterruptedException, IOException {

        int checkLocation;
        int location = 0;
        WebDriverWait wait = new WebDriverWait(Utils.driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) Utils.driver;
        js.executeScript("scrollBy(0, 500)");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS)));
        WebElement allFeed = Utils.driver.findElement(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS));
        ArrayList<Map> feedList = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            List<WebElement> feedListLocator = allFeed.findElements(By.cssSelector(Constants.FEED_LIST_LOCATOR_BY_CSS));
            checkLocation = location;
            Thread.sleep(7000);
            location = feedListLocator.get(feedListLocator.size() - 1).getLocation().y;
            if (location == checkLocation || i == 4) {
//                String dateLocator = "div[class=\"clearfix _42ef\"]";
                for (WebElement feed : feedListLocator) {
                    Map<String, String> post = new HashMap<>();
                    try {
                        WebElement postMessageContainer = feed.findElement(By.cssSelector(Constants.FEED_POST_MESSAGE_LOCATOR_BY_CSS));
                        String postMessage = Utils.getElementInnerTextByTagNameByCss(postMessageContainer, Constants.TAG_NAME_P, Constants.ATTRIBUTE_INNER_TEXT);
                        String postImageUrl = checkImageType(feed, Constants.IMG_ATTRIBUTE_SRC);
                        String postTitle = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_TITLE_LOCATOR_BY_CSS, Constants.ATTRIBUTE_INNER_TEXT);
                        String postSharedLink = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_SHARED_LINK_BY_CSS, Constants.A_ATTRIBUTE_HREF);
                        String postSharedText = Utils.getElementAttributeValueByParentByCss(feed, Constants.FEED_POST_SHARED_TEXT_BY_CSS, Constants.ATTRIBUTE_INNER_TEXT);
                        post.put(JsonKeys.MESSAGE, postMessage);
                        post.put(JsonKeys.POST_IMAGE_URL, postImageUrl);
                        post.put(JsonKeys.TITLE, postTitle);
                        post.put(JsonKeys.SHARED_POST_LINK, postSharedLink);
                        post.put(JsonKeys.POST_TEXT, postSharedText);
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        post.put(JsonKeys.MESSAGE, null);
                    }


                    if (!feedList.contains(post)) {
                        feedList.add(post);
                    }
//                    String date = dateInfo.findElement(By.cssSelector(dateLocator)).getAttribute("innerText");
//                     date = executeCorrectDate(date);
//                    System.out.println(date);

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


