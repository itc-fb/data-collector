package fb.DataCollectorProject.getData;

import fb.DataCollectorProject.Constants;
import fb.DataCollectorProject.Pages.BasePage;
import fb.DataCollectorProject.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;


public class PlacesList extends BasePage {


    public PlacesList(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }



    @FindBy(css = Constants.MORE_DROPDOWN_LOCATOR_BY_CSS)
    private WebElement moreDropDownLocator;


    @FindBy(css = Constants.PLACE_LOCATOR_BY_CSS)
    private WebElement placeLocator;


    @FindBy(css = Constants.PLACE_ELEMENTS_LOCATOR_BY_CSS)
    private ArrayList<WebElement> placeElements;

    private void placeLocatorClick(){
        placeLocator.click();
    }

    private ArrayList<String> getPlacesNames() {
        ArrayList<String> places = new ArrayList<>();
        for (WebElement placeElement: placeElements
        ) {
            places.add(placeElement.getAttribute("title"));
        }
        return places;
    }

    public void getPlaces(){
        Actions actions = new Actions(Utils.driver);
        actions.moveToElement(moreDropDownLocator).perform();
       // Utils.moveToElement(moreDropDownLocator);
        placeLocatorClick();
        System.out.println(getPlacesNames());
    }


}
