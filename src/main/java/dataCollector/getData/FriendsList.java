package dataCollector.getData;


import dataCollector.Constants;
import dataCollector.Utils;
import dataCollector.pages.MainPage;
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

    @FindBy(css = Constants.FRIENDS_COUNT_LOCATOR_BY_CSS)
    private WebElement friendsCountLocator;

    private void goToFriendsPage() {
        String url = Utils.driver.getCurrentUrl();
        String newUrl = url.concat(Constants.FRIENDS);
        Utils.driver.get(newUrl);
    }

    private void goToUserProfile() {
        new MainPage().userProfileButtonClick();
    }

    private ArrayList<String> getFriends() throws InterruptedException {
        ArrayList<String> friendsList = new ArrayList<>();
        Utils.waitByMls(5000);
        if (visibleFriends.size() > 0) {
            WebElement lastFriend;
            int location;
            int count = Integer.parseInt(friendsCountLocator.getText());

            while (visibleFriends.size() <= count) {
                lastFriend = visibleFriends.get(visibleFriends.size() - 1);
                location = lastFriend.getLocation().y;
                Utils.scrollByLocation(location);
                Utils.waitByMls(5000);
                if (visibleFriends.size() == count) {
                    for (WebElement friend : visibleFriends
                    ) {
                        friendsList.add(friend.getAttribute(Constants.IMG_ATTRIBUTE_ARIA_LABEL));
                    }
                    break;
                }
            }
        }else{
            friendsList.add(null);
        }
        return friendsList;
    }

    public ArrayList<String> getFriendsList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        goToFriendsPage();
        return getFriends();
    }
}