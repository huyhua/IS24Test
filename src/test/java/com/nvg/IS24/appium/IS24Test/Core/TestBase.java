package com.nvg.IS24.appium.IS24Test.Core;

import static com.nvg.SupportClasses.Helpers.text;
import static com.nvg.SupportClasses.Helpers.waitSec;
import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.NoSuchElementException;

import com.nvg.IS24.appium.pageObject.LoginPageObject;

public abstract class TestBase extends AppiumSetup {
	LoginPageObject loginPage;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	public void startMobileTest(Consumer<AppiumDriver> action) {

		bypassInitialScreens();
		action.accept(driver);

	};

	public void continueCurrentMobileTest(Consumer<AppiumDriver> action) {
		action.accept(driver);
	}

	public void startMobileTestWithLogin(String username, String password,
			Consumer<AppiumDriver> action) {

		bypassInitialScreens();
		loginPage = new LoginPageObject(driver, platform).open().loginWith(username,
				password);

		loginPage.menuPage.search();
		action.accept(driver);

	}

	private void bypassInitialScreens() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			text("Ignore").click();
		} catch (NoSuchElementException e) {
			try {
				text("Dismiss").click();
			} catch (NoSuchElementException e1) {

			}
			// If one of the 2 screens don't appear. Skip it instead of disband
			// test
		}
		try {
			text("English").click();
		} catch (NoSuchElementException e) {
			// If one of the 2 screens don't appear. Skip it instead of disband
			// test
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		waitSec(5);
	}

}
