package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopCommerce.user.UserHomePageUI;

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
		return isControlDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK );
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK );
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserMyAccountPageObject openMyAccountPage(WebDriver driver) {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserMyAccountPage(driver);
	}
	

}

