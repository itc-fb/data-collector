package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

    @FindBy(css = Constants.IMAGE_ATTACHED_PEOPLE_LOCATOR_BY_CSS)
    private List<WebElement> attachedPeopleLocator;



    @FindBy(css = Constants.IMAGE_LOCATOR_BY_CSS)
    private WebElement nextImageLocator;

    private void photoSectionClick() {
        photoSection.click();
    }

    private void findMyPhotosSectionAndClick() {

        for (WebElement section : photosSections
        ) {
            String findSection = section.getAttribute("href");
            if (findSection.contains("photos_all")) {
                section.click();
            }
        }

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
                firstImageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator, "src");
        while(true){

            Map<String, Object> photo = new HashMap<>();
            ArrayList<Map> attachedPeople = new ArrayList<>();

            String imageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator,"src"),
                    imageDate = Utils.getElementAttributeValueByCss(imageDateLocator, "title"),
                    imageText = Utils.getElementInnerTextByCss(imageTextLocator),
                    imagePlace = Utils.getElementAttributeValueByCss(imagePlaceLocator, "href");
            for (WebElement person: attachedPeopleLocator
                 ) {
                try {
                    Map<String, String> attachedPerson = new HashMap<>();
                    String attachedPersonPageUrl = person.getAttribute("href");
                    String attachedPersonName = person.getText();
                    attachedPerson.put("pageUrl", attachedPersonPageUrl);
                    attachedPerson.put("name", attachedPersonName);
                    attachedPeople.add(attachedPerson);
                }catch (NoSuchElementException | StaleElementReferenceException e){
                    attachedPeople.add(null);
                }
            }

            photo.put("url", imageUrl);
            photo.put("date", imageDate);
            photo.put("text", imageText);
            photo.put("place", imagePlace);
            photo.put("attachedPeople", attachedPeople);

            photoList.add(photo);

            nextImageLocator.click();
            Utils.waitByMls(3000);
            String checkImageUrl = Utils.getElementAttributeValueByCss(imageUrlLocator,"src");
            assert firstImageUrl != null;
            if(firstImageUrl.equals(checkImageUrl)){
                break;
            }

        }

//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(photoList));
        return photoList;
    }

    public void getPhotos() throws InterruptedException {
        Utils.waitByMls(2000);
        photoSectionClick();
        Utils.waitByMls(3000);
        findMyPhotosSectionAndClick();
        firstPhotoClick();
        Utils.waitByMls(3000);
        System.out.println(getPhotosList());
    }

}
