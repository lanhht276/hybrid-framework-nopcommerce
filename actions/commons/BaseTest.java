package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected WebDriver driver;
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		/*
		 * if (browser == BrowserList.FIREFOX) {
		 * WebDriverManager.firefoxdriver().setup(); driverBaseTest = new
		 * FirefoxDriver();
		 * 
		 * } else if (browser == BrowserList.CHROME) {
		 * WebDriverManager.chromedriver().setup(); driverBaseTest = new ChromeDriver();
		 * 
		 * } else if (browser == BrowserList.EDGE) {
		 * WebDriverManager.edgedriver().setup(); driverBaseTest = new EdgeDriver();
		 * 
		 * } else if (browser == BrowserList.COCCOC) {
		 * WebDriverManager.chromedriver().driverVersion("112.0.5615.28").setup();
		 * ChromeOptions options = new ChromeOptions(); options.
		 * setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
		 * driverBaseTest = new ChromeDriver(options);
		 * 
		 * } else if (browser == BrowserList.OPERA) {
		 * //System.setProperty("webdriver.opera.driver", projectPath +
		 * "\\browserDrivers\\operadriver.exe");
		 * 
		 * WebDriverManager.operadriver().setup();
		 * 
		 * 
		 * //ChromeOptions options = new ChromeOptions(); //options.setBinary(
		 * "C:\\Users\\HP\\AppData\\Local\\Programs\\Opera\\opera.exe"); driverBaseTest
		 * = new OperaDriver();
		 * 
		 * } else if (browser == BrowserList.BRAVE) {
		 * WebDriverManager.chromedriver().driverVersion("114.0.5735.16").setup();
		 * ChromeOptions options = new ChromeOptions(); options.
		 * setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"
		 * ); driverBaseTest = new ChromeDriver(options);
		 * 
		 * } else { throw new RuntimeException("BrowserName is invalid"); }
		 */
		
		switch (browser) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("112.0.5615.28").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			break;
		case BRAVE:
			WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
			ChromeOptions braveoptions = new ChromeOptions();
			braveoptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(braveoptions);
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		default:
			throw new RuntimeException("BrowserName is invalid");
		}
		
		driver.manage().window().setPosition(new Point(0, 0));
		//driver.manage().window().setSize(new Dimension(1920,1080));
		
		driver.manage().window().fullscreen();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(GlobalConstants.USER_PAGE_URL);
		return driver;
		 
	}
	protected void closeBrowser() {
		driver.close();
	}
}
