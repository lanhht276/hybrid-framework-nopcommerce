package com.nopcommerce.user;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TestCase04_NopCommerce_Search_Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword, company, country, stateProvince, city,
			address1, address2, zipPostalCode, phoneNumber, faxNumber, updatedFirstName, updatedLastName,
			updatedEmailAddress, newPassword, product, titleReview, textReview;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressesPageObject UserAddressesPage;
	private UserChangePasswordPageObject UserChangePasswordPage;
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

		/*
		 * userRegisterPage = userHomePage.openRegisterPage();
		 * userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		 * userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		 * userRegisterPage.inputToTextBoxInRegisterPage("Email", emailAddress);
		 * System.out.println(emailAddress);
		 * userRegisterPage.inputToTextBoxInRegisterPage("Password", password);
		 * userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword",
		 * confirmPassword); userRegisterPage.clickToRegisterButton();
		 * Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(),
		 * "Your registration completed"); userHomePage =
		 * userRegisterPage.clickToLogoutLink();
		 * 
		 * userLoginPage = userHomePage.openLoginPage();
		 * userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		 * System.out.println("email in TC is: " + emailAddress);
		 * userLoginPage.inputToFieldInLoginPage("Password", password);
		 * System.out.println("pw in TC is: " + password);
		 * 
		 * userHomePage = userLoginPage.clickToLoginButton();
		 * Assert.assertTrue(userHomePage.isHomepageDisplayed("My account"));
		 */
		userHomePage.hoverOnMenu("Computers ");
		userHomePage.clickToLinkName("Notebooks ");
	}

	@Test
	public void TC_01_Sort_With_Name_A_To_Z() {
		userHomePage.clickToDropdown("products-orderby", "Name: A to Z");
		
	}

	@Test
	public void  TC_02_Sort_With_Name_Z_To_A() {
		
	}

	@Test
	public void TC_03_Sort_With_Price_Low_To_High() {
		
	}

	@Test
	public void  TC_04_Sort_With_Price_High_To_Low() {	
	
	}
	
	
	@Test
	public void TC_05_Advanced_Search_With_Parent_Categories() {
		
	}
	@Test
	public void TC_06_Advanced_Search_With_Sub_Categories() {
	
	}
	@Test
	public void TC_07_Advanced_Search_With_Incorrect_Manufacturer() {
		
	}
	@Test
	public void TC_08_Advanced_Search_With_Correct_Manufacturer() {
	
	}

	@AfterClass
	public void afterClass() {
		// closeBrowser();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
