package com.nopcommerce.user;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObject;
import pageFactory.nopCommerce.LoginPageObject;
import pageFactory.nopCommerce.RegisterPageObject;


public class Level_05_Page_Factory extends BaseTest{
	private WebDriver driver;
	private String firstName;String lastName;String invalidEmail; String existingEmail; String notFoundEmail; String password;String confirmPassword;String invalidPassword;String emailAddress;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	 
	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on browserName: " + browserName);
		
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "afc@mail.";
		notFoundEmail = "afc" + getRandomNumber() + "@mail.com";
		password = "123456";
		confirmPassword = "123456";
		invalidPassword = "123";
	
		homePage = new HomePageObject(driver);
		System.out.println("Pre-condition - Step 1: Click To Login Link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Pre-condition - Step 2: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
			
		System.out.println("Pre-condition - Step 3: Click To Login Button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 4: Verify success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 5: Click to Continue button");
		registerPage.clickToContinueButton();
		// registerPage.clickToLogoutLink();

	}

	@Test
	public void Login_01_Empty_Data() {
		
		homePage = new HomePageObject(driver);
		
		System.out.println("Login_01 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_01 - Step 2: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01 - Step 3: Verify error message is displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
					
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_02 - Step 2: Input invalid email to email textbox");
		
		loginPage.inputToEmailTextbox(invalidEmail);
		
		System.out.println("Login_02 - Step 3: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_02 - Step 4: Verify error message is displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
		
	}
	
	@Test
	public void Login_03_Email_Not_Register() {
		System.out.println("Login_03 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_03 - Step 2: Input not registered email");
	
		loginPage.inputToEmailTextbox(notFoundEmail);
		
		System.out.println("Login_03 - Step 3: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_03 - Step 4: Verify error message is displayed");		
		Assert.assertEquals(loginPage.getNotFoundAccountlMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
  
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Login_04 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_04 - Step 2: Input registered email");
		loginPage.inputToEmailTextbox(emailAddress);
		
		System.out.println("Login_04 - Step 3:  Not input password");
		loginPage.inputToPasswordTextbox("");
		
		System.out.println("Login_04 - Step 4: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_04 - Step 5: Verify error message is displayed");
		Assert.assertEquals(loginPage.getInvalidErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Login_05 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_05 - Step 2: Input registered email");
		loginPage.inputToEmailTextbox(emailAddress);
		
		System.out.println("Login_05 - Step 3: Input incorrect password");
		loginPage.inputToPasswordTextbox(invalidPassword);
		
		System.out.println("Login_05 - Step 4: Click To Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login_05 - Step 5: Verify error message is displayed");
		Assert.assertEquals(loginPage.getInvalidErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	
	@Test
	public void Login_06_Existing_Email_Correct_Password() {
		System.out.println("Login_06 - Step 1: Click To Login Link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_06 - Step 2: Input to required fields");
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
				
		System.out.println("Login_06 - Step 3: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_06 - Step 4: Verify My Account link is displayed");						
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
				
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
