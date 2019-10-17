package dataCollector.getData;


import dataCollector.Constants;
import dataCollector.pages.BasePage;
import dataCollector.Utils;
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

    @FindBy(css = Constants.POST_DATE_LOCATOR_BY_CSS)
    private List<WebElement> postDateListLocator;

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
                postCheckedInPlaceLocator = Constants.PLACE_WHERE_CHECKED_IN_THE_POST_LOCATOR_BY_CSS,
                postTextTitleLocator = Constants.POST_TEXT_TITLE_LOCATOR_BY_CSS,
                postTextLocator = Constants.POST_TEXT_LOCATOR_BY_CSS,
                sharedPostLinkLocator = Constants.SHARED_POST_LINK_LOCATOR_BY_CSS;
        WebElement lastPost;
        String lastDate;
        int newYear,
                location = 0;
        boolean checkYear;
        while (true) {
            lastPost = postListLocator.get(postListLocator.size() - 1);
            lastDate = postDateListLocator.get(postDateListLocator.size() - 1).getAttribute("title");
            newYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
            checkYear = lastDate.contains(Integer.toString(newYear));
            if (location == lastPost.getLocation().y || checkYear) {
//                Utils.doTimeOuts(Utils.driver, 0);
                for (WebElement postInfo : postListLocator) {
                    Map<String, String> post = new HashMap<>();
                    String postTitle = Utils.getElementInnerTextByParentByCss(postInfo, postTitleLocator),
                            postDate = Utils.getElementAttributeValueByParentByCss(postInfo, postDateLocator, "title"),
                            postMessage = Utils.getElementInnerTextByParentByCss(postInfo, postMessageLocator),
                            postMapImage = Utils.getElementAttributeValueByParentByCss(postInfo, postMapImageLocator, "src"),
                            postImage = Utils.getElementAttributeValueByParentByCss(postInfo, postImageLocator, "src"),
                            postVideo = Utils.getElementAttributeValueByParentByCss(postInfo, postVideoLocator, "src"),
                            postLink = Utils.getElementAttributeValueByParentByCss(postInfo, postLinkLocator, "href"),
                            postCheckedInPeopleCount = Utils.getElementInnerTextByParentByCss(postInfo, postCheckedInPeopleCountLocator),
                            postCheckInPlace = Utils.getElementInnerTextByParentByCss(postInfo, postCheckedInPlaceLocator),
                            postTextTitle = Utils.getElementInnerTextByParentByCss(postInfo, postTextTitleLocator),
                            postText = Utils.getElementInnerTextByParentByCss(postInfo, postTextLocator),
                            sharedPostLink = Utils.getElementAttributeValueByParentByCss(postInfo,sharedPostLinkLocator,"href");
                    post.put("Title", postTitle);
                    post.put("Date", postDate);
                    post.put("Message", postMessage);
                    post.put("MapImageUrl", postMapImage);
                    post.put("ImageUrl", postImage);
                    post.put("VideoUrl", postVideo);
                    post.put("PostedLink", postLink);
                    post.put("CheckinPeopleCount", postCheckedInPeopleCount);
                    post.put("CheckinPlace", postCheckInPlace);
                    post.put("PostTextTitle", postTextTitle);
                    post.put("PostText", postText);
                    post.put("SharedPostLink",sharedPostLink);
                    postList.add(post);
                }
                break;
            }
            location = lastPost.getLocation().y;
            Utils.scrollByLocation(location);
            Utils.waitByMls(3000);
        }
//        Utils.doTimeOuts(Utils.driver, 30);
        return postList;
    }

    public void getUserPosts() throws InterruptedException {
        Utils.waitByMls(2000);
        System.out.println(getUserPostsList());
    }
}
