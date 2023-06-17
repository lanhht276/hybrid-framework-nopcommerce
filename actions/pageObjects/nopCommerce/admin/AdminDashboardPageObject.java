package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage{
	private WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver) {
		 this.driver = driver;	 
	 }

	public boolean isDashboardPageDisplayed() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isControlDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}
	

	

}