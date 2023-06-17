package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserMyAccountPageObject extends BasePage{
	private WebDriver driver;
	
	public UserMyAccountPageObject(WebDriver driver) {
		 this.driver = driver;	 
	 }
}
