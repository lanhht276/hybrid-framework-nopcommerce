package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.BasePageNopCommerceUI;

public class UserMyAccountPageObject extends BasePage {
	private WebDriver driver;

	public UserMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, BasePageNopCommerceUI.CUSTOMER_INFO_HEADER);
		return isControlDisplayed(driver, BasePageNopCommerceUI.CUSTOMER_INFO_HEADER);
	}

}
