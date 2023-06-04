package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
	
	private static final long timeout = 30;
	private JavascriptExecutor jsExecutor;
	Actions action;

	public static BasePage getBasePageObject() {
		return new BasePage();
		
	}
	protected void openPageUrl(WebDriver driver, String pageUrl) {
		
		driver.get(pageUrl);
	}
	
	protected String getTitle(WebDriver driver) {
		
		return driver.getTitle();
	}
	
	protected String getCurrentUrl(WebDriver driver) {
		
		return driver.getCurrentUrl();
	}
	
	protected String getPageSource(WebDriver driver) {
		
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		
		 driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage(WebDriver driver) {
		
		driver.navigate().refresh();
	}
	
	protected Alert waitforAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
		
	protected void acceptAlert(WebDriver driver) {
		waitforAlertPresence(driver).accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		waitforAlertPresence(driver).dismiss();
	}
	
	protected String getTextAlert(WebDriver driver) {
		return waitforAlertPresence(driver).getText();
	}
	
	protected void sendKeyToAlert(WebDriver driver, String textAlert) {
		waitforAlertPresence(driver).sendKeys(textAlert);
	}
	
	protected String getWindowHandle(WebDriver driver, String parentID) {
		return driver.getWindowHandle();
	}
	
	protected void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	private WebElement getWebElement(WebDriver driver,String xpathlocator) {
		
		return driver.findElement(getByXpath(xpathlocator));
		
	}
	
	private List<WebElement> getListWebElement(WebDriver driver,String xpathlocator) {
		
		return driver.findElements(getByXpath(xpathlocator));
		
	}
	
	private By getByXpath(String xpathlocator) {
		
		return By.xpath(xpathlocator);
	}

	protected void clickToElement(WebDriver driver, String xpathlocator) {
		getWebElement(driver, xpathlocator).click();
		
	}
	
	protected void sendKeyToElement(WebDriver driver, String xpathlocator, String textValue) {
		WebElement element = getWebElement(driver, xpathlocator);
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	protected void selectItemInDropdown(WebDriver driver, String xpathlocator, String textToSelect) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		select.selectByValue(textToSelect);
	}
	
	protected String getSelectedItemInDropdown(WebDriver driver, String xpathlocator) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropdownMultiple(WebDriver driver, String xpathlocator, String textToSelect) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.isMultiple();
	}
	
	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, timeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	
	protected String getElementAttribute(WebDriver driver, String xpathlocator, String attributeName) {
		return getWebElement(driver, xpathlocator).getAttribute(attributeName);
		
	}
	
	protected String getElementText(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).getText();
		
	}
	
	protected String getElementCssValue(WebDriver driver, String xpathlocator, String propertyName) {
		return getWebElement(driver, xpathlocator).getCssValue(propertyName);
		
	}
	
	protected String getHexaColorFromRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
		
	}
	
	protected int getElementSize(WebDriver driver,String xpathlocator) {
		
		return getListWebElement(driver, xpathlocator).size();
		
	}
	
	protected void checkToDefaultCheckBoxOrRadio(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (!element.isSelected()){
			element.click();
			
		}
	}
	protected void checkToCheckBoxOrRadio(WebDriver driver, WebElement element) {
		if (!element.isSelected() && element.isEnabled()){
			element.click();
			Assert.assertTrue(isElementSelected(element));
			
		}
	}
	
	protected void unCheckToDefaultCheckBox(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (element.isSelected()){
			element.click();
			
		}
	}
	protected void unCheckToCheckBoxOrRadio(WebElement checkbox) {
		if (checkbox.isSelected()){
			checkbox.click();
			
		}
	}
	
	protected boolean isElementSelected (WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).isSelected(); 
	}
	protected boolean isElementSelected (WebElement element) {
		return element.isSelected(); 
	}
	
	protected boolean isControlDisplayed (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isDisplayed();
		
	}
	protected boolean isControlSelected (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isSelected();
		
	}
	protected boolean isControlEnabled (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isEnabled();
		
	}
	
	protected void switchToIFrame (WebDriver driver, String xpathlocator) {
		driver.switchTo().frame(getWebElement(driver, xpathlocator));
		
	}
	
	protected void switchToFrame (WebDriver driver, String xpathlocator) {
		driver.switchTo().frame(xpathlocator);
		
	}
	
	protected void switchToDefaultContent (WebDriver driver) {
		driver.switchTo().defaultContent();
		
	}
	
	protected void doubleClickToElement (WebDriver driver, String xpathlocator) {
		action = new Actions(driver);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathlocator));
		
		action.doubleClick(getWebElement(driver, xpathlocator)).perform();;
	}
	
	protected void hoverMouseToElement (WebDriver driver, String xpathlocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathlocator)).perform();;
	}
	
	
	protected void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public  Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	
	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
		
	}
	
	protected void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
		
	}
	
	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		
	}
	
	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		
	}
	
	protected long longTimeout = 30;
	
	

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
