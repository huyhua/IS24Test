package com.nvg.IS24.appium.IS24Test.Core;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitSec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import com.nvg.IS24.appium.pageObject.LoginPageObject;

public abstract class TestBase extends AppiumSetup {
	LoginPageObject loginPage;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	public void startIOSMobileTest(Consumer<AppiumDriver> action) {

		bypassInitialScreens();
		action.accept(driver);

	};

	public void continueCurrentIOSMobileTest(Consumer<AppiumDriver> action) {
		action.accept(driver);
	}

	public void startIOSMobileTestWithLogin(String username, String password,
			Consumer<AppiumDriver> action) {

		bypassInitialScreens();
		loginPage = new LoginPageObject(driver).open().loginWith(username,
				password);

		loginPage.menuPage.search();
		action.accept(driver);

	}

	private void bypassInitialScreens() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			((IOSDriver) driver)
					.findElementByIosUIAutomation(
							"UIATarget.localTarget().frontMostApp().alert().buttons()[\"Ignore\"]")
					.click();
			;
		} catch (Exception e) {
			// If one of the 2 screens don't appear. Skip it instead of disband
			// test
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			((IOSDriver) driver)
					.findElementByIosUIAutomation(
							"UIATarget.localTarget().frontMostApp().alert().buttons()[\"English\"]")
					.click();
			;
		} catch (Exception e) {
			// If one of the 2 screens don't appear. Skip it instead of disband
			// test
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		waitSec(5);
	}

}
