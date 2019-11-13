package dataCollector;

import dataCollector.getData.*;
import dataCollector.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainTest {
    /**
     * Set up the driver before the test method starts.
     */
    @BeforeClass
    static public void setupDriver() {
        Utils.initDriver();
    }

    /**
     * Quit the driver after the test method finished.
     */
    @AfterClass
    static public void closeDriver() {
        Utils.closeDriver(Utils.driver);
    }

    /**
     * Test, which collects all required data.
     */
    @Parameters({Constants.LOGIN, Constants.PASSWORD})
    @Test
    public void getData(String login, String password) throws InterruptedException, IOException {
        LoginPage.logIn(login, password);
        ArrayList<Map> feed = new FeedList(Utils.driver).getFeedList();
        ArrayList<String> friends = new FriendsList(Utils.driver).getFriendsList();
        ArrayList<String> places = new PlacesList(Utils.driver).getPlaceList();
        ArrayList<Map> videos = new VideosList(Utils.driver).getVideoList();
        ArrayList<Map> posts = new PostList(Utils.driver).getUserPostList();
        ArrayList<Map> photos = new PhotoList(Utils.driver).getPhotoList();
        Utils.writeToJson(login, feed, friends, places, videos, posts, photos);
    }
}