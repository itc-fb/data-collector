package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.JsonKeys;
import dataCollector.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PhotoList {
    public PhotoList(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Constants.PHOTO_SECTION_LOCATOR_BY_CSS)
    private WebElement photoSection;

    @FindBy(css = Constants.PHOTOS_SECTIONS_LOCATOR_BY_CSS)
    private List<WebElement> photosSections;

    @FindBy(css = Constants.FIRST_PHOTO_LOCATOR_BY_CSS)
    private WebElement firstPhotoLocator;

    @FindBy(xpath = Constants.IMAGE_ATTACHED_PEOPLE_FIRST_LOCATOR_BY_XPATH)
    private List<WebElement> attachedPeopleFirstLocator;

    @FindBy(xpath = Constants.IMAGE_ATTACHED_PEOPLE_SECOND_LOCATOR_BY_XPATH)
    private List<WebElement> attachedPeopleSecondLocator;

    @FindBy(css = Constants.IMAGE_LOCATOR_BY_CSS)
    private WebElement nextImageLocator;

    private void photoSectionClick() {
        photoSection.click();
    }

    private void findMyPhotosSectionAndClick() {
        for (WebElement section : photosSections
        ) {
            String findSection = section.getAttribute(Constants.A_ATTRIBUTE_HREF);
            if (findSection.contains(Constants.MY_PHOTO_SECTION_CHECK)) {
                section.click();
            }
        }
    }

    private ArrayList<Map> collectAttachedPeople(List<WebElement> attachedPeopleLocator) {
        ArrayList<Map> attachedPeople = new ArrayList<>();
            for (WebElement person : attachedPeopleLocator
            ) {
                Map<String, String> attachedPerson = new HashMap<>();
                String attachedPersonPageUrl = person.getAttribute(Constants.A_ATTRIBUTE_HREF);
                String attachedPersonName = person.getText();
                attachedPerson.put(JsonKeys.PAGE_URL, attachedPersonPageUrl);
                attachedPerson.put(JsonKeys.NAME, attachedPersonName);
                attachedPeople.add(attachedPerson);
            }
        return attachedPeople;
    }

    private void firstPhotoClick() {
        firstPhotoLocator.click();
    }

    private ArrayList<Map> getPhotosList() throws InterruptedException {
        ArrayList<Map> photoList = new ArrayList<>();
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
            if(attachedPeopleFirstLocator.size() > 0){
                photo.put(JsonKeys.ATTACHED_PEOPLE, collectAttachedPeople(attachedPeopleFirstLocator));
            }else if(attachedPeopleSecondLocator.size() > 0){
                photo.put(JsonKeys.ATTACHED_PEOPLE, collectAttachedPeople(attachedPeopleSecondLocator));
            }else{
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
    }

    public ArrayList<Map> getPhotos() throws InterruptedException {
        Utils.waitByMls(2000);
        photoSectionClick();
        Utils.waitByMls(3000);
        findMyPhotosSectionAndClick();
        firstPhotoClick();
        Utils.waitByMls(3000);
        return getPhotosList();
    }

}
