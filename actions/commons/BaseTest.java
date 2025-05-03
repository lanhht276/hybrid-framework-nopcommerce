package commons;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;


public class BaseTest {
	
	protected WebDriver driver;
	
	
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		Path path = null;
		File extensionFilePath = null;
		
		switch (browser) {
		case FIREFOX:
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-profile", "C:\\Users\\tho2_mantu\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\xv1afi70.Automation Test");
			driver = new FirefoxDriver(firefoxOptions);
			break;
			
		case CHROME:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--user-data-dir=C:/Users/tho2_mantu/AppData/Local/Google/Chrome/User Data");
			chromeOptions.addArguments("--profile-directory=Profile 2");
			driver = new ChromeDriver(chromeOptions);
		
			ChromeOptions options = new ChromeOptions();
			path = Paths.get(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.crx");
			extensionFilePath = new File(path.toUri());
			options.addExtensions(extensionFilePath);
			
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		
		default:
			throw new RuntimeException("BrowserName is invalid");
		}
		
		driver.manage().window().setPosition(new Point(0, 0));
		//driver.manage().window().setSize(new Dimension(1680,1050));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.get(GlobalConstants.USER_PAGE_URL);
		return driver;
		 
	}
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		Path path = null;
		File extensionFilePath = null;
		
		switch (browser) {
		case FIREFOX:
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-profile", "C:\\Users\\tho2_mantu\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\xv1afi70.Automation Test");
			driver = new FirefoxDriver(firefoxOptions);
			break;
			
		case CHROME:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--user-data-dir=C:/Users/tho2_mantu/AppData/Local/Google/Chrome/User Data");
			chromeOptions.addArguments("--profile-directory=Profile 2");
			driver = new ChromeDriver(chromeOptions);
		
			/*
			 * ChromeOptions options = new ChromeOptions(); path =
			 * Paths.get(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.crx");
			 * extensionFilePath = new File(path.toUri());
			 * options.addExtensions(extensionFilePath);
			 */
			
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		
		default:
			throw new RuntimeException("BrowserName is invalid");
		}
		
		driver.manage().window().setPosition(new Point(0, 0));
		//driver.manage().window().setSize(new Dimension(1680,1050));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.get(appUrl);
		return driver;
		 
	}
	protected String getEnvironmentUrl(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
		if (environment == EnvironmentList.DEV) {
			envUrl = "https://demo.nopcommerce.com/";
		} else if (environment == EnvironmentList.TESTING) {
			envUrl = "https://admin-demo.nopcommerce.com/";
		} else if (environment == EnvironmentList.STAGING) {
			envUrl = "https://staging-orangehrmlive.com/";
		} else if (environment == EnvironmentList.PRODUCTION) {
			envUrl = "https://production-orangehrmlive.com/";
		}
		
		return envUrl;
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	protected void closeBrowser() {
		driver.quit();
	}
}
