package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.HomePageObject;
import PageObjects.RegisterPageObject;
import commons.BasePage;
import pageUIs.LoginPageUI;


public class Level_03_Page_Object extends BasePage{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	String firstName = "Automation";
	String lastName = "FC";
	String invalidEmail = "afc@mail";
	
	String password = "123456";
	String confirmPassword = "123456";
	String invalidPassword = "123";
		
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
			
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToRegisterLink();
					
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(invalidEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		
		registerPage.clickToRegisterButton();
				
		Assert.assertEquals(registerPage.getInvalidErrorMessageEmail(), "Wrong email");
		
	}
	
	@Test
	public void TC_03_Register_Success() {
		homePage.clickToRegisterLink();
						
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		
				
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToContinueButton();
		registerPage.clickToLogoutLink();
		
	}
  
	@Test
	public void TC_04_Register_Existing_Email() {
		homePage.clickToRegisterLink();
				
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getInvalidErrorMessageEmail(), "The specified email already exists");
		
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		
		homePage.clickToRegisterLink();
		
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(invalidPassword);
		registerPage.inputToConfirmPasswordTextbox(invalidPassword);
		registerPage.clickToRegisterButton();

		
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
				
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();
			
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(invalidPassword);
		registerPage.clickToRegisterButton();

								
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
				
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
