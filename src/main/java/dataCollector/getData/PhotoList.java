package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoList {
    /**
     *Initialize page factory elements.
     */
    public PhotoList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Finds the first photo locator on photos page.
     */
    @FindBy(css = Constants.FIRST_PHOTO_LOCATOR_BY_CSS)
    private List<WebElement> firstPhotoLocator;

    /**
     * Finds the attached people locators on a single photo.
     */
    @FindBy(xpath = Constants.IMAGE_ATTACHED_PEOPLE_FIRST_LOCATOR_BY_XPATH)
    private List<WebElement> attachedPeopleFirstLocator;
    @FindBy(xpath = Constants.IMAGE_ATTACHED_PEOPLE_SECOND_LOCATOR_BY_XPATH)
    private List<WebElement> attachedPeopleSecondLocator;

    /**
     * Finds the single photo locator.
     */
    @FindBy(css = Constants.IMAGE_LOCATOR_BY_CSS)
    private WebElement nextImageLocator;

    /**
     *Goes to photos page by url.
     */
    private void goToPhotosPage() {
        String profileUrl = Utils.driver.findElement(By.cssSelector(Constants.PROFILE_URL_LOCATOR_BY_CSS)).getAttribute(Constants.A_ATTRIBUTE_HREF);
        String newUrl = profileUrl.concat(Constants.PHOTOS);
        Utils.driver.get(newUrl);
    }

    /**
     * Collect attached people data from single photo.
     * Return data in array list.
     */
    private ArrayList<Map> collectAttachedPeople(List<WebElement> attachedPeopleLocator) {
        ArrayList<Map> attachedPeople = new ArrayList<>();
        attachedPeopleLocator.forEach((person) -> {
            Map<String, String> attachedPerson = new HashMap<>();
            String attachedPersonPageUrl = person.getAttribute(Constants.A_ATTRIBUTE_HREF);
            String attachedPersonName = person.getText();
            attachedPerson.put(JsonKeys.PAGE_URL, attachedPersonPageUrl);
            attachedPerson.put(JsonKeys.NAME, attachedPersonName);
            attachedPeople.add(attachedPerson);
        });
        return attachedPeople;
    }

    /**
     * Click on first photo if it exists.
     */
    private void firstPhotoClick() {
        firstPhotoLocator.get(0).click();
    }

    /**
     * Click on first photo if it exists.
     * Collects all required data from photo, and click next photo and do same steps.
     * After data add to an array list.
     * Return array list.
     */
    public ArrayList<Map> getPhotoList() {
        goToPhotosPage();
        Utils.waitByMls(3000);
        ArrayList<Map> photoList = new ArrayList<>();
        try {
            firstPhotoClick();
            Utils.waitByMls(3000);
            String imageUrlLocator = Constants.IMAGE_LOCATOR_BY_CSS,
                    imageDateLocator = Constants.IMAGE_DATE_LOCATOR_BY_CSS,
                    imageTextLocator = Constants.IMAGE_TEXT_LOCATOR_BY_CSS,
                    imagePlaceLocator = Constants.IMAGE_PLACE_LOCATOR_BY_CSS,
                    firstImageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator, Constants.IMG_ATTRIBUTE_SRC);
            while (true) {
                Map<String, Object> photo = new HashMap<>();
                String imageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator, Constants.IMG_ATTRIBUTE_SRC),
                        imageDate = Utils.getElementAttributeValueByCss(imageDateLocator, Constants.ABBR_ATTRIBUTE_TITLE),
                        imageText = Utils.getElementInnerTextByCss(imageTextLocator),
                        imagePlace = Utils.getElementAttributeValueByCss(imagePlaceLocator, Constants.A_ATTRIBUTE_HREF);
                if (attachedPeopleFirstLocator.size() > 0) {
                    photo.put(JsonKeys.ATTACHED_PEOPLE, collectAttachedPeople(attachedPeopleFirstLocator));
                } else if (attachedPeopleSecondLocator.size() > 0) {
                    photo.put(JsonKeys.ATTACHED_PEOPLE, collectAttachedPeople(attachedPeopleSecondLocator));
                } else {
                    photo.put(JsonKeys.ATTACHED_PEOPLE, null);
                }
                photo.put(JsonKeys.IMAGE_URL, imageUrl);
                photo.put(JsonKeys.IMAGE_DATE, imageDate);
                photo.put(JsonKeys.TEXT, imageText);
                photo.put(JsonKeys.PLACE, imagePlace);
                photoList.add(photo);
                nextImageLocator.click();
                Utils.waitByMls(3000);
                String checkImageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator, Constants.IMG_ATTRIBUTE_SRC);
                assert firstImageUrl != null;
                if (firstImageUrl.equals(checkImageUrl)) {
                    break;
                }
            }
            return photoList;
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException | IndexOutOfBoundsException e) {
            return photoList;
        }
    }
}