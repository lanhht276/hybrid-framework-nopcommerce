package commons;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private static final long timeout = GlobalConstants.LONG_TIMEOUT;

	Actions action;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	protected void sendKeyToElement(WebDriver driver, WebElement element, String textValue) {
		// WebElement element = getWebElement(driver, element);
		element.clear();
		element.sendKeys(textValue);
	}

	protected boolean isControlDisplayed(WebDriver driver, WebElement element) {
		waitForElementVisible(driver, element);
		return element.isDisplayed();
	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

}
