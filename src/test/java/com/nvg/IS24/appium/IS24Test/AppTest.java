package com.nvg.IS24.appium.IS24Test;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.nvg.IS24.appium.IS24Test.Core.TestBase;
import com.nvg.IS24.appium.pageObject.LoginPageObject;
import com.nvg.IS24.appium.pageObject.SearchPageObject;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
	
	private SearchPageObject searchPage;
	@SuppressWarnings("unused")
	private LoginPageObject loginPage;

	@Test
	public void testCase101() {

		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver).where("Zurich");

			Assert.assertTrue("City Area tab isn't available.",
					uiAutomation("tableViews()[0].cells()[\"City areas\"]")
							.isDisplayed());
			Assert.assertFalse("City Area tab is empty", searchPage.cityArea
					.open().getAll().isEmpty());

		});
	}

	@Test
	@Ignore
	public void loginPageManipulation() {
		startIOSMobileTest(driver -> {
			loginPage = new LoginPageObject(driver).open().loginSection()
					.fillName("huyhua@nhatvietgroup.com.vn")
					.fillPassword("123456").login().waitForLogin();
		});
	}

}
