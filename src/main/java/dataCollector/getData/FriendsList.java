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
    /**
     *Initialize page factory elements.
     */
    public FriendsList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     *Finds visible friends locators before scroll if they are exist.
     */
    @FindBy(css = Constants.VISIBLE_FRIENDS_LOCATOR_BY_CSS)
    private List<WebElement> visibleFriends;

    /**
     *Goes to friends page by url.
     */
    private void goToFriendsPage() {
        String profileUrl = Utils.driver.findElement(By.cssSelector(Constants.PROFILE_URL_LOCATOR_BY_CSS)).getAttribute(Constants.A_ATTRIBUTE_HREF);
        String newUrl = profileUrl.concat(Constants.FRIENDS);
        Utils.driver.get(newUrl);
    }

    /**
     * Scroll on friends page.
     * If all friends are loaded, start collecting their names.
     * After data add to an array list.
     * Return array list.
     */
    public ArrayList<String> getFriendsList() {
        goToFriendsPage();
        Utils.waitByMls(3000);
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
}