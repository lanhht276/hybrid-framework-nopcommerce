package com.jquery;


import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import graphql.Assert;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;


public class Level_11_Upload_Files extends BaseTest{
	
	private WebDriver driver;
	private HomePageObject homePage;

	String beachFileName = "Beach.jpg";
	String hillFileName = "Hill.jpg";
	String noelFileName = "Noel.jpg";
	String[] multipleFileNames = { beachFileName,hillFileName, noelFileName };
	
	
	@Parameters({"browser", "url"})
	
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		System.out.println("Run on browserName: " + browserName);
		driver = getBrowserDriver(browserName, appUrl);
		homePage= PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, beachFileName);
		Assert.assertTrue(homePage.isFileLoadedByName(beachFileName));	
		homePage.clickToStartButton();
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isFileLinkUploadedByName(beachFileName));
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isFileImageUploadedByName(beachFileName), "Anh chua đuocc load đung.");
		homePage.sleepInSecond(3);
	}
  
	@Test
	public void Upload_02_Multiple_Files_Per_Time() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadMultipleFiles(driver, multipleFileNames);
		Assert.assertTrue(homePage.isFileLoadedByName(beachFileName));
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isFileLoadedByName(hillFileName));
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isFileLoadedByName(noelFileName));	
		homePage.sleepInSecond(2);
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUploadedByName(beachFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(hillFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(noelFileName));
		
		Assert.assertTrue(homePage.isFileImageUploadedByName(beachFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(hillFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(noelFileName));
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



