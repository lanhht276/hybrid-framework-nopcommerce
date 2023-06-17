package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserProductPageObject extends BasePage{
		private WebDriver driver;
		
		public UserProductPageObject(WebDriver driver) {
			 this.driver = driver;	 
		 }
}
