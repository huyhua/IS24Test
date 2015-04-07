package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_text;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;

public class LoginPageObject extends MasterPageObject {

	public LoginPageObject(IOSDriver driver) {
		super(driver);
		setPageIdentifier(for_text("My account"));

	}

	public LoginPageObject open() {
		menuPage.open().login();
		waitForPage();
		waitMsec(500);
		return new LoginPageObject(driver);
	}

	public LoginPageObject loginSection() {
		uiAutomation("tableViews()[0].buttons()[\"Sign in\"]").click();
		waitMsec(500);
		return new LoginPageObject(driver);
	}

	public LoginPageObject registerSection() {
		return new LoginPageObject(driver);
	}

	public LoginPageObject fillName(String name) {

		((IOSElement) uiAutomation("tableViews()[0].cells()[0].textFields()[0]"))
				.setValue(name);
		return new LoginPageObject(driver);

	}

	public LoginPageObject fillPassword(String password) {

		((IOSElement) uiAutomation("tableViews()[0].cells()[1].secureTextFields()[0]"))
				.setValue(password);
		return new LoginPageObject(driver);

	}

	public LoginPageObject login() {

		uiAutomation("tableViews()[0].buttons()[\"Sign in\"]").click();		
		return this;
	}
	
	public LoginPageObject waitForLogin(){
		
		setPageIdentifier(for_text("Home"));
		waitForPage();
		return this;
	}
	
	public LoginPageObject register(){
		
		uiAutomation("tableViews()[0].buttons()[\"Sign up\"]").click();		
		return this;
	}

}
