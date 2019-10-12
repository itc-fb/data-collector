package dataCollector;

import dataCollector.getData.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class MainTest {
    private static WebDriver driver;

    private void openFacebookPage(){
        Utils.maximizeWindow(driver);
        Utils.doTimeOuts(driver, 30);
        Utils.getUrl(driver);
    }

    private void getLoggedToMainPage(){
        new dataCollector.pages.LoginPage(driver).goToMainPage();
    }

    private void goToUserProfile(){
        new dataCollector.pages.MainPage(driver).userProfileButtonClick();
    }

    private void getFriendsList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new FriendsList(driver).getFriendsList();
    }

    private void getPlaceList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PlacesList(driver).getPlaces();
    }

    private void getVideosList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new VideosList(driver).getVideos();
    }

    private void getUserPostList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PostList(driver).getUserPosts();
    }

    private void getPhotoList() throws InterruptedException {
        goToUserProfile();
        Utils.waitByMls(5000);
        new PhotoList(driver).getPhotos();
    }

    @BeforeClass
    static public void beforeC() {
        Utils.initializeDriver();
        driver = Utils.driver;
    }

    @AfterClass
    static public void afterC() {
        Utils.closeDriver(driver);
    }

    @Test
    public void testMethod() throws InterruptedException, IOException {
        openFacebookPage();
        getLoggedToMainPage();
        getFriendsList();
        getPlaceList();
        getVideosList();
        getUserPostList();
        getPhotoList();

        System.out.println(System.getProperty("os.name"));
    }
}
