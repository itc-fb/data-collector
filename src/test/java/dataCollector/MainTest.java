package dataCollector;

import dataCollector.getData.*;
import dataCollector.pages.LoginPage;
import dataCollector.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainTest {

    private void getLoggedToMainPage() {
        new LoginPage().logIn();
    }

    @Parameters({"browser"})
    @BeforeClass
    static public void setupDriver(String browser) {
        Utils.initDriver(browser);
    }

    @AfterClass
    static public void closeDriver() {
        Utils.closeDriver(Utils.driver);
    }

    @Test
    public void testMethod() throws InterruptedException, IOException {
        getLoggedToMainPage();
        ArrayList<Map> feed = new FeedList(Utils.driver).getFeedList();
        ArrayList<String> friends = new FriendsList(Utils.driver).getFriendsList();
        ArrayList<String> places = new PlacesList(Utils.driver).getPlaceList();
        ArrayList<Map> videos = new VideosList(Utils.driver).getVideoList();
        ArrayList<Map> posts = new PostList(Utils.driver).getUserPostList();
        ArrayList<Map> photos = new PhotoList(Utils.driver).getPhotoList();
        Utils.writeToJson(feed, friends, places, videos, posts, photos);

        System.out.println(System.getProperty("os.name"));
    }
}
