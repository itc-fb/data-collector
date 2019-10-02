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
        String postMessageLocator = Constants.POST_MESSAGE_TEXT_BY_CSS;
        WebElement lastPost;
        int location = 0;
        while (true) {
            lastPost = postListLocator.get(postListLocator.size() - 1);
            if (location == lastPost.getLocation().y) {
                for (WebElement postInfo : postListLocator) {
                    Utils.waitByMls(1000);
                    Map<String, String> post = new HashMap<>();
                    WebElement postContainer = postInfo.findElement(By.cssSelector(postMessageLocator));
                    String postData = postContainer.getText();
                    post.put("data", postData);
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
