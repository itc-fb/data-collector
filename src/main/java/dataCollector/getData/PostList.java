package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostList {
    /**
     * Initialize page factory elements.
     */
    public PostList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Finds posts locators, if they are exist.
     */
    @FindBy(css = Constants.POST_LIST_LOCATOR_BY_CSS)
    private List<WebElement> postListLocator;

    /**
     * Finds date locators, to check posts, if they are older than 1 year.
     */
    @FindBy(css = Constants.POST_DATE_LOCATOR_BY_CSS)
    private List<WebElement> postDateListLocator;

    /**
     * Get the people checkin count from post.
     */
    private static String exportNumberFromString(String checkingCount){
        String [] numbers = checkingCount.split("[^0-9]");
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            result.append(number);
        }
        if(result.toString().equals("")){
            return null;
        }
        return result.toString();
    }

    /**
     * Goes to user profile page by url.
     */
    private void goToProfile() {
        String newUrl = Utils.driver.findElement(By.cssSelector(Constants.PROFILE_URL_LOCATOR_BY_CSS)).getAttribute(Constants.A_ATTRIBUTE_HREF);
        Utils.driver.get(newUrl);
    }

    /**
     * Scroll on users profile page.
     * If all posts are loaded, which is older than 1 yer, start collecting their data.
     * After data add to an array list.
     * Return array list.
     */
    public ArrayList<Map> getUserPostList() {
        goToProfile();
        Utils.waitByMls(5000);
        ArrayList<Map> postList = new ArrayList<>();
        String postDateLocator = Constants.POST_DATE_LOCATOR_BY_CSS,
                postMessageLocator = Constants.POST_MESSAGE_TEXT_BY_CSS,
                postTitleLocator = Constants.POST_TITLE_LOCATOR_BY_CSS,
                postMapImageLocator = Constants.POST_MAP_ATTACHMENT_IMAGE_LOCATOR_BY_CSS,
                postImageLocator = Constants.POST_IMAGE_LOCATOR_BY_CSS,
                postLinkLocator = Constants.POST_LINK_LOCATOR_BY_CSS,
                postCheckedInPeopleCountLocator = Constants.COUNT_OF_PEOPLE_CHECKED_IN_POST_LINK_LOCATOR_BY_CSS,
                postCheckedInPlaceLocator = Constants.PLACE_WHERE_CHECKED_IN_THE_POST_LOCATOR_BY_CSS;
        WebElement lastPost;
        String lastDate;
        int newYear,
                location = 0;
        boolean checkYear;
        while (true) {
            Utils.waitByMls(4000);
            lastPost = postListLocator.get(postListLocator.size() - 1);
            lastDate = postDateListLocator.get(postDateListLocator.size() - 1).getAttribute(Constants.ABBR_ATTRIBUTE_TITLE);
            newYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
            checkYear = lastDate.contains(Integer.toString(newYear));
            if (location == lastPost.getLocation().y || checkYear) {
                for (WebElement postInfo : postListLocator) {
                    Map<String, String> post = new HashMap<>();
                    String postTitle = Utils.getElementInnerTextByParentByCss(postInfo, postTitleLocator),
                            postDate = Utils.getElementAttributeValueByParentByCss(postInfo, postDateLocator, Constants.ABBR_ATTRIBUTE_TITLE),
                            postMessage = Utils.getElementInnerTextByParentByCss(postInfo, postMessageLocator),
                            postMapImage = Utils.getElementAttributeValueByParentByCss(postInfo, postMapImageLocator, Constants.IMG_ATTRIBUTE_SRC),
                            postImage = Utils.getElementAttributeValueByParentByCss(postInfo, postImageLocator, Constants.IMG_ATTRIBUTE_SRC),
                            postLink = Utils.getElementAttributeValueByParentByCss(postInfo, postLinkLocator, Constants.A_ATTRIBUTE_HREF),
                            postCheckedInPeopleCount = Utils.getElementInnerTextByParentByCss(postInfo, postCheckedInPeopleCountLocator),
                            postCheckInPlace = Utils.getElementInnerTextByParentByCss(postInfo, postCheckedInPlaceLocator);
                    if(postCheckedInPeopleCount != null) {
                        postCheckedInPeopleCount = exportNumberFromString(postCheckedInPeopleCount);
                    }
                    post.put(JsonKeys.TITLE, postTitle);
                    post.put(JsonKeys.POST_DATE, postDate);
                    post.put(JsonKeys.MESSAGE, postMessage);
                    post.put(JsonKeys.MAP_IMAGE_URL, postMapImage);
                    post.put(JsonKeys.POST_IMAGE_URL, postImage);
                    post.put(JsonKeys.POSTED_LINK, postLink);
                    post.put(JsonKeys.CHECKIN_PEOPLE_COUNT, postCheckedInPeopleCount);
                    post.put(JsonKeys.CHECKIN_PLACE, postCheckInPlace);
                    postList.add(post);
                }
                break;
            }
            location = lastPost.getLocation().y;
            Utils.scrollToLocationWithWait(location);
        }
        return postList;
    }
}