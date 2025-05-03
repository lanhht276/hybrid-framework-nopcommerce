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

public class TestCase05_NopCommerce_Wishlist_Compare_RecentView extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword, company, country, stateProvince, city,
			address1, address2, zipPostalCode, phoneNumber, faxNumber, updatedFirstName, updatedLastName,
			updatedEmailAddress, newPassword, productMacbook, productCamera;
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
		
		productMacbook = "Apple MacBook Pro";
		productCamera = "Apple iCam";

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

		userLoginPage = userHomePage.openLoginPage();
		userLoginPage.inputToFieldInLoginPage("Email", emailAddress);
		System.out.println("email in TC is: " + emailAddress);
		userLoginPage.inputToFieldInLoginPage("Password", password);
		System.out.println("pw in TC is: " + password);

		userHomePage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(userHomePage.isHeaderNameDisplayed("account"));
		userHomePage.clickToProductInHomePage(productMacbook);
	}

	@Test
	public void TC_01_Add_To_Wishlist() {
		userHomePage.clickAddToWishlistButton();
		Assert.assertEquals(userHomePage.getSuccessMessageDisplayed(), "The product has been added to your wishlist");
		userHomePage.clickToCloseMessage();
		userHomePage.clickToLinkName("Wishlist");
		Assert.assertTrue(userHomePage.isLinkNameDisplayed(productMacbook));
		userHomePage.clickToSharingLink();
		Assert.assertEquals(userHomePage.getPageTitle(), "Wishlist of" + " " + firstName + " " + lastName);
		userHomePage.backToPage(driver);
	}

	@Test
	public void  TC_02_Add_Product_To_Cart_From_Wishlist_Page() {
		userHomePage.clickToDynamicLinkName("wishlist");
		userHomePage.checkToCheckBox("addtocart");
		userHomePage.sleepInSecond(3);
		userHomePage.clickToButon("buttons", "Add to cart");
		userHomePage.clickToDynamicLinkName("wishlist");
		Assert.assertEquals(userHomePage.getPageTitle(), "Wishlist");
		Assert.assertEquals(userHomePage.getPageBodyEmpty(), "The wishlist is empty!");
		userHomePage.clickToDynamicLinkName("cart");
		Assert.assertEquals(userHomePage.getPageTitle(), "Shopping cart");
		Assert.assertEquals(userHomePage.getProductInCart(), productMacbook );
		userHomePage.clickToRemoveButtonFromCartPage();
	}

	@Test
	public void TC_03_Remove_Product_In_Wishlist_Page() {
		userHomePage.clickToLHeaderLogo();
		userHomePage.hoverOnMenu("Electronics");
		userHomePage.clickToLinkName("Camera & photo ");
		userHomePage.clickToProductInHomePage(productCamera);
		userHomePage.clickAddToWishlistButton();
		Assert.assertEquals(userHomePage.getSuccessMessageDisplayed(), "The product has been added to your wishlist");
		userHomePage.clickToCloseMessage();
		userHomePage.clickToLinkName("Wishlist");
		Assert.assertTrue(userHomePage.isLinkNameDisplayed(productCamera));
		userHomePage.checkToCheckBox("addtocart");
		userHomePage.clickToRemoveButtonFromCartPage();
		userHomePage.clickToDynamicLinkName("wishlist");
		Assert.assertEquals(userHomePage.getPageTitle(), "Wishlist");
		Assert.assertEquals(userHomePage.getPageBodyEmpty(), "The wishlist is empty!");
	}

	@Test
	public void  TC_04_Add_Product_To_Compare() {	
		userHomePage.clickToLHeaderLogo();
		userHomePage.clickToProductItemButton("1", "Add to compare list");
		userHomePage.sleepInSecond(2);
		userHomePage.clickToProductItemButton("3", "Add to compare list");
		Assert.assertEquals(userHomePage.getSuccessMessageDisplayed(), "The product has been added to your product comparison");
		userHomePage.clickToLinkName("product comparison");
		Assert.assertEquals(userHomePage.getPageTitle(), "Compare products");
		Assert.assertEquals(userHomePage.getNumberOfProductNameInCompareProductPage(), 2);
		Assert.assertEquals(userHomePage.getNumberOfRemoveButtonInCompareProductPage(), 2);
		Assert.assertEquals(userHomePage.getNumberOfPriceInCompareProductPage(), 2);
		Assert.assertTrue(userHomePage.isLinkNameDisplayed("Clear list"));
		userHomePage.clickToLinkName("Clear list");
		Assert.assertEquals(userHomePage.getPageBodyEmpty(), "You have no items to compare.");
		//verify undisplayed
	}
	
	
	@Test
	public void TC_05_() {
		
	}
	@Test
	public void TC_06_() {
	
	}
	@Test
	public void TC_07_() {
		
	}
	@Test
	public void TC_08_() {
	
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
