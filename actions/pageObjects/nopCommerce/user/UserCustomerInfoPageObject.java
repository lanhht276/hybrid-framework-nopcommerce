package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.BasePageUI;
import pageUI.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage{
	private WebDriver driver;
	 
	public UserCustomerInfoPageObject(WebDriver driver) {
		 this.driver = driver;	 
	 }
	
	public void clickToNewsletterCheckbox() {
		checkToDefaultCheckBoxOrRadio(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, BasePageUI.CUSTOMER_INFO_HEADER);
		return isControlDisplayed(driver, BasePageUI.CUSTOMER_INFO_HEADER);
	}

}

