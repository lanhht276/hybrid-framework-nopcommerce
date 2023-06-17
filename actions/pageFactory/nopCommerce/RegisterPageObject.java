package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;
	 
	public RegisterPageObject(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameErrorMsg;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameErrorMsg;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMsg;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordErrorMsg;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMsg;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMsg;
	
	@FindBy(xpath = "//a[contains(@class, 'register-continue-button')]")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement invalidEmailErrorMsg;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton );
		clickToElement(driver, registerButton);
	}
	public void inputToFirstNameTextbox(String firstName) {
		sendKeyToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		sendKeyToElement(driver, lastNameTextbox, lastName);	
	}

	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, emailTextbox, email);
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, passwordTextbox, password);	
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		sendKeyToElement(driver, confirmPasswordTextbox, confirmPassword);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		return getElementText(driver, firstNameErrorMsg);
	}

	public String getErrorMessageAtLastNameTextbox() {
		return getElementText(driver, lastNameErrorMsg);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, emailErrorMsg);
	}
	
	public String getInvalidErrorMessageEmail() {
		return getElementText(driver, invalidEmailErrorMsg);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, passwordErrorMsg);
	}
	
	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(driver, confirmPasswordErrorMsg);
	}

	public String getRegisterSuccessMessage() {
		return getElementText(driver, registerSuccessMsg);
	}

	public void clickToContinueButton() {
		waitForElementClickable(driver, continueButton);
		clickToElement(driver, continueButton);
	}
}
