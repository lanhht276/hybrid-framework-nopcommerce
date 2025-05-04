package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement registerLink;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLink;

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement invalidAccountMessage;

	@FindBy(id = "Email-error")
	private WebElement errorMessageEmailTextbox;

	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public void inputToEmailTextbox(String email) {
		sendKeyToElement(driver, emailTextbox, email);
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, passwordTextbox, password);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, errorMessageEmailTextbox);
	}

	public String getNotFoundAccountlMessage() {
		return getElementText(driver, invalidAccountMessage);
	}

	public String getInvalidErrorMessage() {
		return getElementText(driver, invalidAccountMessage);
	}
}
