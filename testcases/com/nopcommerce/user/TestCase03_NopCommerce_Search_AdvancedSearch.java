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
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class TestCase03_NopCommerce_Search_AdvancedSearch extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword, product;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "QC test";
		lastName = "FC1234";
		emailAddress = "afc" + getRandomNumber() + "@mail.com";
		password = "123456";
		confirmPassword = "123456";
		product = "Apple MacBook Pro";

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
		userHomePage.clickToLinkName("Search");
	}

	@Test
	public void TC_01_Search_with_Empty_Data() {
		userHomePage.clickToButon("search-input", "Search");
		userHomePage.sleepInSecond(2);
		Assert.assertEquals(userHomePage.getSearchErrorMessage(), "Search term minimum length is 3 characters");
		userHomePage.sleepInSecond(2);
	}

	@Test
	public void TC_02_Search_With_Data_Not_Exist() {
		userHomePage.inputToTextbox("input", "q", "macbook 2050");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getSearchErrorMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_03_Search_Partial_With_Product_Name() {
		userHomePage.inputToTextbox("input", "q", "Lenovo");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getNumberResult(), 2);

		List<WebElement> results = userHomePage.getResult();
		boolean found1 = false;
		boolean found2 = false;

		for (WebElement result : results) {
			String text = result.getText();
			if (text.contains("Lenovo IdeaCentre")) {
				found1 = true;
			}
			if (text.contains("Lenovo Thinkpad Carbon Laptop")) {
				found2 = true;
			}
		}
		Assert.assertTrue(found1, "Result 1 is not found!");
		Assert.assertTrue(found2, "Result 2 is not found!");
	}

	@Test
	public void TC_04_Search_Exactly_With_Product_Name() {
		userHomePage.inputToTextbox("input", "q", "Lenovo Thinkpad Carbon Laptop");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getNumberResult(), 1);
		Assert.assertEquals(userHomePage.getProductName(), "Lenovo Thinkpad Carbon Laptop");
		/*
		 * List<WebElement> results = userHomePage.getResult(); boolean foundProduct =
		 * false; for (WebElement result : results) { String text = result.getText(); if
		 * (text.contains("Lenovo Thinkpad Carbon Laptop")) { foundProduct = true; } }
		 * Assert.assertTrue(foundProduct, "Product is not found!");
		 */
	}

	// userHomePage.checkToCheckBox("isc");
	// userHomePage.checkToCheckBox("sid");
	@Test
	public void TC_05_Advanced_Search_With_Parent_Categories() {
		userHomePage.inputToTextbox("input", "q", product);
		userHomePage.checkToCheckBox("advs");
		userHomePage.clickToDropdown("cid", "Computers");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getSearchErrorMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Advanced_Search_With_Sub_Categories() {
		userHomePage.inputToTextbox("input", "q", product);
		userHomePage.checkToCheckBox("advs");
		userHomePage.clickToDropdown("cid", "Computers");
		userHomePage.checkToCheckBox("isc");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getNumberResult(), 1);
		Assert.assertEquals(userHomePage.getProductName(), "Apple MacBook Pro");
		/*
		 * List<WebElement> results = userHomePage.getResult(); boolean foundProduct =
		 * false; for (WebElement result : results) { String text = result.getText(); if
		 * (text.contains("Apple MacBook Pro")) { foundProduct = true; } }
		 * Assert.assertTrue(foundProduct, "Product is not found!");
		 */
	}

	@Test
	public void TC_07_Advanced_Search_With_Incorrect_Manufacturer() {
		userHomePage.inputToTextbox("input", "q", "Apple Macbook Pro");
		userHomePage.checkToCheckBox("advs");
		userHomePage.clickToDropdown("cid", "Computers");
		userHomePage.checkToCheckBox("isc");
		userHomePage.clickToDropdown("mid", "HP");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getSearchErrorMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Advanced_Search_With_Correct_Manufacturer() {
		userHomePage.inputToTextbox("input", "q", "Apple Macbook Pro");
		userHomePage.checkToCheckBox("advs");
		userHomePage.clickToDropdown("cid", "Computers");
		userHomePage.checkToCheckBox("isc");
		userHomePage.clickToDropdown("mid", "Apple");
		userHomePage.clickToButon("search-input", "Search");
		Assert.assertEquals(userHomePage.getNumberResult(), 1);
		Assert.assertEquals(userHomePage.getProductName(), "Apple MacBook Pro");

		/*
		 * List<WebElement> results = userHomePage.getResult(); boolean foundProduct =
		 * false; for (WebElement result : results) { String text = result.getText(); if
		 * (text.contains("Apple MacBook Pro")) { foundProduct = true; } }
		 * Assert.assertTrue(foundProduct, "Product is not found!");
		 */
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
