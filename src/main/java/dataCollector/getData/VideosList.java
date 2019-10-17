package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;


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
        ArrayList<Map> videoList = new ArrayList<>();
        if(visibleVideosLocator.size() > 0) {
            WebElement lastVideo;
            int location = 0;
            while (true) {
                lastVideo = visibleVideosLocator.get(visibleVideosLocator.size() - 1);
                if (location == lastVideo.getLocation().y) {
                    for (WebElement videoInfo : visibleVideosLocator) {
                        Utils.waitByMls(3000);
                        Map<String, String> video = new HashMap<>();
                        String videoUrl = videoInfo.getAttribute("href");
                        videoInfo.click();
                        try {
                            String videoDescription = videoDescriptionLocator.getText();
                            video.put("url", videoUrl);
                            video.put("description ", videoDescription);
                        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
                            video.put("url", videoUrl);
                            video.put("description ", null);
                        }

                        Utils.driver.navigate().back();
                        videoList.add(video);
                    }
                    break;
                }
                location = lastVideo.getLocation().y;
                Utils.scrollByLocation(location);
                Utils.waitByMls(3000);
            }
        }
        else{
            videoList.add(null);
        }
        return videoList;
    }

    public void getVideos() throws InterruptedException {
        Utils.waitByMls(2000);
        Utils.moveToElement(moreDropDownLocator);
        Utils.waitByMls(2000);
        videoSectionClick();
        System.out.println(getVideosList());
    }

}
