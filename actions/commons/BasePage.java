package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrderPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.nopCommerce.user.UserStockPageObject;
import pageUI.jQuery.uploadFile.BasePageJQueryUI;
import pageUI.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {
	
	private JavascriptExecutor jsExecutor;
	Actions action;

	public static BasePage getBasePageObject() {
		return new BasePage();
		
	}
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
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
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
	private String getDynamicXpath(String locatorType, String...dynamicValues  ) {
		if (locatorType.startsWith("xpath=")||locatorType.startsWith("XPATH=")||locatorType.startsWith("Xpath=")||locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[])dynamicValues);
		}
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver,String locatorType) {
		return driver.findElement(getByLocator(locatorType));
		
	}
	
	public WebElement getWebElement(WebDriver driver,String locatorType,String...dynamicValues  ) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
		
	}
	
	public List<WebElement> getListWebElement(WebDriver driver,String locatorType) {
		return driver.findElements(getByLocator(locatorType));
		
	}
	
	private By getByLocator(String locatorType) {
		if (locatorType == null || locatorType.trim().isEmpty()) {
			throw new IllegalArgumentException("Locator type cannot be null or empty.");
		}
		
		String locator = locatorType.toLowerCase();
		
		switch (locator.split("=")[0]) {
			case "id":
				return By.id(locatorType.substring(3));
			case "class":
				return By.className(locatorType.substring(6));
			case "name":
				return By.name(locatorType.substring(5));
			case "css":
				return By.cssSelector(locatorType.substring(4));
			case "xpath":
				return By.xpath(locatorType.substring(6));
			default:
				throw new RuntimeException("Locator type is not supported: " + locatorType);
		}
		
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locatorType, String textToSelect) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textToSelect);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locatorType, String textToSelect,  String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textToSelect);
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType, String textToSelect) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

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
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    WebElement element = getWebElement(driver, locatorType);
	    
	    return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attributeName);
	}
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attributeName);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver,String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	public int getElementSize(WebDriver driver,String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckBoxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()){
			element.click();
		}
	}
	public void checkToDefaultCheckBoxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
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
	
	public void unCheckToDefaultCheckBox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()){
			element.click();
			
		}
	}
	public void unCheckToDefaultCheckBox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()){
			element.click();
			
		}
	}
	public void unCheckToCheckBoxOrRadio(WebElement checkbox) {
		if (checkbox.isSelected()){
			checkbox.click();
		}
	}
	
	public boolean isElementSelected (WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected(); 
	}
	public boolean isElementSelected (WebElement element) {
		return element.isSelected(); 
	}
	
	public boolean isControlDisplayed (WebDriver driver, String locatorType) {
		return  getWebElement(driver, locatorType).isDisplayed();
		
	}
	public boolean isControlDisplayed (WebDriver driver, String locatorType, String... dynamicValues) {
		return  getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
		
	}
	public boolean isControlSelected (WebDriver driver, String locatorType) {
		return  getWebElement(driver, locatorType).isSelected();
		
	}
	public boolean isControlEnabled (WebDriver driver, String locatorType) {
		return  getWebElement(driver, locatorType).isEnabled();
	}
	
	public void switchToIFrame (WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));	
	}
	
	public void switchToFrame (WebDriver driver, String locatorType) {
		driver.switchTo().frame(locatorType);
	}
	
	public void switchToDefaultContent (WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickToElement (WebDriver driver, String locatorType) {
		action = new Actions(driver);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
		
		action.doubleClick(getWebElement(driver, locatorType)).perform();;
	}
	
	public void hoverMouseToElement (WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();;
	}
	public void hoverMouseToElement (WebDriver driver, String locatorType,  String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();;
	}
	
	public void pressKeyToElement (WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();;
	}
	
	public void pressKeyToElement (WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();;
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle =(String) jsExecutor.executeScript("return arguments[0].getAttribute('style');", element);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
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

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
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

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));	
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));	
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	} 
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));	
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));	
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	public void uploadMultipleFiles(WebDriver driver, String...fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String file: fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	
	public long longTimeout = GlobalConstants.LONG_TIMEOUT;
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	public UserAddressesPageObject openAddressesPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.ADDRESSES_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}
	public UserOrderPageObject openOrderPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.ORDERS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ORDERS_LINK);
		return PageGeneratorManager.getUserOrderPage(driver);
	}
	public UserProductPageObject openProductPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DOWNLOADABLE_PRODUCTS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.DOWNLOADABLE_PRODUCTS_LINK);
		return PageGeneratorManager.getUserProductPage(driver);
	}
	public UserStockPageObject openStockPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.BACK_IN_STOCK_LINK);
		clickToElement(driver, BasePageNopCommerceUI.BACK_IN_STOCK_LINK);
		return PageGeneratorManager.getUserStockPage(driver);
	}
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable (driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER_PAGE);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable (driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public BasePage openPagesAtMyAccountPageByName(WebDriver driver, String pageName) {
		waitForElementClickable (driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressesPage(driver);
		case "Orders":
			return PageGeneratorManager.getUserOrderPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getUserProductPage(driver);
		case "Back in stock subscriptions":
			return PageGeneratorManager.getUserStockPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
			
		default:
			throw new RuntimeException("Invalid page name at My Account area.");
		}
	}
	
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
