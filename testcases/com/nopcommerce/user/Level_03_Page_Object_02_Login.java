package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_02_Login extends BasePage{
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String firstName,lastName,invalidEmail, existingEmail, notFoundEmail, password,confirmPassword,invalidPassword;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	 
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "afc@123.";
		//emailAddress = "afc" + getRandomNumber() + "@mail.com";
		existingEmail = "afc" + getRandomNumber() + "@mail.com";
		notFoundEmail = "afc"+ getRandomNumber() + "@mail.vn";
		
		password = "123456";
		confirmPassword = "123456";
		invalidPassword = "123";
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Pre-condition - Step 1: Click To Login Link");
		homePage.openRegisterPage();
		
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Pre-condition - Step 2: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
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
		
		homePage = new UserHomePageObject(driver);
		
		System.out.println("Login_01 - Step 1: Click To Login Link");
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		
		System.out.println("Login_01 - Step 2: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01 - Step 3: Verify error message is displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
					
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 1: Click To Login Link");
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		
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
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
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
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		System.out.println("Login_04 - Step 2: Input registered email");
		loginPage.inputToEmailTextbox(existingEmail);
		
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
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		System.out.println("Login_05 - Step 2: Input registered email");
		loginPage.inputToEmailTextbox(existingEmail);
		
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
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		System.out.println("Login_06 - Step 2: Input to required fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
				
		System.out.println("Login_06 - Step 3: Click To Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_06 - Step 4: Verify My Account link is displayed");						
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
				
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
