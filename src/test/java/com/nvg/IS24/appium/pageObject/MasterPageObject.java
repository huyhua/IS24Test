package com.nvg.IS24.appium.pageObject;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_text;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
import io.appium.java_client.ios.IOSDriver;

public abstract class MasterPageObject extends PageObjectBase {
	public menuPageObject menuPage;

	public MasterPageObject(IOSDriver driver) {
		super(driver);
		menuPage = new menuPageObject(driver);

	}

	public MasterPageObject done() {
		driver.findElementByIosUIAutomation(
				"UIATarget.localTarget().frontMostApp().navigationBar().rightButton()")
				.click();
		return this;
	}

	public MasterPageObject back() {
		driver.findElementByIosUIAutomation(
				"UIATarget.localTarget().frontMostApp().navigationBar().leftButton()")
				.click();
		return this;
	}

	public class menuPageObject extends PageElementObjectBase {

		public menuPageObject(IOSDriver driver) {
			super(driver);
			setPageIdentifier(for_text("Home"));
		}

		public menuPageObject open() {

			driver.findElementByIosUIAutomation(
					"UIATarget.localTarget().frontMostApp().navigationBar().buttons()[\"Menu\"]")
					.click();
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
