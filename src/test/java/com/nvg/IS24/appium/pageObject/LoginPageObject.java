package com.nvg.IS24.appium.pageObject;

import static com.nvg.SupportClasses.Helpers.for_text;
import static com.nvg.SupportClasses.Helpers.tryAction;
import static com.nvg.SupportClasses.Helpers.uiAutomation;
import static com.nvg.SupportClasses.Helpers.waitMsec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;

public class LoginPageObject extends MasterPageObject {

	LoginSectionPageObject login;
	RegisterSectionPageObject register;

	public LoginPageObject(AppiumDriver driver, String platform) {
		super(driver, platform);
		setPageIdentifier(for_text("My account"));
		login = new LoginSectionPageObject(driver, platform);
		register = new RegisterSectionPageObject(driver, platform);

	}

	public LoginPageObject open() {
		menuPage.open().login();
		waitForPage();
		waitMsec(500);
		return new LoginPageObject(driver, platform);
	}

	public class LoginSectionPageObject extends PageElementObjectBase {

		public LoginSectionPageObject(AppiumDriver driver, String platform) {
			super(driver, platform);
		}

		public LoginSectionPageObject open() {
			tryAction(driver -> {
				uiAutomation("tableViews()[0].buttons()[\"Sign in\"]").click();
			});
			waitMsec(500);
			return this;
		}

		public LoginSectionPageObject fillName(String name) {

			((IOSElement) uiAutomation("tableViews()[0].cells()[0].textFields()[0]"))
					.setValue(name);
			return this;

		}

		public LoginSectionPageObject fillPassword(String password) {

			((IOSElement) uiAutomation("tableViews()[0].cells()[1].secureTextFields()[0]"))
					.setValue(password);
			return this;

		}

		public LoginSectionPageObject submit() {

			uiAutomation("tableViews()[0].buttons()[\"Sign in\"]").click();
			return this;
		}

		private LoginSectionPageObject waitForLogin() {

			setPageIdentifier(for_text("Home"));
			waitForPage();
			return this;
		}

	}

	public LoginPageObject loginWith(String username, String password) {
		login.open().fillName(username).fillPassword(password).submit()
				.waitForLogin();

		return new LoginPageObject(driver, platform);
	}

	public class RegisterSectionPageObject extends PageElementObjectBase {

		public RegisterSectionPageObject(AppiumDriver driver, String platform) {
			super(driver, platform);
		}

		public RegisterSectionPageObject open() {
			tryAction(driver -> {
				uiAutomation("tableViews()[0].buttons()[\"Sign up now\"]")
						.click();
			});
			return this;
		}

		public RegisterSectionPageObject fillEmail(String name) {

			((IOSElement) uiAutomation("tableViews()[0].cells()[0].textFields()[0]"))
					.setValue(name);
			return this;

		}

		public RegisterSectionPageObject fillPassword(String password) {

			((IOSElement) uiAutomation("tableViews()[0].cells()[1].secureTextFields()[0]"))
					.setValue(password);
			return this;

		}

		public RegisterSectionPageObject submit() {

			uiAutomation("tableViews()[0].buttons()[\"Sign up\"]").click();
			return this;
		}

		private RegisterSectionPageObject waitForRegister() {

			setPageIdentifier(for_text("Home"));
			waitForPage();
			return this;
		}

	}

	public LoginPageObject registerWith(String email, String password) {

		register.open().fillEmail(email).fillPassword(password).submit()
				.waitForRegister();
		return new LoginPageObject(driver, platform);
	}

}
