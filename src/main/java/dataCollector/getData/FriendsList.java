package dataCollector.getData;


import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class FriendsList {
    public FriendsList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.VISIBLE_FRIENDS_LOCATOR_BY_CSS)
    private List<WebElement> visibleFriends;

    private void goToFriendsPage() {
        String profileUrl = Utils.driver.findElement(By.cssSelector(Constants.PROFILE_URL_LOCATOR_BY_CSS)).getAttribute(Constants.A_ATTRIBUTE_HREF);
        String newUrl = profileUrl.concat(Constants.FRIENDS);
        Utils.driver.get(newUrl);
    }

    private ArrayList<String> getFriends() {
        ArrayList<String> friendsList = new ArrayList<>();
        Utils.waitByMls(5000);
        if (visibleFriends.size() > 0) {
            WebElement lastFriend;
            int location = 0;
            while (true) {
                lastFriend = visibleFriends.get(visibleFriends.size() - 1);
                Utils.waitByMls(2000);
                if (lastFriend.getLocation().y == location) {
                    for (WebElement friend : visibleFriends) {
                        friendsList.add(friend.getAttribute(Constants.IMG_ATTRIBUTE_ARIA_LABEL));
                    }
                    break;
                }
                location = lastFriend.getLocation().y;
                Utils.scrollToLocationWithWait(location); //5000
            }
        }
        return friendsList;
    }

    public ArrayList<String> getFriendsList() throws InterruptedException {
        goToFriendsPage();
        Utils.waitByMls(3000);
        return getFriends();
    }
}