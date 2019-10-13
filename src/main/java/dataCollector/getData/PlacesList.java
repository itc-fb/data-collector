package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.pages.BasePage;
import dataCollector.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;


public class PlacesList extends BasePage {

    public PlacesList(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = Constants.MORE_DROPDOWN_LOCATOR_BY_CSS)
    private WebElement moreDropDownLocator;


    @FindBy(css = Constants.PLACE_SECTION_LOCATOR_BY_CSS)
    private WebElement placeSectionLocator;


    @FindBy(css = Constants.VISIBLE_PLACES_LOCATOR_BY_CSS)
    private List<WebElement> visiblePlaces;

    @FindBy(css = Constants.PLACES_COUNT_LOCATOR_BY_CSS)
    private WebElement placesCountLocator;

    private void placeLocatorClick() {
        placeSectionLocator.click();
    }

    private ArrayList<String> getPlacesNames() throws InterruptedException {
        ArrayList<String> places = new ArrayList<>();
        if (visiblePlaces.size() > 0) {
            WebElement lastPlace;
            int location;
            int count = Integer.parseInt(placesCountLocator.getText());

            while (visiblePlaces.size() <= count) {
                lastPlace = visiblePlaces.get(visiblePlaces.size() - 1);
                location = lastPlace.getLocation().y;
                Utils.scrollByLocation(location);
                Utils.waitByMls(3000);

                if (visiblePlaces.size() == count) {
                    String placeAttributeTitle = "title";
                    for (WebElement placeElement : visiblePlaces
                    ) {
                        places.add(placeElement.getAttribute(placeAttributeTitle));
                    }
                    break;
                }
            }
        } else {
            places.add(null);
        }
        return places;
    }

    public void getPlaces() throws InterruptedException {
        Utils.moveToElement(moreDropDownLocator);
        Utils.waitByMls(1000);
        placeLocatorClick();
        System.out.println(getPlacesNames());
    }
}
