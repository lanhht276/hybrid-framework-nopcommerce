package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Driver at RegisterPageObject is: " + driver.toString());
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON );
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	public void inputToFirstNameTextbox(String firstName) {
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);	
	}

	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);	
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		sendKeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassword);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
	}

	public String getErrorMessageAtLastNameTextbox() {
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
	}
	
	public String getInvalidErrorMessageEmail() {
		return getElementText(driver, RegisterPageUI.INVALID_EMAIL_ERROR_MSG);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
	}
	
	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}

	public String getRegisterSuccessMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	public void clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
	}
}
