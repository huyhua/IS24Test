package com.nvg.IS24.appium.IS24Test;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


import com.nvg.IS24.appium.IS24Test.Core.TestBase;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
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
	public void testCase1() {
		
		//Test case 1.1
		startIOSMobileTest(driver -> {
			searchPage = new SearchPageObject(driver).where("Zurich");
			
			collector.checkThat("City Area tab isn't available.", uiAutomation("tableViews()[0].cells()[\"City areas\"]")
					.isDisplayed(), is(true));

			collector.checkThat("City Area tab is empty", searchPage.cityArea
					.open().getAll().isEmpty(), is(false));
			searchPage.back();
			waitMsec(500);
		});
		
		//Test case 1.2, reusing current session to avoid losing time
		continueCurrentIOSMobileTest(driver ->{
			int originalHit;
			
			searchPage = new SearchPageObject(driver);
			originalHit = searchPage.hitNumber;
			
			searchPage = searchPage.cityArea("District 1"); //Check District 1, renew searchPage to get latest hit number
			
			collector.checkThat("Search result didn't change after changing district",originalHit, is(not(searchPage.hitNumber)));
			
			searchPage = searchPage.cityArea("District 1"); //Uncheck it
			
			collector.checkThat("Search result is not the same when un-checking all city areas",originalHit, is(searchPage.hitNumber));
			
		});
	}
	
	@Test
	public void testCase2(){
		startIOSMobileTest(driver ->{
			
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
