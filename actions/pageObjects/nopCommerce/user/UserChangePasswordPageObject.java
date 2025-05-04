package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.UserAddressesPageUI;
import pageUI.nopCommerce.user.UserChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {
	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToTextBoxinChangePasswordPage(String oldPassword, String value) {
		waitForElementClickable(driver, UserChangePasswordPageUI.TEXTBOX_IN_CHANGE_PASSWORD_PAGE, oldPassword);
		sendKeyToElement(driver, UserChangePasswordPageUI.TEXTBOX_IN_CHANGE_PASSWORD_PAGE, value, oldPassword);
	}

	public void clickToButton(String button) {
		waitForElementClickable(driver, UserChangePasswordPageUI.BUTTON_IN_CHANGE_PASSWORD_PAGE, button);
		clickToElement(driver, UserChangePasswordPageUI.BUTTON_IN_CHANGE_PASSWORD_PAGE, button);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, UserChangePasswordPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserChangePasswordPageUI.SUCCESS_MESSAGE);
	}

	public void clickToCloseMessage() {
		waitForElementVisible(driver, UserChangePasswordPageUI.SUCCESS_MESSAGE);
		clickToElement(driver, UserChangePasswordPageUI.CLOSE_BUTTON);
	}
}
