package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.HomePageObject;
import PageObjects.RegisterPageObject;
import commons.BasePage;
import commons.BaseTest;


public class Level_04_Multiple_Browser extends BaseTest{
	
	private WebDriver driver;
	private String emailAddress;String firstName;String lastName; String invalidEmail;String password;String confirmPassword; String invalidPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	
	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on browserName: " + browserName);
		
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "afc@mail";
		
		password = "123456";
		confirmPassword = "123456";
		invalidPassword = "123";
	}

	@Test
	public void Register_01_Empty_Data() {
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		System.out.println(" Register_01 - Step 1: Click To Register Link");
		homePage.clickToRegisterLink();
		
		System.out.println(" Register_01 - Step 2: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println(" Register_01 - Step 3: Verify error message is displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
			
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		System.out.println(" Register_02 - Step 1: Click To Register Link");
		homePage.clickToRegisterLink();
		
		System.out.println(" Register_02 - Step 2: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(invalidEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		
		System.out.println(" Register_02 - Step 3: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println(" Register_02 - Step 4: Verify error message is displayed");
		Assert.assertEquals(registerPage.getInvalidErrorMessageEmail(), "Wrong email");
		
	}
	
	/*
	 * @Test public void Register_03_Success() {
	 * System.out.println(" Register_03 - Step 1: Click To Register Link");
	 * homePage.clickToRegisterLink();
	 * 
	 * System.out.println(" Register_03 - Step 2: Input to required fields");
	 * registerPage.inputToFirstNameTextbox(firstName);
	 * registerPage.inputToLastNameTextbox(lastName);
	 * registerPage.inputToEmailTextbox(emailAddress);
	 * registerPage.inputToPasswordTextbox(password);
	 * registerPage.inputToConfirmPasswordTextbox(confirmPassword);
	 * 
	 * System.out.println(" Register_03 - Step 3: Click To Register Button");
	 * registerPage.clickToRegisterButton();
	 * 
	 * System.out.
	 * println(" Register_03 - Step 4: Verify success message is displayed");
	 * Assert.assertEquals(registerPage.getRegisterSuccessMessage(),
	 * "Your registration completed");
	 * 
	 * System.out.println(" Register_03 - Step 5: Click to Continue button");
	 * registerPage.clickToContinueButton(); //registerPage.clickToLogoutLink();
	 * 
	 * }
	 * 
	 * @Test public void Register_04_Existing_Email() {
	 * System.out.println(" Register_04 - Step 1: Click To Register Link");
	 * homePage.clickToRegisterLink();
	 * 
	 * System.out.println(" Register_04 - Step 2: Input to required fields");
	 * registerPage.inputToFirstNameTextbox(firstName);
	 * registerPage.inputToLastNameTextbox(lastName);
	 * registerPage.inputToEmailTextbox(emailAddress);
	 * registerPage.inputToPasswordTextbox(password);
	 * registerPage.inputToConfirmPasswordTextbox(confirmPassword);
	 * 
	 * System.out.println(" Register_04 - Step 3: Click To Register Button");
	 * registerPage.clickToRegisterButton();
	 * 
	 * System.out.println(" Register_04 - Step 4: Verify error message is displayed"
	 * ); Assert.assertEquals(registerPage.getInvalidErrorMessageEmail(),
	 * "The specified email already exists");
	 * 
	 * }
	 * 
	 * @Test public void Register_05_Password_Less_Than_6_Chars() {
	 * System.out.println(" Register_05 - Step 1: Click To Register Link");
	 * homePage.clickToRegisterLink();
	 * 
	 * System.out.println(" Register_05 - Step 2: Input to required fields");
	 * registerPage.inputToFirstNameTextbox(firstName);
	 * registerPage.inputToLastNameTextbox(lastName);
	 * registerPage.inputToEmailTextbox(emailAddress);
	 * registerPage.inputToPasswordTextbox(invalidPassword);
	 * registerPage.inputToConfirmPasswordTextbox(invalidPassword);
	 * 
	 * System.out.println(" Register_05 - Step 3: Click To Register Button");
	 * registerPage.clickToRegisterButton();
	 * 
	 * System.out.println(" Register_05 - Step 4: Verify error message is displayed"
	 * ); Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
	 * "Password must meet the following rules:\nmust have at least 6 characters");
	 * 
	 * }
	 * 
	 * @Test public void Register_06_Invalid_Confirm_Password() {
	 * System.out.println(" Register_06 - Step 1: Click To Register Link");
	 * homePage.clickToRegisterLink();
	 * 
	 * System.out.println(" Register_06 - Step 2: Input to required fields");
	 * registerPage.inputToFirstNameTextbox(firstName);
	 * registerPage.inputToLastNameTextbox(lastName);
	 * registerPage.inputToEmailTextbox(emailAddress);
	 * registerPage.inputToPasswordTextbox(password);
	 * registerPage.inputToConfirmPasswordTextbox(invalidPassword);
	 * 
	 * System.out.println(" Register_06 - Step 3: Click To Register Button");
	 * registerPage.clickToRegisterButton();
	 * 
	 * System.out.println(" Register_06 - Step 4: Verify error message is displayed"
	 * );
	 * Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
	 * "The password and confirmation password do not match.");
	 * 
	 * }
	 */
	
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
