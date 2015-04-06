package com.nvg.IS24.appium.IS24Test;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitSec;

import org.junit.Ignore;
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
	public void smokeTest() {

		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver);
		});

	}

	@Test
	@Ignore
	public void searchPageManipulation() {

		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver).dateFrom("2013")
					.dateTo("2015")
					.priceFrom("CHF 1'000")
					.priceTo("CHF 12'500")
					.kmFrom("7'500")
					.kmTo("10'000")
					.carType(3)
					.sortOrder(4)
					.brand("ALFA ROMEO", "145");
			waitSec(3);
		});
	}

	@Test
	@Ignore
	public void loginPageManipulation() {

		startIOSMobileTest(driver -> {
			loginPage = new LoginPageObject(driver).open()
					.loginSection()
					.fillName("huyhua@nhatvietgroup.com")
					.fillPassword("123456")
					.login();
		});
	}

	@Test
	@Ignore
	public void SearchjobPageManipulation() {

		startIOSMobileTest(driver -> {
			loginPage = new LoginPageObject(driver).open()
					.loginSection()
					.login();

			searchPage = new SearchPageObject(driver).open()
					.carType(4)
					.clickSearch();
		});

	}

}
