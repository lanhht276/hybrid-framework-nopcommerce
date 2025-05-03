package com.jquery;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;
import pageUI.jQuery.dataTable.HomePageUI;


public class Level_10_DataTable_DataGrid extends BaseTest{
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expecteddAllCountryValues;
	
	@Parameters({"browser", "url"})
	
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		System.out.println("Run on browserName: " + browserName);
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	
	}

	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
	}
  
	
	public void Table_02_Enter_To_Header() {
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		homePage.sleepInSecond(1);
		homePage.enterToHeaderTextboxByLabel("Country", "Australia");
		homePage.enterToHeaderTextboxByLabel("Females", "145412");
		homePage.enterToHeaderTextboxByLabel("Males", "154696");
		homePage.enterToHeaderTextboxByLabel("Total", "300109");
		homePage.sleepInSecond(1);
		
	}
	
	public void Table_03_Enter_To_Header() {
		
		
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expecteddAllCountryValues);
	}
	
	
	public void Table_04_Action_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(3);
		
		  homePage.enterToTextBoxByColumnNameAtRowNumber("Company","1",
		  "Germany company");
		  
		  homePage.enterToTextBoxByColumnNameAtRowNumber("Contact Person","1",
		  "LanhHT");
		  
		  homePage.enterToTextBoxByColumnNameAtRowNumber("Order Placed","1", "1000");
		  
		  homePage.enterToCalendarByColumnNameAtRowNumber("Member Since", "1",
		  "2000-06-20");
		  
		  homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Germany" );
		  homePage.sleepInSecond(1);
		  homePage.selectDropdownByColumnNameAtRowNumber("Country", "3", "Taiwan" );
		  homePage.sleepInSecond(1);
		  homePage.selectDropdownByColumnNameAtRowNumber("Country", "2", "Hong Kong" );
		  homePage.sleepInSecond(1);
		  homePage.enterToCalendarByColumnNameAtRowNumber("Member Since", "2",
		  "2023-06-28");
		  
		  homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "2");
		  homePage.sleepInSecond(1);
		  homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "3");
		  homePage.sleepInSecond(1);
		  homePage.uncheckToCheckBoxByColumnNameAtRowNumber("NPO?", "1");
		  homePage.sleepInSecond(1);
		  homePage.uncheckToCheckBoxByColumnNameAtRowNumber("NPO?", "4");
		  homePage.sleepInSecond(1);
		 
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("1", "Insert Row Above");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("3", "Move Up");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("5", "Remove Current Row");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("4", "Remove Current Row");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("3", "Remove Current Row");		
		homePage.sleepInSecond(1);

		homePage.clickToIconByRowNumber("2", "Remove Current Row");		
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("4", "Remove Current Row");		
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("3", "Remove Current Row");		
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");		
		homePage.sleepInSecond(1);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");		
		homePage.sleepInSecond(1);
		
	}
	

	@Test
	public void Table_05_Demo_Register_And_Check_AdminPage() {
		
		homePage.clickToAccountLink();		
		homePage.clickToLinkFromTheAcountLink("Register");	
		
		homePage.sendKeyToFieldInAccountPage("First Name", "Super QC");
		homePage.sendKeyToFieldInAccountPage("Last Name", "Test 19925");
		//homePage.sendKeyToFieldInAccountPage("Email Address", "afc" + getRandomNumber() + "@mail.com" );
		homePage.sendKeyToFieldInAccountPage("Email Address", "afc19925@mail.com" );
		homePage.sendKeyToFieldInAccountPage("Password", "SuperQCTest1234");
		homePage.sendKeyToFieldInAccountPage("Confirm Password", "SuperQCTest1234");
		
		homePage.clickToRegisterButton();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isSuccessMessageDisplayed());
		homePage.sleepInSecond(5);
		homePage.clickToAccountLink();		
		homePage.clickToLinkFromTheAcountLink("Log Out");	
		
		//Admin page verify
		driver.get("https://live.techpanda.org/index.php/backendlogin");
		homePage.sendKeyToFieldInAdminPage("username", "user01");
		homePage.sendKeyToFieldInAdminPage("login", "guru99com");
		homePage.clickToLoginButton();
		homePage.sleepInSecond(3);
		homePage.clickToClosePopup();
		homePage.sleepInSecond(3);
		
		Assert.assertTrue(homePage.isNameAndEmailDisplayed("3", "Super QC Test 19925"));
		Assert.assertTrue(homePage.isNameAndEmailDisplayed("4", "afc19925@mail.com"));
		
		homePage.clickToLogOutButton();
	}
	
	@AfterClass
	public void afterClass() {
		//driver.close();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}



