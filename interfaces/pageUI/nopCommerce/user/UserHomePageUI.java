package pageUI.nopCommerce.user;

public class UserHomePageUI {
	public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account' and text()='My account']";
	public static final String REGISTER_LINK = "Css=a.ico-register";
	public static final String LOGIN_LINK = "css=a.ico-login";
	public static final String DYNAMIC_HEADER_LINK_NAME = "XPATH=//a[@class='ico-%s']";
	public static final String TOP_MENU_IN_THE_HOMEPAGE = "xpath=//ul[@class='top-menu notmobile']//a[text()='%s ']";
	public static final String LINK_NAME_IN_THE_HOMEPAGE = "xpath=//a[text()='%s']";
	public static final String SHARING_LINK = "xpath=//a[@class='share-link']";

	public static final String CONTINUE_BUTTON = "css=a[class='button-1 register-continue-button']";
	public static final String BUTTON_IN_HOMEPAGE = "xpath=//div[@class='%s']//button[text()='%s']";
	public static final String CLOSE_BUTTON = "css=span.close";
	public static final String REMOVE_BUTTON = "XPATH=//div[@class='table-wrapper']//button[@name='updatecart']";
	public static final String PRODUCT_ITEM_BUTTON = "XPATH=//div[@class='item-box'][%s]//button[text()='%s']";
	public static final String WISHLIST_BUTTON = "xpath=//div[@class='overview-buttons']//button[text()='Add to wishlist']";
	public static final String ADD_TO_CART_BUTTON = "xpath=//div[contains(@class,'button')]//button[text()='Add to cart']";

	public static final String TEXTBOX_IN_THE_HOMEPAGE = "xpath=//%s[@id='%s']";
	public static final String CHECKBOX_IN_HOME_PAGE = "xpath=//input[@name='%s' and @type='checkbox']";
	public static final String DROPDOWN_LIST = "xpath=//select[@id='%s']";
	public static final String REVIEW_RATING = "xpath=//div[@class='review-rating']//input[@value='%s']";

	public static final String SUCCESS_MESSAGE = "css=p.content";
	public static final String ERROR_MESSAGE = "XPATH=//div[@class='search-results']//div[2]/div";

	public static final String PRODUCT_RESULT = "XPATH=//h2//a";
	public static final String PAGE_TITLE = "xpath=//div[@class='page-title']/h1";
	public static final String PAGE_BODY = "XPATH=//div[@class='page-body']/div";

	public static final String PRODUCT_IN_CART = "xpath=//a[@class='product-name']";
	public static final String HEADER_LOGO = "css=div[class='header-logo']>a";
	public static final String PRODUCT_NAME_IN_COMPARE_PAGE = "xpath=//tr[@class='product-name']//a";
	public static final String REMOVE_BUTTON_IN_COMPARE_PAGE = "xpath=//div[@class='table-wrapper']//button[text()='Remove']";
	public static final String PRICE_IN_COMPARE_PAGE = "xpath=//tr[@class='product-price']//td[@style]";

}
