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
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrderPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.nopCommerce.user.UserStockPageObject;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, validEmail, password, confirmPassword;

	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressesPageObject userAddressesPage;
	private UserRewardPointPageObject userRewardPointPage;
	private UserOrderPageObject userOrderPage;
	private UserProductPageObject userProductPage;
	private UserStockPageObject userStockPage;
	private UserMyProductReviewPageObject userMyProductReviewPage;
	private UserChangePasswordPageObject userChangePasswordPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on browserName: " + browserName);

		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		validEmail = "afc" + getRandomNumber() + "@mail.com";
		password = "123456";
		confirmPassword = "123456";

	}

	@Test
	public void User_01_Register() {

		userRegisterPage = userHomePage.openRegisterPage();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(validEmail);
		System.out.println(validEmail);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(confirmPassword);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userRegisterPage.clickToLogoutLink();

	}

	@Test
	public void User_02_Login() {

		userLoginPage = userHomePage.openLoginPage();

		userLoginPage.inputToEmailTextbox(validEmail);
		userLoginPage.inputToPasswordTextbox(password);

		userLoginPage.clickToLoginButton();

		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_My_Account() {
		userCustomerInfoPage = userHomePage.openCustomerInfoPage();
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		userAddressesPage = userCustomerInfoPage.openAddressesPage(driver);
		userRewardPointPage = userAddressesPage.openRewardPointPage(driver);
		userOrderPage = userRewardPointPage.openOrderPage(driver);
		userProductPage = userOrderPage.openProductPage(driver);
		userStockPage = userProductPage.openStockPage(driver);
		userMyProductReviewPage = userStockPage.openMyProductReviewPage(driver);
		userChangePasswordPage = userMyProductReviewPage.openChangePasswordPage(driver);
		userCustomerInfoPage = userChangePasswordPage.openCustomerInfoPage(driver);
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
