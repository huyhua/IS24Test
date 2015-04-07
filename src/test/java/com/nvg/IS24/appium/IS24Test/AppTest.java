package com.nvg.IS24.appium.IS24Test;

import org.junit.Test;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitSec;

import com.nvg.IS24.appium.IS24Test.Core.TestBase;
import com.nvg.IS24.appium.pageObject.SearchPageObject;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
	@SuppressWarnings("unused")
	private SearchPageObject searchPage;

	@Test
	public void searchPageManipulation() {

		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver)
					.buyWhat(2)
					.where("Zurich")
					.cityArea("District 1")
					.radius(2)
					.search();
			
			waitSec(10);

		});
	}

}
