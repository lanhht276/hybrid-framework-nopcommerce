package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class HomePageObject extends BasePage{
	 private WebDriver driver;
	 
	public HomePageObject(WebDriver mappingDriver) {
		 driver = mappingDriver;
		 System.out.println("Driver at HomePageObject is: " + driver.toString());
	 }
	

	public void clickToRegisterLink() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_LINK );
		clickToElement(driver, LoginPageUI.REGISTER_LINK );
		
	}

	
}

