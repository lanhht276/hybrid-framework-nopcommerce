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
	
	private static final long timeout = 0;
	private JavascriptExecutor jsExecutor;
	Actions action;


	public void openPageUrl(WebDriver driver, String pageUrl) {
		
		driver.get(pageUrl);
	}
	
	public String getTitle(WebDriver driver) {
		
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		
		 driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		
		driver.navigate().refresh();
	}
	
	public Alert waitforAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
		
	public void acceptAlert(WebDriver driver) {
		waitforAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitforAlertPresence(driver).dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return waitforAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String textAlert) {
		waitforAlertPresence(driver).sendKeys(textAlert);
	}
	
	public String getWindowHandle(WebDriver driver, String parentID) {
		return driver.getWindowHandle();
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public WebElement getWebElement(WebDriver driver,String xpathlocator) {
		
		return driver.findElement(getByXpath(xpathlocator));
		
	}
	
	public List<WebElement> getListWebElement(WebDriver driver,String xpathlocator) {
		
		return driver.findElements(getByXpath(xpathlocator));
		
	}
	
	public By getByXpath(String xpathlocator) {
		
		return By.xpath(xpathlocator);
	}

	public void clickToElement(WebDriver driver, String xpathlocator) {
		getWebElement(driver, xpathlocator).click();
		
	}
	
	public void sendKeyToElement(WebDriver driver, String xpathlocator, String textValue) {
		WebElement element = getWebElement(driver, xpathlocator);
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	public void selectItemInDropdown(WebDriver driver, String xpathlocator, String textToSelect) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		select.selectByValue(textToSelect);
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, String xpathlocator) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathlocator, String textToSelect) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
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
	
	
	public String getElementAttribute(WebDriver driver, String xpathlocator, String attributeName) {
		return getWebElement(driver, xpathlocator).getAttribute(attributeName);
		
	}
	
	public String getElementText(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).getText();
		
	}
	
	public String getElementCssValue(WebDriver driver, String xpathlocator, String propertyName) {
		return getWebElement(driver, xpathlocator).getCssValue(propertyName);
		
	}
	
	public String getHexaColorFromRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
		
	}
	
	public int getElementSize(WebDriver driver,String xpathlocator) {
		
		return getListWebElement(driver, xpathlocator).size();
		
	}
	
	public void checkToDefaultCheckBoxOrRadio(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (!element.isSelected()){
			element.click();
			
		}
	}
	public void checkToCheckBoxOrRadio(WebDriver driver, WebElement element) {
		if (!element.isSelected() && element.isEnabled()){
			element.click();
			Assert.assertTrue(isElementSelected(element));
			
		}
	}
	
	public void unCheckToDefaultCheckBox(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (element.isSelected()){
			element.click();
			
		}
	}
	public void unCheckToCheckBoxOrRadio(WebElement checkbox) {
		if (checkbox.isSelected()){
			checkbox.click();
			
		}
	}
	
	public boolean isElementSelected (WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).isSelected(); 
	}
	public boolean isElementSelected (WebElement element) {
		return element.isSelected(); 
	}
	
	public boolean isControlDisplayed (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isDisplayed();
		
	}
	public boolean isControlSelected (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isSelected();
		
	}
	public boolean isControlEnabled (WebDriver driver, String xpathlocator) {
		return  getWebElement(driver, xpathlocator).isEnabled();
		
	}
	
	public void switchToIFrame (WebDriver driver, String xpathlocator) {
		driver.switchTo().frame(getWebElement(driver, xpathlocator));
		
	}
	
	public void switchToFrame (WebDriver driver, String xpathlocator) {
		driver.switchTo().frame(xpathlocator);
		
	}
	
	public void switchToDefaultContent (WebDriver driver) {
		driver.switchTo().defaultContent();
		
	}
	
	public void doubleClickToElement (WebDriver driver, String xpathlocator) {
		action = new Actions(driver);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathlocator));
		
		action.doubleClick(getWebElement(driver, xpathlocator)).perform();;
	}
	
	public void hoverMouseToElement (WebDriver driver, String xpathlocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathlocator)).perform();;
	}
	
	
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
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

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
		
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
		
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		
	}
	
	private long longTimeout = 30;
	
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
