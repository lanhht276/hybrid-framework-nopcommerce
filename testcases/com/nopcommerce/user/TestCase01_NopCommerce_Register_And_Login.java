package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class TestCase01_NopCommerce_Register_And_Login extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, invalidEmail, existingEmail, notFoundEmail, password,
			confirmPassword, invalidPassword;

	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "afc@123.";
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		existingEmail = "afc" + getRandomNumber() + "@mail.com";
		notFoundEmail = "afc" + getRandomNumber() + "@mail.vn";

		password = "123456";
		confirmPassword = "123456";
		invalidPassword = "123";
	}

	@Test(priority = 1)
	public void Register_01_Empty_Data() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("FirstName"), "First name is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("LastName"), "Last name is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("Email"), "Email is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("ConfirmPassword"), "Password is required.");
	}

	@Test(priority = 2)
	public void Register_02_Invalid_Email() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		userRegisterPage.inputToTextBoxInRegisterPage("Email", invalidEmail);
		userRegisterPage.inputToTextBoxInRegisterPage("Password", password);
		userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword", confirmPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("Email"), "Please enter a valid email address.");
	}

	@Test(priority = 3)
	public void Register_03_Valid_Email() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
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

	}

	@Test(priority = 4)
	public void Register_04_Existing_Email() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		userRegisterPage.inputToTextBoxInRegisterPage("Email", emailAddress);
		System.out.println(emailAddress);
		userRegisterPage.inputToTextBoxInRegisterPage("Password", password);
		userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword", confirmPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getInvalidErrorMessageEmail(), "The specified email already exists");
	}

	@Test(priority = 5)
	public void Register_05_Password_Less_Than_6_Characters() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		userRegisterPage.inputToTextBoxInRegisterPage("Email", emailAddress);
		userRegisterPage.inputToTextBoxInRegisterPage("Password", invalidPassword);
		userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword", invalidPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("Password"),
				"Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");
	}
	
	@Test(priority = 6)
	public void Register_06_Password_Not_Match() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.openRegisterPage();
		userRegisterPage.inputToTextBoxInRegisterPage("FirstName", firstName);
		userRegisterPage.inputToTextBoxInRegisterPage("LastName", lastName);
		userRegisterPage.inputToTextBoxInRegisterPage("Email", emailAddress);
		userRegisterPage.inputToTextBoxInRegisterPage("Password", password);
		userRegisterPage.inputToTextBoxInRegisterPage("ConfirmPassword", invalidPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox("ConfirmPassword"),
				"The password and confirmation password do not match.");
	}
	
	@Test(priority = 7)
	public void Login_07_Empty_Data() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}
	
	@Test(priority = 8)
	public void Login_08_Invalid_Email() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", invalidEmail);
		userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Please enter a valid email address.");
	}
	@Test(priority = 9)
	public void Login_09_Not_Register_Email() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", notFoundEmail);
		userLoginPage.inputToFieldInLoginPage("Password", invalidPassword);
		userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	@Test(priority = 10)
	public void Login_10_Register_Email_Empty_Password() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");	
	}
	@Test(priority = 11)
	public void Login_11_Register_Email_Wrong_Password() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", invalidPassword);
		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");	
	}
	@Test(priority = 12)
	public void Login_12_Register_Email_Correct_Password() {
		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		System.out.println("email in TC 12 is: " + emailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", password);
		System.out.println("pw in TC 12 is: " + password);
		
		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(userHomePage.isLinkNameDisplayed("My account"));
		 
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
