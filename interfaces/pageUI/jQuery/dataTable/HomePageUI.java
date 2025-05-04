package pageUI.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGINATION_ACTIVE_PAGE_BY_NUMBER = "xpath=//li/a[@class='qgrd-pagination-page-link active']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text() = '%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGINATION = "css=ul.qgrd-pagination-ul>li.qgrd-pagination-page";
	public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]/a";
	public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";

	public static final String COLUMN_INDEX_BY_NAME = "XPATH=//th[text()='%s']/preceding-sibling::th";
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "XPATH=//tbody/tr[%s]/td[%s]/input";
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "XPATH=//tbody/tr[%s]/td[%s]//select";
	public static final String LOAD_BUTTON = "css=button#load";
	public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "XPATH=//tbody/tr[%s]/td[%s]//input";
	public static final String ICON_NAME_BY_ROW_NUMBER = "xpath=//tbody/tr[%s]//button[@title='%s']";

	public static final String ACCOUNT_LINK = "xpath=//div/a//span[@class='label' and text()='Account']";
	public static final String LINK_FORM_THE_ACCOUNT = "xpath=//div/ul/li/a[text()='%s']";
	public static final String FIELD_TO_INPUT_IN_ACCOUNT_PAGE = "xpath=//ul//input[@title='%s']";
	public static final String REGISTER_BUTTON = "XPATH=//button[@title='Register']";
	public static final String SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//li/span[contains(text(), 'Thank you for registering with Main Website Store.')]";

	public static final String FIELD_TO_INPUT_IN_ADMIN_PAGE = "xpath=//input[@id='%s']";
	public static final String LOGIN_BUTTON = "XPATH=//input[@title='Login']";
	public static final String CLOSE_BUTTON = "xpath=//span[text()='close']";

	public static final String TEXTBOX_BY_ROW_INDEX = "XPATH=//table[@class='data']//tbody/tr[1]//td[%s]";
	public static final String LOGOUT_BUTTON = "XPATH=//a[@class='link-logout']";

}
