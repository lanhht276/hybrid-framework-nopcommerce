package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageUI.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;


	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		waitForAllElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);

	}

	public boolean isPageNumberActived(String pageNumber) {
		waitForAllElementVisible(driver, HomePageUI.PAGINATION_ACTIVE_PAGE_BY_NUMBER, pageNumber);
		return isControlDisplayed(driver, HomePageUI.PAGINATION_ACTIVE_PAGE_BY_NUMBER, pageNumber);
	}

	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);

		List<String> allRowValueAllPage = new ArrayList<String>();
		for (int index = 1; index <= totalPage; index++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
			sleepInSecond(1);
			
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowValueAllPage.add(eachRow.getText());
			}

		}
		for (String value: allRowValueAllPage) {
			System.out.println("--------------------------");
			System.out.println(value);
		}
		return allRowValueAllPage;
	}

	public void enterToTextBoxByColumnNameAtRowNumber(String columnName, String rowNumber, String valueToEnter) {
		//column index dua vao ten cot
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		//send key vao dong nao
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToEnter, rowNumber, String.valueOf(columnIndex));
	}
	public void enterToCalendarByColumnNameAtRowNumber(String columnName, String rowNumber, String valueToEnter) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		WebElement calendarTextbox = getWebElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + valueToEnter + "'", calendarTextbox);
	}


	public void selectDropdownByColumnNameAtRowNumber(String columnName,  String rowNumber, String valueToSelect) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToSelect, rowNumber, String.valueOf(columnIndex));
	}

	public void clickToLoadButton() {
		waitForElementVisible(driver, HomePageUI.LOAD_BUTTON);
		clickToElement(driver, HomePageUI.LOAD_BUTTON);
	}

	public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		checkToDefaultCheckBoxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		
	}

	public void uncheckToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		unCheckToDefaultCheckBox(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowNumber, String.valueOf(columnIndex));
		
	}

	public void clickToIconByRowNumber(String rowNumber,String iconName ) {
		waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber, iconName);
		clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber, iconName);
		
	}

	public void clickToAccountLink() {
		waitForElementClickable(driver, HomePageUI.ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
		
	}

	public void clickToLinkFromTheAcountLink(String linkName) {
		waitForElementClickable(driver, HomePageUI.LINK_FORM_THE_ACCOUNT, linkName);
		clickToElement(driver, HomePageUI.LINK_FORM_THE_ACCOUNT, linkName);
	}

	public void sendKeyToFieldInAccountPage(String fieldName, String valueToInput) {
		waitForElementClickable(driver, HomePageUI.FIELD_TO_INPUT_IN_ACCOUNT_PAGE,fieldName);
		sendKeyToElement(driver, HomePageUI.FIELD_TO_INPUT_IN_ACCOUNT_PAGE,valueToInput, fieldName);
		
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, HomePageUI.REGISTER_BUTTON);
		clickToElement(driver, HomePageUI.REGISTER_BUTTON);
		
	}

	public boolean isSuccessMessageDisplayed() {
		waitForAllElementVisible(driver, HomePageUI.SUCCESS_MESSAGE);
		return isControlDisplayed(driver, HomePageUI.SUCCESS_MESSAGE);
	}

	public void sendKeyToFieldInAdminPage(String fieldName, String valueToInput) {
		waitForElementClickable(driver, HomePageUI.FIELD_TO_INPUT_IN_ADMIN_PAGE, fieldName);
		sendKeyToElement(driver, HomePageUI.FIELD_TO_INPUT_IN_ADMIN_PAGE,valueToInput, fieldName);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, HomePageUI.LOGIN_BUTTON);
		clickToElement(driver, HomePageUI.LOGIN_BUTTON);
		
	}

	public void clickToClosePopup() {
		waitForElementClickable(driver, HomePageUI.CLOSE_BUTTON	);
		clickToElement(driver, HomePageUI.CLOSE_BUTTON);
		
	}

	public boolean isNameAndEmailDisplayed(String columnNumber, String valueDisplayed) {
		waitForAllElementVisible(driver, HomePageUI.TEXTBOX_BY_ROW_INDEX, columnNumber);
		return isControlDisplayed(driver, HomePageUI.TEXTBOX_BY_ROW_INDEX, columnNumber, valueDisplayed );
	
	}

	public void clickToLogOutButton() {
		waitForElementClickable(driver, HomePageUI.LOGOUT_BUTTON);
		clickToElement(driver, HomePageUI.LOGOUT_BUTTON);

	}


}

