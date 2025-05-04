package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class TestCase02_NopCommerce_My_Account extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword, company, country, stateProvince, city,
			address1, address2, zipPostalCode, phoneNumber, faxNumber, updatedFirstName, updatedLastName,
			updatedEmailAddress, newPassword, product, titleReview, textReview;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressesPageObject userAddressesPage;
	private UserChangePasswordPageObject userChangePasswordPage;
	private UserMyProductReviewPageObject UserMyProductReviewPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "QC test";
		lastName = "FC1234";
		updatedFirstName = "Automation";
		updatedLastName = "FC";
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		updatedEmailAddress = "automationfc2025" + getRandomNumber() + ".vn@gmail.com";
		company = "Automation FC";
		password = "123456";
		confirmPassword = "123456";
		country = "Vietnam";
		stateProvince = "Đà Nẵng";
		city = "Da Nang";
		address1 = "123/04 Le Lai";
		address2 = "234/05 Hai Phong";
		zipPostalCode = "550000";
		phoneNumber = "0123456789";
		faxNumber = "0987654321";
		newPassword = "666666";
		titleReview = "Automation test title";
		textReview = "Automation test review" + getRandomNumber();
		product = "Apple MacBook Pro";

		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		userRegisterPage.inputToTextBoxInRegisterPage("Email", emailAddress);
		System.out.println(emailAddress);
		userRegisterPage.inputToTextBoxInRegisterPage("Password", password);
		userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword", confirmPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		userHomePage = userRegisterPage.clickToLogoutLink();

		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		System.out.println("email in TC is: " + emailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", password);
		System.out.println("pw in TC is: " + password);

		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(userHomePage.isHeaderNameDisplayed("account"));
	}

	@Test
	public void TC_01_Customer_Info() {

		// userHomePage.openCustomerInfoPage(driver);
		// userHomePage.clickToLinkName("My account");
		userHomePage.openCustomerInfoPage();
		// userCustomerInfoPage = userHomePage.openCustomerInfoPage(driver);
		userCustomerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoPageDisplayed());
		userHomePage.sleepInSecond(2);

		userCustomerInfoPage.checkToGenderRadio();
		userCustomerInfoPage.inputToTextboxInCustomerInfoPage("FirstName", updatedFirstName);
		userCustomerInfoPage.inputToTextboxInCustomerInfoPage("LastName", updatedLastName);
		userCustomerInfoPage.inputToTextboxInCustomerInfoPage("Email", updatedEmailAddress);
		userCustomerInfoPage.inputToTextboxInCustomerInfoPage("Company", company);
		userCustomerInfoPage.clickToSaveButton();
		userCustomerInfoPage.sleepInSecond(3);
		Assert.assertEquals(userCustomerInfoPage.getSuccessMessageDisplayed(),
				"The customer info has been updated successfully.");

		Assert.assertTrue(userCustomerInfoPage.isFemaleSelected());
		Assert.assertEquals(userCustomerInfoPage.getTextInTextbox("FirstName"), updatedFirstName);
		Assert.assertEquals(userCustomerInfoPage.getTextInTextbox("LastName"), updatedLastName);
		Assert.assertEquals(userCustomerInfoPage.getTextInTextbox("Email"), updatedEmailAddress);
		Assert.assertEquals(userCustomerInfoPage.getTextInTextbox("Company"), company);
	}

	@Test
	public void TC_02_Address() {
		userHomePage.openPagesAtMyAccountPageByName(driver, "Addresses");
		userAddressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		userAddressesPage.clickToButton("Add new");
		userAddressesPage.inputToTextBoxInAddressPage("FirstName", updatedFirstName);
		userAddressesPage.inputToTextBoxInAddressPage("LastName", updatedLastName);
		userAddressesPage.inputToTextBoxInAddressPage("Email", updatedEmailAddress);
		userAddressesPage.inputToTextBoxInAddressPage("Company", company);
		userAddressesPage.selectItemInDropdownInAddressPage("Country", country);
		userAddressesPage.selectItemInDropdownInAddressPage("StateProvince", stateProvince);
		userAddressesPage.inputToTextBoxInAddressPage("City", city);
		userAddressesPage.inputToTextBoxInAddressPage("Address1", address1);
		userAddressesPage.inputToTextBoxInAddressPage("Address2", address2);
		userAddressesPage.inputToTextBoxInAddressPage("ZipPostalCode", zipPostalCode);
		userAddressesPage.inputToTextBoxInAddressPage("PhoneNumber", phoneNumber);
		userAddressesPage.inputToTextBoxInAddressPage("FaxNumber", faxNumber);
		userAddressesPage.clickToButton("Save");
		Assert.assertEquals(userAddressesPage.getSuccessMessageDisplayed(),
				"The new address has been added successfully.");

		Assert.assertEquals(userAddressesPage.getTextInAddressPage("name"), updatedFirstName + " " + updatedLastName);
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("email").contains(updatedEmailAddress));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("phone").contains(phoneNumber));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("fax").contains(faxNumber));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("company").contains(company));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("country").contains(country));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("stateprovince").contains(stateProvince));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("city").contains(city));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("address1").contains(address1));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("address2").contains(address2));
		Assert.assertTrue(userAddressesPage.getTextInAddressPage("zippostalcode").contains(zipPostalCode));
	}

	@Test
	public void TC_03_Change_Password() {
		userAddressesPage.openPagesAtMyAccountPageByName(driver, "Change password");
		userChangePasswordPage = PageGeneratorManager.getUserChangePasswordPage(driver);
		userChangePasswordPage.inputToTextBoxinChangePasswordPage("OldPassword", password);
		userChangePasswordPage.inputToTextBoxinChangePasswordPage("NewPassword", newPassword);
		userChangePasswordPage.inputToTextBoxinChangePasswordPage("ConfirmNewPassword", newPassword);
		userChangePasswordPage.clickToButton("Change password");
		Assert.assertEquals(userChangePasswordPage.getSuccessMessageDisplayed(), "Password was changed");
		userChangePasswordPage.clickToCloseMessage();
		userChangePasswordPage.sleepInSecond(3);
		userChangePasswordPage.clickToLogoutLinkAtUserPage(driver);

		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", updatedEmailAddress);
		System.out.println("email in TC is: " + updatedEmailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", password);
		System.out.println("pw in TC is: " + password);

		userHomePage = userLoginPage.clickToLoginButton();

		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		userLoginPage.inputToFieldInLoginPage("Email", updatedEmailAddress);
		System.out.println("email in TC is: " + updatedEmailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", newPassword);
		System.out.println("pw in TC is: " + newPassword);
		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(userHomePage.isHeaderNameDisplayed("account"));
	}

	@Test
	public void TC_04_My_Product_Review() {
		userHomePage.clickToProductInHomePage(product);
		userHomePage.clickToLinkName("Add your review");
		userHomePage.inputToTextbox("input", "AddProductReview_Title", titleReview);
		userHomePage.inputToTextbox("textarea", "AddProductReview_ReviewText", textReview);
		userHomePage.clickToRating("4");
		userHomePage.clickToButon("buttons", "Submit review");
		Assert.assertEquals(userHomePage.getSuccessMessageDisplayed(), "Product review is successfully added.");
		userHomePage.clickToCloseMessage();
		userHomePage.sleepInSecond(3);
		userHomePage.clickToDynamicLinkName("account");
		UserMyProductReviewPage = userHomePage.openMyProductReviewPage(driver);
		Assert.assertEquals(UserMyProductReviewPage.getProductReview("review-title"), titleReview);
		Assert.assertEquals(UserMyProductReviewPage.getProductReview("review-text"), textReview);
		Assert.assertTrue(UserMyProductReviewPage.getProductReview("review-info").contains(product));
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
