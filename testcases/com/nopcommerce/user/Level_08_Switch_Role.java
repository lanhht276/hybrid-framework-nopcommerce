package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;


public class Level_08_Switch_Role extends BaseTest{
	private WebDriver driver;
	private String firstName,lastName, userEmailAddress, userPassword,confirmPassword, adminEmailAddress, adminPassword;
	
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private UserRegisterPageObject userRegisterPage ;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	
	 
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on browserName: " + browserName);

		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		
		firstName = "Automation";
		lastName = "FC";
		userEmailAddress = "afc" + getRandomNumber() + "@mail.com";
		userPassword = "123456";
		confirmPassword = "123456";
		
		userRegisterPage = userHomePage.openRegisterPage();
		
		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(userEmailAddress);
		System.out.println(userEmailAddress);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(confirmPassword);
			
		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userRegisterPage.clickToContinueButton();
	}

	@Test
	public void Role_01_User() {
		userLoginPage = userHomePage.openLoginPage();
		
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		userCustomerInfoPage = userHomePage.openCustomerInfoPage();
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardPageDisplayed());	
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
		
	}
	
	@Test
	public void Role_02_Admin() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
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