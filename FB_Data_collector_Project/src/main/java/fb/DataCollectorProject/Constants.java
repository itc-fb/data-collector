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
    public static final String AFTER_SCROLL_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";


    // Place list locators

    public static final String MORE_DROPDOWN_LOCATOR_BY_CSS = "ul>li>div[class=\"_6a uiPopover 6-6 9rx _5v-0\"]";
    public static final String PLACE_LOCATOR_BY_CSS = "li>a[data-tab-key=\"map\"]";
    public static final String PLACE_ELEMENTS_LOCATOR_BY_CSS = "li>div>div[class=\"_gx6 _agv\"]>a";


}
