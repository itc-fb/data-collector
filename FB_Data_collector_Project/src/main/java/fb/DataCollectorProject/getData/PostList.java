package fb.DataCollectorProject.getData;

import fb.DataCollectorProject.Constants;
import fb.DataCollectorProject.Pages.BasePage;
import fb.DataCollectorProject.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class PostList extends BasePage {

    public PostList(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.POST_LIST_LOCATOR_BY_CSS)
    private List<WebElement> postListLocator;


    private ArrayList<Map> getUserPostsList() throws InterruptedException {
        ArrayList<Map> postList = new ArrayList<>();
        String postTitleLocator = Constants.POST_TITLE_LOCATOR_BY_CSS;
        String messageLinkLocator = Constants.POST_MESSAGE_LINK_BY_CSS;
        String dateLocator = Constants.POST_DATE_LOCATOR_BY_TAG_NAME;
        String messageTextLocator = Constants.POST_MESSAGE_TEXT_BY_CSS;
        WebElement lastPost;
        int location = 0;
        while (true) {
            lastPost = postListLocator.get(postListLocator.size() - 1);
            if (location == lastPost.getLocation().y) {
                boolean isLink;
                boolean isText;
                for (WebElement postInfo : postListLocator) {
                    Utils.waitByMls(1000);
                    String postMessageLink;
                    String postMessageText;
                    Map<String, String> post = new HashMap<>();
                    String postTitle = postInfo.findElement(By.cssSelector(postTitleLocator)).getText();
                    String postDate = postInfo.findElement(By.tagName(dateLocator)).getAttribute("title");
                    try {
                        isLink = postInfo.findElement(By.cssSelector(messageLinkLocator)).isDisplayed();
                        isText = postInfo.findElement(By.cssSelector(messageTextLocator)).isDisplayed();
                    } catch (Exception e) {
                        isLink = false;
                        isText = false;
                    }
                    if (isText) {
                        if (isLink) {
                            postMessageText = postInfo.findElement(By.cssSelector(messageTextLocator)).getText();
                            postMessageLink = postInfo.findElement(By.cssSelector(messageLinkLocator)).getAttribute("href");
                        } else {
                            postMessageText = postInfo.findElement(By.cssSelector(messageTextLocator)).getText();
                            postMessageLink = null;
                        }
                   }
//                    else if (isLink && !isText) {
//                        postMessageLink = postInfo.findElement(By.cssSelector(messageLinkLocator)).getAttribute("href");
//                        postMessageText = null;
//                    } else if(){}
//
                    else {
                        postMessageText = null;
                        postMessageLink = null;
                    }


                    post.put("title", postTitle);
                    post.put("date", postDate);
                    post.put("messageText", postMessageText);
                    post.put("messageLink", postMessageLink);
                    postList.add(post);
                }

                break;
            }

            location = lastPost.getLocation().y;
            Utils.scrollByLocation(location);
            Utils.waitByMls(3000);
        }
        return postList;
    }

    public void getUserPosts() throws InterruptedException {
        Utils.waitByMls(2000);
        System.out.println(getUserPostsList());
    }
}
