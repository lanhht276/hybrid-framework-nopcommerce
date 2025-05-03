package pageObjects.nopCommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageUI.nopCommerce.user.BasePageNopCommerceUI;
import pageUI.nopCommerce.user.UserAddressesPageUI;
import pageUI.nopCommerce.user.UserChangePasswordPageUI;
import pageUI.nopCommerce.user.UserHomePageUI;
import pageUI.nopCommerce.user.UserRegisterPageUI;

public class UserHomePageObject extends BasePage{
	private WebDriver driver;
	 
	public UserHomePageObject(WebDriver driver) {
		 this.driver = driver;	 
	 }
	
	
	public UserRegisterPageObject openRegisterPage() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK );
		clickToElement(driver, UserHomePageUI.REGISTER_LINK );
		//return new RegisterPageObject(driver);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK );
		clickToElement(driver, UserHomePageUI.LOGIN_LINK );
		//return new LoginPageObject(driver);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
	public boolean isMyAccountLinkDisplayed() {
		waitForAllElementInvisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isControlDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}
	public UserCustomerInfoPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK );
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK );
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	public void clickToContinueButton() {
		waitForElementClickable(driver, UserHomePageUI.CONTINUE_BUTTON);
		clickToElement(driver, UserHomePageUI.CONTINUE_BUTTON);
	}
	public UserCustomerInfoPageObject clickToLinkName(String linkName) {
		waitForElementClickable(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE, linkName);
		clickToElement(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE, linkName);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	public boolean isHeaderNameDisplayed(String linkName) {
		waitForAllElementVisible(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_NAME,linkName);
		return isControlDisplayed(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_NAME, linkName);
	}
	public boolean isLinkNameDisplayed(String linkName) {
		waitForAllElementVisible(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE,linkName);
		return isControlDisplayed(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE, linkName);
	}
	public void clickToProductInHomePage(String productName) {
		waitForAllElementVisible(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE, productName);
		clickToElement(driver, UserHomePageUI.LINK_NAME_IN_THE_HOMEPAGE, productName);
	}
	public void inputToTextbox(String type, String textbox, String value) {
		waitForAllElementVisible(driver, UserHomePageUI.TEXTBOX_IN_THE_HOMEPAGE, type, textbox);
		sendKeyToElement(driver, UserHomePageUI.TEXTBOX_IN_THE_HOMEPAGE, value,type, textbox);
	}
	public void clickToRating(String value) {
		waitForAllElementVisible(driver, UserHomePageUI.REVIEW_RATING, value);
		clickToElement(driver, UserHomePageUI.REVIEW_RATING, value);
	}
	public void clickToButon(String className , String button) {
		waitForAllElementVisible(driver, UserHomePageUI.BUTTON_IN_HOMEPAGE,className, button);
		clickToElement(driver, UserHomePageUI.BUTTON_IN_HOMEPAGE,className, button);
	}
	public String getSuccessMessageDisplayed() {
		waitForAllElementVisible(driver, UserHomePageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserHomePageUI.SUCCESS_MESSAGE);
	}
	public void clickToCloseMessage() {
		waitForElementClickable(driver, UserHomePageUI.SUCCESS_MESSAGE);
		clickToElement(driver, UserHomePageUI.CLOSE_BUTTON);
	}
	public String getSearchErrorMessage() {
		waitForAllElementVisible(driver, UserHomePageUI.ERROR_MESSAGE);
		return getElementText(driver, UserHomePageUI.ERROR_MESSAGE);
	}
	public List<WebElement> getResult() {
		waitForAllElementVisible(driver, UserHomePageUI.PRODUCT_RESULT);
		return getListWebElement(driver, UserHomePageUI.PRODUCT_RESULT);
	}
	public int getNumberResult() {
		waitForAllElementVisible(driver, UserHomePageUI.PRODUCT_RESULT);
		return getElementSize(driver, UserHomePageUI.PRODUCT_RESULT);
	}
	public void checkToCheckBox(String nameCheckbox) {
		waitForAllElementVisible(driver, UserHomePageUI.CHECKBOX_IN_HOME_PAGE, nameCheckbox);
		checkToDefaultCheckBoxOrRadio(driver, UserHomePageUI.CHECKBOX_IN_HOME_PAGE, nameCheckbox);
	}
	public void clickToDropdown(String nameDropdown, String value) {
		waitForElementClickable(driver, UserHomePageUI.DROPDOWN_LIST, nameDropdown);
		selectItemInDropdown(driver, UserHomePageUI.DROPDOWN_LIST, value , nameDropdown);
	}
	public String getProductName() {
		waitForAllElementVisible(driver, UserHomePageUI.PRODUCT_RESULT);
		return getElementText(driver, UserHomePageUI.PRODUCT_RESULT);
	}
	public void hoverOnMenu(String linkName) {
		waitForElementClickable(driver, UserHomePageUI.TOP_MENU_IN_THE_HOMEPAGE, linkName);
		hoverMouseToElement(driver, UserHomePageUI.TOP_MENU_IN_THE_HOMEPAGE, linkName);
	}
	public void clickAddToWishlistButton() {
		waitForElementClickable(driver, UserHomePageUI.WISHLIST_BUTTON);
		clickToElement(driver, UserHomePageUI.WISHLIST_BUTTON);
	}
	public void clickToSharingLink() {
		waitForElementClickable(driver, UserHomePageUI.SHARING_LINK);
		clickToElement(driver, UserHomePageUI.SHARING_LINK);
	}
	public String getPageTitle() {
		waitForAllElementVisible(driver, UserHomePageUI.PAGE_TITLE);
		return getElementText(driver, UserHomePageUI.PAGE_TITLE);
	}
	public String getPageBodyEmpty() {
		waitForAllElementVisible(driver, UserHomePageUI.PAGE_BODY);
		return getElementText(driver, UserHomePageUI.PAGE_BODY);
	}
	public void clickToDynamicLinkName(String linkName) {
		waitForElementClickable(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_NAME, linkName);
		clickToElement(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_NAME,linkName);
	}
	public String getProductInCart() {
		waitForAllElementVisible(driver, UserHomePageUI.PRODUCT_IN_CART);
		return getElementText(driver, UserHomePageUI.PRODUCT_IN_CART);
	}
	public void clickToLHeaderLogo() {
		waitForElementClickable(driver, UserHomePageUI.HEADER_LOGO);
		clickToElement(driver, UserHomePageUI.HEADER_LOGO);
	}
	public void clickToRemoveButtonFromCartPage() {
		waitForElementClickable(driver, UserHomePageUI.REMOVE_BUTTON);
		clickToElement(driver, UserHomePageUI.REMOVE_BUTTON);
		
	}
	public void clickToProductItemButton(String index, String buttonName) {
		waitForElementClickable(driver, UserHomePageUI.PRODUCT_ITEM_BUTTON, index, buttonName );
		clickToElement(driver, UserHomePageUI.PRODUCT_ITEM_BUTTON, index, buttonName);
	}
	public int getNumberOfProductNameInCompareProductPage() {
		waitForAllElementVisible(driver, UserHomePageUI.PRODUCT_NAME_IN_COMPARE_PAGE);
		return getElementSize(driver, UserHomePageUI.PRODUCT_NAME_IN_COMPARE_PAGE);
	}
	public int getNumberOfRemoveButtonInCompareProductPage() {
		waitForAllElementVisible(driver, UserHomePageUI.REMOVE_BUTTON_IN_COMPARE_PAGE);
		return getElementSize(driver, UserHomePageUI.REMOVE_BUTTON_IN_COMPARE_PAGE);
	}
	public int getNumberOfPriceInCompareProductPage() {
		waitForAllElementVisible(driver, UserHomePageUI.PRICE_IN_COMPARE_PAGE);
		return getElementSize(driver, UserHomePageUI.PRICE_IN_COMPARE_PAGE);
	}



}

