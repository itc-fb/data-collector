package dataCollector.getData;

import dataCollector.Constants;
import dataCollector.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;


public class PlacesList {

    public PlacesList(WebDriver driver) {
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
        Utils.waitByMls(5000);
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
                    for (WebElement placeElement : visiblePlaces
                    ) {
                        places.add(placeElement.getAttribute(Constants.A_ATTRIBUTE_TITLE));

                    }
                    break;
                }
            }
        } else {
            places.add(null);
        }
        return places;
    }

    public ArrayList<String> getPlaces() throws InterruptedException {
        Utils.moveToElement(moreDropDownLocator);
        Utils.waitByMls(2000);
        placeLocatorClick();
        return getPlacesNames();
    }
}
