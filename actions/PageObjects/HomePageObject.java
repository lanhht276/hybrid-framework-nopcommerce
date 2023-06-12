package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	 
	public HomePageObject(WebDriver driver) {
		 this.driver = driver;	 
	 }
	
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK );
		clickToElement(driver, HomePageUI.REGISTER_LINK );
		//return new RegisterPageObject(driver);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK );
		clickToElement(driver, HomePageUI.LOGIN_LINK );
		//return new LoginPageObject(driver);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		return isControlDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
}

