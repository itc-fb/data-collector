package fb.DataCollectorProject.getData;

import fb.DataCollectorProject.Constants;
import fb.DataCollectorProject.Pages.BasePage;
import fb.DataCollectorProject.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends BasePage {
    public FriendsList(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.VISIBLE_FRIENDS_LOCATOR_BY_CSS)
    private List<WebElement> visibleFriends;

    @FindBy(css = Constants.FRIENDS_SECTION_LOCATOR_BY_CSS)
    private WebElement friendsSectionLocator;

    @FindBy(css = Constants.AFTER_SCROLL_FRIENDS_LOCATOR_BY_CSS)
    private List<WebElement> afterScroll;

    private void friendsSectionClick() {
        friendsSectionLocator.click();
    }

    private ArrayList<String> getFriends() throws InterruptedException {
        ArrayList<String> friendsList = new ArrayList<>();
        WebElement lastFriend;
        int location;
        while (true) {
            lastFriend = visibleFriends.get(visibleFriends.size() - 1);
            location = lastFriend.getLocation().y;
            Utils.scrollByLocation(location);
            Utils.waitByMls(3000);
            if (visibleFriends.size() == afterScroll.size()) {
                String friendAttributeAriaLabel = "aria-label";
                for (WebElement friend : afterScroll
                ) {
                    friendsList.add(friend.getAttribute(friendAttributeAriaLabel));
                }
                break;
            }
        }
        return friendsList;
    }

    public void getFriendsList() throws InterruptedException {
        friendsSectionClick();
        System.out.println(getFriends());
    }
}
