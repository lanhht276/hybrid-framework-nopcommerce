package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.UserAddressesPageUI;

public class UserAddressesPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToButton(String buttonName) {
		waitForElementClickable(driver, UserAddressesPageUI.BUTTON_IN_ADDRESS_PAGE, buttonName);
		clickToElement(driver, UserAddressesPageUI.BUTTON_IN_ADDRESS_PAGE, buttonName);
	}

	public void inputToTextBoxInAddressPage(String textbox, String value) {
		waitForElementClickable(driver, UserAddressesPageUI.TEXTBOX_IN_ADDRESS_PAGE, textbox);
		sendKeyToElement(driver, UserAddressesPageUI.TEXTBOX_IN_ADDRESS_PAGE, value, textbox);
	}

	public void selectItemInDropdownInAddressPage(String dropdownName, String value) {
		waitForElementVisible(driver, UserAddressesPageUI.DROPDOWN_LIST_IN_ADDRESS_PAGE, dropdownName);
		selectItemInDropdown(driver, UserAddressesPageUI.DROPDOWN_LIST_IN_ADDRESS_PAGE, value, dropdownName);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, UserAddressesPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserAddressesPageUI.SUCCESS_MESSAGE);
	}

	public String getTextInAddressPage(String fieldName) {
		waitForElementVisible(driver, UserAddressesPageUI.FIELD_IN_ADDRESS_PAGE, fieldName);
		return getElementText(driver, UserAddressesPageUI.FIELD_IN_ADDRESS_PAGE, fieldName);
	}

}
