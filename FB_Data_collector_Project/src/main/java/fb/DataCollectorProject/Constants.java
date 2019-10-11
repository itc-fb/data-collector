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

    // User profile locator
    public static final String USER_PROFILE_BUTTON = "#u_0_a > div:nth-child(1) > div:nth-child(1) > div > a";

    // More Section DropDown locator
    public static final String MORE_DROPDOWN_LOCATOR_BY_CSS = "ul[class=\"_6_7 clearfix\"]>:nth-child(6)>div";

    // Friends list locators
    public static final String FRIENDS_SECTION_LOCATOR_BY_CSS = "div[class=\"_70k\"]>ul>:nth-child(3)>a";
    public static final String VISIBLE_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";
    public static final String FRIENDS_COUNT_LOCATOR_BY_CSS = "div[class=\"_70k\"]>ul>:nth-child(3)>a>:nth-child(1)";

    // Place list locators
    public static final String PLACE_SECTION_LOCATOR_BY_CSS = "li>a[data-tab-key=\"map\"]";
    public static final String VISIBLE_PLACES_LOCATOR_BY_CSS = "ul[class=\"uiList _620 _14b9 _509- _4ki\"]>li>div>div>a";
    public static final String PLACES_COUNT_LOCATOR_BY_CSS = "div[class=\"clearfix _1_ca\"]>div>:nth-child(1)>:nth-child(2)";

    // Video list locators
    public static final String VIDEO_SECTION_LOCATOR_BY_CSS = "li>a[data-tab-key=\"videos\"]";
    public static final String VISIBLE_VIDEOS_LOCATOR_BY_CSS = "div[class=\"_5h60 _30f\"]>ul>li>a[class=\"async_saving _400z _2-40 __-q\"]";
    public static final String VIDEO_DESCRIPTION_LOCATOR_BY_CSS = "div[class=\"_5-g_ _3qw\"] >div>div>div>div>div[class=\"_6444 _-pb\"]>div[class=\"_437j\"]>:nth-child(2)>:nth-child(2)>span>div";

    // Post list locators
    public static final String POST_LIST_LOCATOR_BY_CSS = "div[class=\"_5pcr userContentWrapper\"]";
    public static final String POST_TITLE_LOCATOR_BY_CSS = "[class=\"_7tae _14f3 _14f5 _5pbw _5vra\"]";
    public static final String POST_DATE_LOCATOR_BY_CSS = "a[class=\"_5pcq\"]>abbr";
    public static final String POST_MESSAGE_TEXT_BY_CSS = "div[data-testid=\"post_message\"]>p";
    public static final String POST_MAP_ATTACHMENT_IMAGE_LOCATOR_BY_CSS = "div[class=\"_4j7v _4_w3\"]>img";
    public static final String POST_IMAGE_LOCATOR_BY_CSS = "div[class=\"_46-h _4-ep\"]>img";
    public static final String POST_VIDEO_LOCATOR_BY_CSS = "div[class=\"_53j5\"]>video";
    public static final String POST_LINK_LOCATOR_BY_CSS = "div[class=\"_3bjv ellipsis\"]>a";
    public static final String COUNT_OF_PEOPLE_CHECKED_IN_POST_LINK_LOCATOR_BY_CSS = "div[class=\"_1-jc ellipsis\"]>span";
    public static final String PLACE_WHERE_CHECKED_IN_THE_POST_LOCATOR_BY_CSS = "div[class=\"_1-jb ellipsis fsm fwn fcg\"]";
    public static final String POST_TEXT_TITLE_LOCATOR_BY_CSS = "div[class=\"mtm _5pcm\"]>span";
    public static final String POST_TEXT_LOCATOR_BY_CSS = "div[class=\"text_exposed_root\"]>:nth-child(1)";
    public static final String SHARED_POST_LINK_LOCATOR_BY_CSS = "div[class=\"_3ekx _29_4\"]>a";

    // Photo list locators
    public static final String PHOTO_SECTION_LOCATOR_BY_CSS = "li>a[data-tab-key=\"photos\"]";
    public static final String PHOTOS_SECTIONS_LOCATOR_BY_CSS = "div[class=\"_3dc lfloat _ohe _5brz\"]>a";
    public static final String FIRST_PHOTO_LOCATOR_BY_CSS = "div[class=\"tagWrapper\"]";
    public static final String IMAGE_LOCATOR_BY_CSS = "div[class=\"_2-sx\"]>img";
    public static final String IMAGE_DATE_LOCATOR_BY_CSS = "span[id=\"fbPhotoSnowliftTimestamp\"]>a>abbr";
    public static final String IMAGE_TEXT_LOCATOR_BY_CSS = "span[class=\"hasCaption\"]";
    public static final String IMAGE_PLACE_LOCATOR_BY_CSS = "span[class=\"ogTagItem\"]>span>span>a";
    public static final String IMAGE_ATTACHED_PEOPLE_LOCATOR_BY_CSS = "span[class=\"ogTagItem\"]>span>a";
}
