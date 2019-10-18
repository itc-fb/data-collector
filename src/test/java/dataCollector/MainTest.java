package dataCollector;

import dataCollector.getData.*;
import dataCollector.pages.LoginPage;
import dataCollector.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

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

    private void getFriendsList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new FriendsList(Utils.driver).getFriendsList();
    }

    private void getPlaceList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PlacesList(Utils.driver).getPlaces();
    }

    private void getVideosList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new VideosList(Utils.driver).getVideos();
    }

    private void getUserPostList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PostList(Utils.driver).getUserPosts();
    }

    private void getPhotoList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PhotoList(Utils.driver).getPhotos();
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
        getFriendsList();
//        getPlaceList();
//        getVideosList();
//        getUserPostList();
//        getPhotoList();

        System.out.println(System.getProperty("os.name"));
    }
}
