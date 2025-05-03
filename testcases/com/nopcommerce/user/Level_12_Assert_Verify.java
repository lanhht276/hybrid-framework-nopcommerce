package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;

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

public class Level_12_Assert_Verify extends BaseTest{
	private WebDriver driver;
	private String firstName,lastName,validEmail, password,confirmPassword;
	
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
	public void User_01_Register_Login() {
		
		userRegisterPage = userHomePage.openRegisterPage();
		
		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(validEmail);
		//System.out.println(validEmail);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(confirmPassword);
			
		userRegisterPage.clickToRegisterButton();

		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");
	
		userRegisterPage.clickToLogoutLink();
		
		userLoginPage = userHomePage.openLoginPage();
		
		userLoginPage.inputToEmailTextbox(validEmail);
		userLoginPage.inputToPasswordTextbox(password);
		
		userLoginPage.clickToLoginButton();
		verifyFalse(userHomePage.isMyAccountLinkDisplayed());
	
		userCustomerInfoPage = userHomePage.openCustomerInfoPage();
		verifyTrue(userCustomerInfoPage.isCustomerInfoPageDisplayed());
	}
  
	@Test
	public void User_02_Switch_Page() {
		userAddressesPage = userCustomerInfoPage.openAddressesPage(driver);
		userRewardPointPage = userAddressesPage.openRewardPointPage(driver);
		userOrderPage = userRewardPointPage.openOrderPage(driver);
		userProductPage = userOrderPage.openProductPage(driver);
		userStockPage = userProductPage.openStockPage(driver);
		userChangePasswordPage = userStockPage.openChangePasswordPage(driver);
		userMyProductReviewPage = userChangePasswordPage.openMyProductReviewPage(driver);
	}
	@Test
	public void User_03_Dynamic_Page_01() {
		userChangePasswordPage = (UserChangePasswordPageObject) userMyProductReviewPage.openPagesAtMyAccountPageByName(driver,"Change password");
		userCustomerInfoPage = (UserCustomerInfoPageObject) userChangePasswordPage.openPagesAtMyAccountPageByName(driver,"Customer info");
		userAddressesPage = (UserAddressesPageObject) userCustomerInfoPage.openPagesAtMyAccountPageByName(driver,"Addresses");
		userRewardPointPage = (UserRewardPointPageObject) userAddressesPage.openPagesAtMyAccountPageByName(driver,"Reward points");
		userOrderPage = (UserOrderPageObject) userRewardPointPage.openPagesAtMyAccountPageByName(driver,"Orders");
		userProductPage = (UserProductPageObject) userOrderPage.openPagesAtMyAccountPageByName(driver,"Downloadable products");
		userStockPage = (UserStockPageObject) userProductPage.openPagesAtMyAccountPageByName(driver,"Back in stock subscriptions");
		userMyProductReviewPage = (UserMyProductReviewPageObject) userStockPage.openPagesAtMyAccountPageByName(driver,"My product reviews");
	}
	
	@Test
	public void User_03_Dynamic_Page_02() {
		userMyProductReviewPage.openPagesAtMyAccountPageByName(driver, "Change password");
		userChangePasswordPage= PageGeneratorManager.getUserChangePasswordPage(driver);
		
		userChangePasswordPage.openPagesAtMyAccountPageByName(driver,"Customer info");
		userCustomerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
		
		userCustomerInfoPage.openPagesAtMyAccountPageByName(driver,"Addresses");
		userAddressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		
		userAddressesPage.openPagesAtMyAccountPageByName(driver,"Reward points");
		userRewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		userRewardPointPage.openPagesAtMyAccountPageByName(driver,"Orders");
		userOrderPage = PageGeneratorManager.getUserOrderPage(driver);
		
		userOrderPage.openPagesAtMyAccountPageByName(driver,"Downloadable products");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);
		
		userProductPage.openPagesAtMyAccountPageByName(driver,"Back in stock subscriptions");
		userStockPage = PageGeneratorManager.getUserStockPage(driver);
		
		userStockPage.openPagesAtMyAccountPageByName(driver,"My product reviews");
		userMyProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
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
