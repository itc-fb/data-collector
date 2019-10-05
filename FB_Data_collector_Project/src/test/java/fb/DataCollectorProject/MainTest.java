package fb.DataCollectorProject;

import fb.DataCollectorProject.Pages.LoginPage;
import fb.DataCollectorProject.Pages.MainPage;
import fb.DataCollectorProject.getData.*;
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
        new LoginPage(driver).goToMainPage();
    }

    private void goToUserProfile(){
        new MainPage(driver).userProfileButtonClick();
    }

    private void getFriendsList() throws InterruptedException {
        new FriendsList(driver).getFriendsList();
    }

    private void getPlaceList() throws InterruptedException {
        new PlacesList(driver).getPlaces();
    }

    private void getVideosList() throws InterruptedException {
        new VideosList(driver).getVideos();
    }

    private void getUserPostList() throws InterruptedException {
        new PostList(driver).getUserPosts();
    }

    private void getPhotoList() throws InterruptedException {
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
        goToUserProfile();
        Utils.waitByMls(5000);
//        getFriendsList();
//        goToUserProfile();
//        Utils.waitByMls(5000);
//        getPlaceList();
//        goToUserProfile();
//        Utils.waitByMls(5000);
//        getVideosList();
//        goToUserProfile();
//        Utils.waitByMls(5000);
//        getUserPostList();
          getPhotoList();

        System.out.println(System.getProperty("os.name"));
    }
}
