package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage{
	private WebDriver driver;
	 
	public UserLoginPageObject(WebDriver driver) {
		 this.driver = driver;	
	 }

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON );
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON );
		//return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX , email);
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX , password);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, UserLoginPageUI.ERROR_MESSAGE_AT_EMAIL_TEXTBOX);
	}

	public String getNotFoundAccountlMessage() {
		return getElementText(driver, UserLoginPageUI.INVALID_ACCOUNT_MESSAGE);
	}

	public String getInvalidErrorMessage() {
		return getElementText(driver, UserLoginPageUI.INVALID_ACCOUNT_MESSAGE);
	}

	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	
}

