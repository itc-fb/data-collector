
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

public class FeedList {
    private WebDriver driver;


    private String checkImageType(WebElement container, String attribute){
        try{
            return container.findElement(By.cssSelector(Constants.POST_IMAGE_LOCATOR_FIRST_BY_CSS)).getAttribute(attribute);

        }catch (NoSuchElementException | StaleElementReferenceException e){

            return container.findElement(By.cssSelector(Constants.POST_IMAGE_LOCATOR_SECOND_BY_CSS)).getAttribute(attribute);
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
        if (result.length() <= 2 && hour <= 23) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(timestamp);
            newDate.add(Calendar.HOUR, -hour);
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Timestamp(newDate.getTime().getTime()));
        } else if (result.length() <= 2) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(timestamp);
            newDate.add(Calendar.HOUR, -1);
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Timestamp(newDate.getTime().getTime()));
        }
        return date;
    }


    public void feedList() throws InterruptedException, IOException {

        int checkLocation;
        int location = 0;
        WebDriverWait wait = new WebDriverWait(driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0, 500)");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS)));
        WebElement allFeed = driver.findElement(By.cssSelector(Constants.ALL_FEED_LOCATOR_BY_CSS));
        ArrayList<Map> feedList = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            List<WebElement> feedListLocator = allFeed.findElements(By.cssSelector(Constants.FEED_LIST_LOCATOR_BY_CSS));
            checkLocation = location;
            Thread.sleep(7000);
            location = feedListLocator.get(feedListLocator.size() - 1).getLocation().y;
            if (location == checkLocation || i==4) {
//                String dateLocator = "div[class=\"clearfix _42ef\"]";
                for (WebElement feed : feedListLocator) {
                    Map<String, String> post = new HashMap<>();
                        try {
                            WebElement postMessageContainer = feed.findElement(By.cssSelector(Constants.POST_MESSAGE_LOCATOR_BY_CSS));
                            String postMessage = postMessageContainer.findElement(By.tagName("p")).getAttribute("innerText");//TODO
//                        String postImageUrl = checkImageType(feed, "src");
//                        String postTitle = feed.findElement(By.cssSelector(Constants.POST_TITLE_LOCATOR_BY_CSS)).getAttribute("innerText");
//                        String postSharedLink = feed.findElement(By.cssSelector(Constants.POST_SHARED_LINK_BY_CSS)).getAttribute("href");
//                        String postSharedText = feed.findElement(By.cssSelector(Constants.POST_SHARED_TEXT_BY_CSS)).getAttribute("innerText");
                            post.put("message", postMessage);
                        }catch (NoSuchElementException | StaleElementReferenceException e){
                            post.put("message", null);
                        }
//                        post.put("imageUrl", postImageUrl);
//                        post.put("title", postTitle);
//                        post.put("sharedLink", postSharedLink);
//                        post.put("sharedText", postSharedText);

                    if(!feedList.contains(post)){
                        feedList.add(post);
                    }
//                    String date = dateInfo.findElement(By.cssSelector(dateLocator)).getAttribute("innerText");
//                     date = executeCorrectDate(date);
//                    System.out.println(date);

                }
                System.out.println(feedList);
                break;
            }
            js.executeScript("scrollBy(0, " + location + ")");
            Thread.sleep(2000);
        }


    }


}


