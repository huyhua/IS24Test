package com.nvg.IS24.appium.pageObject;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_text;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import io.appium.java_client.AppiumDriver;

public abstract class MasterPageObject extends PageObjectBase {
	public menuPageObject menuPage;

	public MasterPageObject(AppiumDriver driver) {
		super(driver);
		menuPage = new menuPageObject(driver);
	}

	public class menuPageObject extends PageElementObjectBase {

		public menuPageObject(AppiumDriver driver) {
			super(driver);
			setPageIdentifier(for_text("MenuAutoScoutLogo"));
		}

		public menuPageObject open() {
			
			uiAutomation("buttons()[4]").click();
			waitForPage();
			return this;
		}

		public menuPageObject clickSearch() {
			uiAutomation("tableViews()[2].cells()[\"Suchen\"]").click();
			return this;
		}

		public menuPageObject clickLogin() {
			
			uiAutomation("tableViews()[2].cells()[\"Anmelden\"]").click();;
			return this;
		}

		public menuPageObject clickSavedSearch() {
			
			uiAutomation("tableViews()[2].cells()[\"Benachrichtigungen\"]").click();;

			return this;
		}

		public menuPageObject clickFeedback() {
			
			uiAutomation("tableViews()[2].cells()[\"Feedback\"]").click();
			
			return this;
		}

		public menuPageObject clickFavorite() {

			uiAutomation("tableViews()[2].cells()[\"favoriten\"]").click();;
			
			return this;
		}
	}

}
