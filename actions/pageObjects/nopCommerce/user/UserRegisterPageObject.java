package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopCommerce.user.BasePageUI;
import pageUI.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON );
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}
	public void inputToFirstNameTextbox(String firstName) {
		sendKeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		sendKeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);	
	}

	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);	
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		sendKeyToElement(driver, UserRegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassword);
	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		return getElementText(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MSG);
	}

	public String getErrorMessageAtLastNameTextbox() {
		return getElementText(driver, UserRegisterPageUI.LASTNAME_ERROR_MSG);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MSG);
	}
	
	public String getInvalidErrorMessageEmail() {
		return getElementText(driver, UserRegisterPageUI.INVALID_EMAIL_ERROR_MSG);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MSG);
	}
	
	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}

	public String getRegisterSuccessMessage() {
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	public void clickToContinueButton() {
		waitForElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
	}
}
