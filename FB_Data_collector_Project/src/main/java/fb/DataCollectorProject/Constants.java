package fb.DataCollectorProject;

public class Constants {
    // Login data
    public static final String INPUT_LOGIN = "+37491221382";
    public static final String INPUT_PASSWORD = "123456789ll";
    static final String BASE_URL = "https://www.facebook.com/";

    // Login page locators
    public static final String LOGIN_FIELD_LOCATOR_BY_ID = "email";
    public static final String PASSWORD_FIELD_LOCATOR_BY_ID = "pass";
    public static final String SUBMIT_BUTTON_LOCATOR_BY_ID = "loginbutton";

    // Friends list locators
    public static final String FRIENDS_SECTION_LOCATOR_BY_CSS = "div[class=\"_70k\"]>ul>:nth-child(3)>a";
    public static final String VISIBLE_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";
    public static final String FRIENDS_COUNT_LOCATOR_BY_CSS = "div[class=\"_70k\"]>ul>:nth-child(3)>a>:nth-child(1)";

    // User profile locator
    public static final String USER_PROFILE_BUTTON = "#u_0_a > div:nth-child(1) > div:nth-child(1) > div > a";

    // Place list locators
    public static final String MORE_DROPDOWN_LOCATOR_BY_CSS = "ul[class=\"_6_7 clearfix\"]>:nth-child(6)>div";
    public static final String PLACE_SECTION_LOCATOR_BY_CSS = "li>a[data-tab-key=\"map\"]";
    public static final String VISIBLE_PLACES_LOCATOR_BY_CSS = "ul[class=\"uiList _620 _14b9 _509- _4ki\"]>li>div>div>a";
    public static final String PLACES_COUNT_LOCATOR_BY_CSS = "div[class=\"clearfix _1_ca\"]>div>:nth-child(1)>:nth-child(2)";


}
