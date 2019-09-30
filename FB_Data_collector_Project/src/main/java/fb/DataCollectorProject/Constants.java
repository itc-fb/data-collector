package fb.DataCollectorProject;

public class Constants {
    // Login data
    public static final String INPUT_LOGIN = "iccup.ke1io@gmail.com";
    public static final String INPUT_PASSWORD = "1354622a";
    static final String BASE_URL = "https://www.facebook.com/";

    // Login page locators
    public static final String LOGIN_FIELD_LOCATOR_BY_ID = "email";
    public static final String PASSWORD_FIELD_LOCATOR_BY_ID = "pass";
    public static final String SUBMIT_BUTTON_LOCATOR_BY_ID = "loginbutton";

    // Friends list locators
    public static final String FRIENDS_SECTION_LOCATOR_BY_CSS = "div[class=\"_70k\"]>ul>:nth-child(3)";
    public static final String VISIBLE_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";
    public static final String AFTER_SCROLL_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";

}
