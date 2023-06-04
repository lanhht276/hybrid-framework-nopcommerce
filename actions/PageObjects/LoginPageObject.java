package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	 
	public LoginPageObject(WebDriver driver) {
		 this.driver = driver;
		 System.out.println("Driver at LoginPageObject is: " + driver.toString());
	 }
	

	public void clickToRegisterLink() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_LINK );
		clickToElement(driver, LoginPageUI.REGISTER_LINK );
		
	}


	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON );
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON );
		
	}


	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX , email);
		
	}


	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX , password);

	}


	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, LoginPageUI.ERROR_MESSAGE_AT_EMAIL_TEXTBOX);
	}


	public String getNotFoundAccountlMessage() {
		return getElementText(driver, LoginPageUI.INVALID_ACCOUNT_MESSAGE);
	}


	public String getInvalidErrorMessage() {
		return getElementText(driver, LoginPageUI.INVALID_ACCOUNT_MESSAGE);
	}


	

	

	
}

