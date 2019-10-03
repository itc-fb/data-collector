package fb.DataCollectorProject.getData;


import fb.DataCollectorProject.Constants;
import fb.DataCollectorProject.Pages.BasePage;
import fb.DataCollectorProject.Utils;
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
        String postDateLocator = Constants.POST_DATE_LOCATOR_BY_CSS,
                postMessageLocator = Constants.POST_MESSAGE_TEXT_BY_CSS,
                postTitleLocator = Constants.POST_TITLE_LOCATOR_BY_CSS,
                postMapImageLocator = Constants.POST_MAP_ATTACHMENT_IMAGE_LOCATOR_BY_CSS,
                postImageLocator = Constants.POST_IMAGE_LOCATOR_BY_CSS,
                postVideoLocator = Constants.POST_VIDEO_LOCATOR_BY_CSS,
                postLinkLocator = Constants.POST_LINK_LOCATOR_BY_CSS,
                postCheckedInPeopleCountLocator = Constants.COUNT_OF_PEOPLE_CHECKED_IN_POST_LINK_LOCATOR_BY_CSS,
                postCheckedInPlaceLocator = Constants.PLACE_WHERE_CHECKED_IN_THE_POST_LOCATOR_BY_CSS;
        WebElement lastPost;
        int location = 0;
        while (true) {
            lastPost = postListLocator.get(postListLocator.size() - 1);
            if (location == lastPost.getLocation().y) {
                Utils.doTimeOuts(Utils.driver, 0);
                for (WebElement postInfo : postListLocator) {
                    Map<String, String> post = new HashMap<>();
                    String postTitle = Utils.getElementInnerTextByCss(postInfo, postTitleLocator),
                            postDate = Utils.getElementAttributeValueByCss(postInfo, postDateLocator,"title"),
                            postMessage = Utils.getElementInnerTextByCss(postInfo, postMessageLocator),
                            postMapImage = Utils.getElementAttributeValueByCss(postInfo, postMapImageLocator, "src"),
                            postImage = Utils.getElementAttributeValueByCss(postInfo, postImageLocator, "src"),
                            postVideo = Utils.getElementAttributeValueByCss(postInfo, postVideoLocator, "src"),
                            postLink = Utils.getElementAttributeValueByCss(postInfo, postLinkLocator, "href"),
                            postCheckedInPeopleCount = Utils.getElementInnerTextByCss(postInfo, postCheckedInPeopleCountLocator),
                            postCheckInPlace = Utils.getElementInnerTextByCss(postInfo, postCheckedInPlaceLocator);
                    post.put("Title", postTitle);
                    post.put("Date", postDate);
                    post.put("Message", postMessage);
                    post.put("MapImageUrl", postMapImage);
                    post.put("ImageUrl", postImage);
                    post.put("VideoUrl", postVideo);
                    post.put("PostedLink", postLink);
                    post.put("CheckinPeopleCount", postCheckedInPeopleCount);
                    post.put("CheckinPlace", postCheckInPlace);
                    postList.add(post);
                }
                break;
            }
            location = lastPost.getLocation().y;
            Utils.scrollByLocation(location);
            Utils.waitByMls(3000);
        }
        Utils.doTimeOuts(Utils.driver,30);
        return postList;
    }

    public void getUserPosts() throws InterruptedException {
        Utils.waitByMls(2000);
        System.out.println(getUserPostsList());
    }
}
