package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	 
	public HomePageObject(WebDriver driver) {
		 this.driver = driver;
		 System.out.println("Driver at HomePageObject is: " + driver.toString());
	 }
	

	public void clickToRegisterLink() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_LINK );
		clickToElement(driver, LoginPageUI.REGISTER_LINK );
	}


	public void clickToLoginLink() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_LINK );
		clickToElement(driver, LoginPageUI.LOGIN_LINK );
	}


	public boolean isMyAccountLinkDisplayed() {
		
		return isControlDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	
}

