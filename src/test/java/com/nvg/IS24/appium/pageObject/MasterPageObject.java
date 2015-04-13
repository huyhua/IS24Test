package com.nvg.IS24.appium.pageObject;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.accessibilityId;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_text;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public abstract class MasterPageObject extends PageObjectBase {
	public menuPageObject menuPage;

	public MasterPageObject(AppiumDriver driver, String platform) {
		super(driver,platform);
		menuPage = new menuPageObject(driver, platform);

	}

	public MobileElement NavItem(String locator) {
		return (MobileElement) ((IOSDriver) driver)
				.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().navigationBar()."
						+ locator);

	}

	public MasterPageObject rightButton() {
		NavItem("rightButton()").click();
		return this;
	}

	public MasterPageObject back() {
		NavItem("leftButton()").click();
		return this;
	}

	public class menuPageObject extends PageElementObjectBase {

		public menuPageObject(AppiumDriver driver, String platform) {
			super(driver, platform);
			setPageIdentifier(for_text("Home"));
		}

		public menuPageObject open() {

			accessibilityId("Global_BtnMenu").click();
			waitForPage();
			return this;
		}

		public menuPageObject search() {
			uiAutomation("tableViews()[0].cells()[\"Search\"]").click();
			waitMsec(300);
			return this;
		}

		public menuPageObject login() {

			uiAutomation("tableViews()[0].cells()[\"Sign in / Sign up\"]")
					.click();
			waitMsec(300);
			return this;
		}

		public menuPageObject notifications() {

			uiAutomation("tableViews()[2].cells()[\"Notifications\"]").click();
			waitMsec(300);

			return this;
		}

		public menuPageObject feedback() {

			uiAutomation("tableViews()[2].cells()[\"Your opinion\"]").click();
			waitMsec(300);

			return this;
		}

		public menuPageObject favorites() {

			uiAutomation("tableViews()[2].cells()[\"List of favourites\"]")
					.click();
			waitMsec(300);

			return this;
		}
	}

}
