package pageUI.nopCommerce.user;

public class BasePageNopCommerceUI {
	public static final String ADDRESSES_LINK = "xpath=//li[@class='customer-addresses inactive']/a[text()='Addresses']";
	public static final String ORDERS_LINK = "xpath=//li[@class='customer-orders inactive']/a[text()='Orders']";
	public static final String DOWNLOADABLE_PRODUCTS_LINK = "xpath=//li[@class='downloadable-products inactive']/a[text()='Downloadable products']";
	public static final String BACK_IN_STOCK_LINK = "xpath=//li[@class='back-in-stock-subscriptions inactive']/a[text()='Back in stock subscriptions']";
	public static final String REWARD_POINTS_LINK = "xpath=//li[@class='reward-points inactive']/a[text()='Reward points']";
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_PAGE = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";

	public static final String CHANGE_PASSWORD_LINK = "xpath=//li[@class='change-password inactive']/a[text()='Change password']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//li[@class='customer-reviews inactive']/a[text()='My product reviews']";
	public static final String CUSTOMER_INFO_LINK = "CSS=li[class='customer-info active']>a";
	public static final String CUSTOMER_INFO_HEADER = "xpath=//div[@class='page-title']/h1[text()='My account - Customer info']";
	public static final String LOGOUT_LINK_AT_USER_PAGE = "css=a[class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN_PAGE = "XPATH=//a[text()='Logout']";

}
