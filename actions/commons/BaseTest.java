package commons;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest {
	
	protected WebDriver driver;
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		
		default:
			throw new RuntimeException("BrowserName is invalid");
		}
		
		driver.manage().window().setPosition(new Point(0, 0));
		//driver.manage().window().setSize(new Dimension(1920,1080));
		
		driver.manage().window().fullscreen();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(GlobalConstants.USER_PAGE_URL);
		return driver;
		 
	}
	protected void closeBrowser() {
		driver.close();
	}
}
