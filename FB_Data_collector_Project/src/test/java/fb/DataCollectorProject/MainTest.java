package fb.DataCollectorProject;

import fb.DataCollectorProject.Pages.LoginPage;
import fb.DataCollectorProject.Pages.MainPage;
import fb.DataCollectorProject.getData.FriendsList;
import fb.DataCollectorProject.getData.PlacesList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MainTest {
    private static WebDriver driver;

    private void openFacebookPage(){
        Utils.maximizeWindow(driver);
        Utils.doTimeOuts(driver);
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
    public void testMethod() throws InterruptedException {
        openFacebookPage();
        getLoggedToMainPage();
        goToUserProfile();
        Utils.waitByMls(5000);
        getFriendsList();
        goToUserProfile();
        Utils.waitByMls(5000);
        getPlaceList();

        System.out.println(System.getProperty("os.name"));
    }
}
