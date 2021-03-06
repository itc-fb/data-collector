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
    /**
     * Initialize page factory elements.
     */
    public VideosList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Finds visible videos locators before scroll.
     */
    @FindBy(css = Constants.VISIBLE_VIDEOS_LOCATOR_BY_CSS)
    private List<WebElement> visibleVideosLocator;

    /**
     * Finds video description after click on a single video.
     */
    @FindBy(css = Constants.VIDEO_DESCRIPTION_LOCATOR_BY_CSS)
    private WebElement videoDescriptionLocator;

    /**
     * Goes to videos page by url.
     */
    private void goToVideoPage() {
        String profileUrl = Utils.driver.findElement(By.cssSelector(Constants.PROFILE_URL_LOCATOR_BY_CSS)).getAttribute(Constants.A_ATTRIBUTE_HREF);
        String newUrl = profileUrl.concat(Constants.VIDEOS);
        Utils.driver.get(newUrl);
    }

    /**
     * Scroll on videos page.
     * If all videos are loaded, start collecting their data.
     * After data add to an array list.
     * Return array list.
     */
    public ArrayList<Map> getVideoList() {
        goToVideoPage();
        Utils.waitByMls(3000);
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
                Utils.scrollToLocationWithWait(location);
                Utils.waitByMls(3000);
            }
        }
        return videoList;
    }
}