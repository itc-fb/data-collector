package dataCollector.getData;


import dataCollector.Constants;
import dataCollector.Utils;
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

    @FindBy(css = Constants.FRIENDS_SECTION_LOCATOR_BY_CSS)
    private WebElement friendsSectionLocator;

    @FindBy(css = Constants.FRIENDS_COUNT_LOCATOR_BY_CSS)
    private WebElement friendsCountLocator;

    private void friendsSectionClick() {
        friendsSectionLocator.click();
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
                    String friendAttributeAriaLabel = "aria-label";
                    for (WebElement friend : visibleFriends
                    ) {
                        friendsList.add(friend.getAttribute(friendAttributeAriaLabel));
                    }
                    break;
                }
            }
        }else{
            friendsList.add(null);
        }
        return friendsList;
    }

    public void getFriendsList() throws InterruptedException {
        friendsSectionClick();
        System.out.println(getFriends());
    }
}