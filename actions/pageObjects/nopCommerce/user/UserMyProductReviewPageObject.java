package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.UserProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage{
		private WebDriver driver;
		
		public UserMyProductReviewPageObject(WebDriver driver) {
			 this.driver = driver;	 
		 }

		public String getProductReview(String fieldName) {
			waitForAllElementVisible(driver, UserProductReviewPageUI.FIELD_IN_REVIEW_PAGE, fieldName);
			return getElementText(driver, UserProductReviewPageUI.FIELD_IN_REVIEW_PAGE, fieldName);
		}
}
