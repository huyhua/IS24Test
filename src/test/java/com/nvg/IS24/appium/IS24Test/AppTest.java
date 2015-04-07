package com.nvg.IS24.appium.IS24Test;

import org.junit.Test;

import com.nvg.IS24.appium.IS24Test.Core.TestBase;
import com.nvg.IS24.appium.pageObject.LoginPageObject;
import com.nvg.IS24.appium.pageObject.SearchPageObject;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
	@SuppressWarnings("unused")
	private SearchPageObject searchPage;
	@SuppressWarnings("unused")
	private LoginPageObject loginPage;

	@Test
	public void searchPageManipulation() {

		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver)
					.buyWhat(2)
					.where("Zurich")
					.cityArea("District 1")
					.radius(2)
					.search();
		});
	}

	@Test
	public void loginPageManipulation() {
		startIOSMobileTest(driver -> {
			loginPage = new LoginPageObject(driver).open()
					.loginSection()
					.fillName("huyhua@nhatvietgroup.com.vn")
					.fillPassword("123456")
					.login()
					.waitForLogin();

		});
	}

}
