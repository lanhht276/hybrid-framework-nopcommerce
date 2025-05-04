package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.BasePageNopCommerceUI;
import pageUI.nopCommerce.user.UserCustomerInfoPageUI;
import pageUI.nopCommerce.user.UserHomePageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		checkToDefaultCheckBoxOrRadio(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, BasePageNopCommerceUI.CUSTOMER_INFO_HEADER);
		return isControlDisplayed(driver, BasePageNopCommerceUI.CUSTOMER_INFO_HEADER);
	}

	public void checkToGenderRadio() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.FEMALE_GENDER);
		checkToDefaultCheckBoxOrRadio(driver, UserCustomerInfoPageUI.FEMALE_GENDER);
	}

	public void inputToTextboxInCustomerInfoPage(String textboxName, String valueToInput) {
		waitForElementClickable(driver, UserCustomerInfoPageUI.TEXTBOX_IN_CUSTOMER_INFO_PAGE, textboxName);
		sendKeyToElement(driver, UserCustomerInfoPageUI.TEXTBOX_IN_CUSTOMER_INFO_PAGE, valueToInput, textboxName);
	}

	public String getTextInTextbox(String textboxName) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.TEXTBOX_IN_CUSTOMER_INFO_PAGE, textboxName);
		return getElementAttribute(driver, UserCustomerInfoPageUI.TEXTBOX_IN_CUSTOMER_INFO_PAGE, "value", textboxName);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserCustomerInfoPageUI.SUCCESS_MESSAGE);
	}

	public boolean isFemaleSelected() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.FEMALE_GENDER);
		return isElementSelected(driver, UserCustomerInfoPageUI.FEMALE_GENDER);
	}
}
