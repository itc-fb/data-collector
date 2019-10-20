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

    private void openFacebookPage() {
        Utils.maximizeWindow(Utils.driver);
        Utils.getUrl(Utils.driver);
    }

    private void getLoggedToMainPage() {
        new LoginPage().goToMainPage();
    }

    private void goToUserProfile() {
        new MainPage().userProfileButtonClick();
    }

    private ArrayList<String> getFriendsList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        return new FriendsList(Utils.driver).getFriendsList();
    }

    private ArrayList<String> getPlaceList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        return new PlacesList(Utils.driver).getPlaces();
    }

    private ArrayList<Map> getVideosList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        return new VideosList(Utils.driver).getVideos();
    }

    private ArrayList<Map> getUserPostList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        return new PostList(Utils.driver).getUserPosts();
    }

    private ArrayList<Map> getPhotoList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        return new PhotoList(Utils.driver).getPhotos();
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

        openFacebookPage();
        getLoggedToMainPage();
        ArrayList<String> friends = getFriendsList();
        ArrayList<String> places = getPlaceList();
        ArrayList<Map> videos = getVideosList();
        ArrayList<Map> posts = getUserPostList();
        ArrayList<Map> photos = getPhotoList();
        Utils.writeToJson(friends, places, videos, posts, photos);


        System.out.println(System.getProperty("os.name"));
    }
}
