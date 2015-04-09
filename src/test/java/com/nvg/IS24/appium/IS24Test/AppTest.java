package com.nvg.IS24.appium.IS24Test;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Ignore;
import org.junit.Test;

import com.nvg.IS24.appium.IS24Test.Core.TestBase;
import com.nvg.IS24.appium.pageObject.LoginPageObject;
import com.nvg.IS24.appium.pageObject.SearchListPageObject;
import com.nvg.IS24.appium.pageObject.SearchPageObject;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {

	private SearchPageObject searchPage;
	@SuppressWarnings("unused")
	private LoginPageObject loginPage;
	private SearchListPageObject searchList;

	@Test
	public void testSuite1() {

		// Test case 2.1
		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver).where("Zurich");

			collector.checkThat("2.1:City Area tab isn't available.",
					uiAutomation("tableViews()[0].cells()[2]")
							.isDisplayed(), is(true));

			collector.checkThat("2.1:City Area tab is empty",
					searchPage.cityArea.open().getAll().isEmpty(), is(false));
			searchPage.back();
			waitMsec(500);
		});

		// Test case 2.2, reusing current session to avoid losing time
		continueCurrentIOSMobileTest(driver -> {

			searchPage = new SearchPageObject(driver);
			int originalHit = searchPage.hitNumber;

			searchPage = searchPage.cityArea("District 1"); // Check District 1,
															// renew searchPage
															// to get latest hit
															// number

			collector.checkThat(
					"2.2:Search result: didn't change after changing district",
					originalHit, is(not(searchPage.hitNumber)));

			searchPage = searchPage.cityArea("District 1"); // Uncheck it

			collector
					.checkThat(
							"Search result: is not the same when un-checking all city areas",
							originalHit, is(searchPage.hitNumber));
		});

		// 2.3 verify Search number with radius modified
		continueCurrentIOSMobileTest(driver -> {

			searchPage = new SearchPageObject(driver);

			int count = searchPage.radius.open().getRadius().size();
			searchPage.radius.select(0);

			for (int i = 1; i < count; i++) {

				int lastHit = searchPage.hitNumber;
				searchPage = searchPage.radius(i);
				collector.checkThat("2.3:Radius at index" + i
						+ "has the same number of hit with the last index",
						searchPage.hitNumber, is(not(lastHit)));
			}

		});

		// 2.4

	}

	@Test
	@Ignore
	public void testSuite2() {
		startIOSMobileTest(driver -> {

		});
	}

	@Test
	public void testSuite3() {
		startIOSMobileTestWithLogin("huyhua@nhatvietgroup.com.vn", "123456",
				driver -> {

				});
	}

	@Test
	public void AccessibilityTest() {
		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver)
			.where("Zurich")
			.search();
			searchList = new SearchListPageObject(driver)
			.open()
			.clickItem(1);
			
		});
	}

}
