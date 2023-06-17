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

public class Level_06_Page_Generator_Manager_III extends BaseTest{
	private WebDriver driver;
	private String firstName,lastName,invalidEmail, existingEmail, notFoundEmail, password,confirmPassword,invalidPassword;
	
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userMyAccountPage;
	 
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on browserName: " + browserName);

		driver = getBrowserDriver(browserName);
		
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "afc@123.";
		//emailAddress = "afc" + getRandomNumber() + "@mail.com";
		existingEmail = "afc" + getRandomNumber() + "@mail.com";
		notFoundEmail = "afc"+ getRandomNumber() + "@mail.vn";
		
		password = "123456";
		confirmPassword = "123456";
		invalidPassword = "123";
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		System.out.println("Pre-condition - Step 1: Click To Login Link");
		userRegisterPage = userHomePage.openRegisterPage();
		
		System.out.println("Pre-condition - Step 2: Input to required fields");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(existingEmail);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(confirmPassword);
			
		System.out.println("Pre-condition - Step 3: Click To Login Button");
		userRegisterPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 4: Verify success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 5: Click to Continue button");
		userRegisterPage.clickToContinueButton();
		// registerPage.clickToLogoutLink();
	}

	@Test
	public void Login_01_Empty_Data() {
		
		System.out.println("Login_01 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_01 - Step 2: Click To Login Button");
		userLoginPage.clickToLoginButton();
		
		System.out.println("Login_01 - Step 3: Verify error message is displayed");
		Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");			
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_02 - Step 2: Input invalid email to email textbox");
		userLoginPage.inputToEmailTextbox(invalidEmail);
		
		System.out.println("Login_02 - Step 3: Click To Login Button");
		userLoginPage.clickToLoginButton();
		
		System.out.println("Login_02 - Step 4: Verify error message is displayed");
		Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Wrong email");	
	}
	
	@Test
	public void Login_03_Email_Not_Register() {
		System.out.println("Login_03 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_03 - Step 2: Input not registered email");
		userLoginPage.inputToEmailTextbox(notFoundEmail);
		
		System.out.println("Login_03 - Step 3: Click To Login Button");
		userLoginPage.clickToLoginButton();
		
		System.out.println("Login_03 - Step 4: Verify error message is displayed");		
		Assert.assertEquals(userLoginPage.getNotFoundAccountlMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");	
	}
  
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Login_04 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_04 - Step 2: Input registered email");
		userLoginPage.inputToEmailTextbox(existingEmail);
		
		System.out.println("Login_04 - Step 3:  Not input password");
		userLoginPage.inputToPasswordTextbox("");
		
		System.out.println("Login_04 - Step 4: Click To Login Button");
		userLoginPage.clickToLoginButton();
		
		System.out.println("Login_04 - Step 5: Verify error message is displayed");
		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");	
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Login_05 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_05 - Step 2: Input registered email");
		userLoginPage.inputToEmailTextbox(existingEmail);
		
		System.out.println("Login_05 - Step 3: Input incorrect password");
		userLoginPage.inputToPasswordTextbox(invalidPassword);
		
		System.out.println("Login_05 - Step 4: Click To Login Button");
		userLoginPage.clickToLoginButton();

		System.out.println("Login_05 - Step 5: Verify error message is displayed");
		Assert.assertEquals(userLoginPage.getInvalidErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Existing_Email_Correct_Password() {
		System.out.println("Login_06 - Step 1: Click To Login Link");
		userLoginPage = userHomePage.openLoginPage();
		
		System.out.println("Login_06 - Step 2: Input to required fields");
		userLoginPage.inputToEmailTextbox(existingEmail);
		userLoginPage.inputToPasswordTextbox(password);
				
		System.out.println("Login_06 - Step 3: Click To Login Button");
		userHomePage = userLoginPage.clickToLoginButton();
		
		System.out.println("Login_06 - Step 4: Verify My Account link is displayed");						
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		//Click on My Account page 
		userMyAccountPage = userHomePage.openCustomerInfoPage();
		
		//Click on Newsletter checkbox
		userMyAccountPage.clickToNewsletterCheckbox();
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
