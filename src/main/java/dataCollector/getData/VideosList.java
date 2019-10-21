package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideosList {
    public VideosList(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.MORE_DROPDOWN_LOCATOR_BY_CSS)
    private WebElement moreDropDownLocator;

    @FindBy(css = Constants.VIDEO_SECTION_LOCATOR_BY_CSS)
    private WebElement videoSectionLocator;

    @FindBy(css = Constants.VISIBLE_VIDEOS_LOCATOR_BY_CSS)
    private List<WebElement> visibleVideosLocator;

    @FindBy(css = Constants.VIDEO_DESCRIPTION_LOCATOR_BY_CSS)
    private WebElement videoDescriptionLocator;


    private void videoSectionClick() {
        videoSectionLocator.click();
    }

    private ArrayList<Map> getVideosList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(Utils.driver, 5);
        ArrayList<Map> videoList = new ArrayList<>();
        Utils.waitByMls(5000);
        if (visibleVideosLocator.size() > 0) {
            WebElement lastVideo;
            int location = 0;
            while (true) {
                lastVideo = visibleVideosLocator.get(visibleVideosLocator.size() - 1);
                if (location == lastVideo.getLocation().y) {
                    for (WebElement videoInfo : visibleVideosLocator) {
                        Map<String, String> video = new HashMap<>();
                        String videoUrl = videoInfo.getAttribute(Constants.A_ATTRIBUTE_HREF);
                        videoInfo.click();
                        WebElement videoDescriptionLocator;
                        String videoDescription;
                        try {
                            videoDescriptionLocator = wait.until(
                                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.VIDEO_DESCRIPTION_LOCATOR_BY_CSS)));
                            videoDescription = videoDescriptionLocator.getText();
                        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
                            videoDescription = null;
                        }
                        video.put(JsonKeys.VIDEO_URL, videoUrl);
                        video.put(JsonKeys.DESCRIPTION, videoDescription);
                        Utils.waitByMls(2000);
                        Utils.driver.navigate().back();
                        videoList.add(video);
                    }
                    break;
                }
                location = lastVideo.getLocation().y;
                Utils.scrollByLocation(location);
                Utils.waitByMls(3000);
            }
        } else {
            videoList.add(null);
        }
        return videoList;
    }

    public ArrayList<Map> getVideos() throws InterruptedException {
        Utils.moveToElement(moreDropDownLocator);
        Utils.waitByMls(3000);
        videoSectionClick();
        return getVideosList();
    }

}
