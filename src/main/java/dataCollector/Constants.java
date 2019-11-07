package dataCollector;

public class Constants {
    static final String BASE_URL = "https://www.facebook.com/";

    //Parameters
    static final String BROWSER_NAME = "browser";
    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    // Login page locators
    public static final String LOGIN_FIELD_LOCATOR_BY_CSS = "input[name=\"email\"]";
    public static final String PASSWORD_FIELD_LOCATOR_BY_CSS = "input[name=\"pass\"]";
    public static final String SUBMIT_INPUT_BUTTON_LOCATOR_BY_CSS = "input[type=\"submit\"]";
    public static final String SUBMIT_BUTTON_LOCATOR_BY_CSS = "button[type=\"submit\"]";
    public static final String PROFILE_URL_LOCATOR_BY_CSS = "div[class=\"_1k67 _cy7\"]>a";

    // Friends list locators
    public static final String VISIBLE_FRIENDS_LOCATOR_BY_CSS = "div>a[class=\"_5q6s _8o _8t lfloat _ohe\"]>img";

    // Place list locators
    public static final String VISIBLE_PLACES_LOCATOR_BY_CSS = "ul[class=\"uiList _620 _14b9 _509- _4ki\"]>li>div>div>a";
    public static final String PLACES_COUNT_LOCATOR_BY_CSS = "div[class=\"clearfix _1_ca\"]>div>:nth-child(1)>:nth-child(2)";

    // Video list locators
    public static final String VISIBLE_VIDEOS_LOCATOR_BY_CSS = "div[class=\"_5h60 _30f\"]>ul>li>a[class=\"async_saving _400z _2-40 __-q\"]";
    public static final String VIDEO_DESCRIPTION_LOCATOR_BY_CSS = "div[class=\"_5-g_ _3qw\"] >div>div>div>div>div[class=\"_6444 _-pb\"]>div[class=\"_437j\"]>:nth-child(2)>:nth-child(2)>span>div";
    static final String DATE_FORMAT = "yyyy-MM-dd";

    // Post list locators
    public static final String POST_LIST_LOCATOR_BY_CSS = "div[class=\"_5pcr userContentWrapper\"]";
    public static final String POST_TITLE_LOCATOR_BY_CSS = "[class=\"_7tae _14f3 _14f5 _5pbw _5vra\"]";
    public static final String POST_DATE_LOCATOR_BY_CSS = "a[class=\"_5pcq\"]>abbr";
    public static final String POST_MESSAGE_TEXT_BY_CSS = "div[data-testid=\"post_message\"]>p";
    public static final String POST_MAP_ATTACHMENT_IMAGE_LOCATOR_BY_CSS = "div[class=\"_4j7v _4_w3\"]>img";
    public static final String POST_IMAGE_LOCATOR_BY_CSS = "div[class=\"_46-h _4-ep\"]>img";
    public static final String POST_LINK_LOCATOR_BY_CSS = "div[class=\"_3bjv ellipsis\"]>a";
    public static final String COUNT_OF_PEOPLE_CHECKED_IN_POST_LINK_LOCATOR_BY_CSS = "div[class=\"_1-jc ellipsis\"]>span";
    public static final String PLACE_WHERE_CHECKED_IN_THE_POST_LOCATOR_BY_CSS = "div[class=\"_1-jb ellipsis fsm fwn fcg\"]";
    static final int POST_COUNT = 1000/10;

    // Photo list locators
    public static final String FIRST_PHOTO_LOCATOR_BY_CSS = "div[class=\"tagWrapper\"]";
    public static final String IMAGE_LOCATOR_BY_CSS = "div[class=\"_2-sx\"]>img";
    public static final String IMAGE_DATE_LOCATOR_BY_CSS = "span[id=\"fbPhotoSnowliftTimestamp\"]>a>abbr";
    public static final String IMAGE_TEXT_LOCATOR_BY_CSS = "span[class=\"hasCaption\"]";
    public static final String IMAGE_PLACE_LOCATOR_BY_CSS = "span[class=\"fcg\"]>:nth-last-child(1)>a";
    public static final String IMAGE_ATTACHED_PEOPLE_FIRST_LOCATOR_BY_XPATH = "//*[@class=\"ogTagItem\"]/span/a";
    public static final String IMAGE_ATTACHED_PEOPLE_SECOND_LOCATOR_BY_XPATH = "//*[@class=\"fcg\"]/span[@class=\"fbPhotoTagListTag withTagItem tagItem\"]/a";

    //Feed locators
    public static final String ALL_FEED_LOCATOR_BY_CSS = "div[role=\"feed\"]>:nth-child(3)";
    public static final String FEED_LIST_LOCATOR_BY_CSS = "div[class=\"_5pcr userContentWrapper\"]>div[class=\"_1dwg _1w_m _q7o\"]>:nth-child(2)";
    public static final String FEED_POST_TITLE_LOCATOR_BY_CSS = "[class=\"_7tae _14f3 _14f5 _5pbw _5vra\"]";
    public static final String FEED_POST_MESSAGE_LOCATOR_BY_CSS = "div[data-testid=\"post_message\"]";
    static final String FEED_POST_IMAGE_LOCATOR_FIRST_BY_CSS = "div[class=\"uiScaledImageContainer _6m5 fbStoryAttachmentImage\"]>img";
    static final String FEED_POST_IMAGE_LOCATOR_SECOND_BY_CSS = "div[class=\"uiScaledImageContainer _517g\"]>img";
    public static final String FEED_POST_SHARED_LINK_BY_CSS = "a[class=\"_52c6\"]";
    public static final String FEED_POST_SHARED_TEXT_BY_CSS = "div[class=\"text_exposed_root\"]>p";

    // Element Attribute name
    public static final String IMG_ATTRIBUTE_ARIA_LABEL = "aria-label";
    public static final String ABBR_ATTRIBUTE_TITLE = "title";
    public static final String IMG_ATTRIBUTE_SRC = "src";
    public static final String A_ATTRIBUTE_HREF = "href";
    public static final String A_ATTRIBUTE_TITLE = "title";
    public static final String ATTRIBUTE_INNER_TEXT = "innerText";
    public static final String TAG_NAME_P = "p";

    // String to concat to url for photos, friends, videos, places pages.
    public static final String VIDEOS = "/videos";
    public static final String PHOTOS = "/photos_all";
    public static final String FRIENDS = "/friends";
    public static final String PLACES = "/map";

    //Browser names
    static final String CHROME = "chrome";
    static final String EDGE = "edge";
    static final String SAFARI = "safari";

}


