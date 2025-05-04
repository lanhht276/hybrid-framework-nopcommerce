package pageUI.nopCommerce.user;

public class UserRegisterPageUI {
	public static final String FIRSTNAME_TEXTBOX = "id=FirstName";
	public static final String LASTNAME_TEXTBOX = "id=LastName";
	public static final String EMAIL_TEXTBOX = "id=Email";
	public static final String PASSWORD_TEXTBOX = "id=Password";
	public static final String CONFIRMPASSWORD_TEXTBOX = "id=ConfirmPassword";
	public static final String REGISTER_BUTTON = "id=register-button";

	public static final String TEXTBOX_IN_REGISTER_PAGE = "xpath=//input[@id='%s']";

	public static final String FIRSTNAME_ERROR_MSG = "id=FirstName-error";
	public static final String LASTNAME_ERROR_MSG = "id=LastName-error";
	public static final String EMAIL_ERROR_MSG = "id=Email-error";
	public static final String PASSWORD_ERROR_MSG = "id=Password-error";
	public static final String CONFIRM_PASSWORD_ERROR_MSG = "id=ConfirmPassword-error";
	public static final String REGISTER_SUCCESS_MSG = "css=div[class='result']";

	public static final String INVALID_EMAIL_ERROR_MSG = "css=div[class='message-error validation-summary-errors']>ul>li";

	public static final String TEXTBOX_ERROR_MSG = "xpath=//span[@id='%s-error']";

}
