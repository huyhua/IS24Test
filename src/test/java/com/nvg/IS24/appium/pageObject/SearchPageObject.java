package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_tags;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitSec;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;

public class SearchPageObject extends MasterPageObject {

	public SearchPageObject(IOSDriver driver) {
		super(driver);
		setPageIdentifier(for_tags("UIANavigationBar"));
	}

	public SearchPageObject open() {

		menuPage.open().search();

		return new SearchPageObject(driver);
	}

	public SearchPageObject buyWhat(int index) {

		uiAutomation("tableViews()[0].buttons()[\"New search\"]").click();
		uiAutomation("tableViews()[0].buttons()[\"Buy\"]").click();
		uiAutomation("tableViews()[0].visibleCells()[" + index + "]").click();
		waitMsec(500);
		return new SearchPageObject(driver);
	}

	public SearchPageObject rentWhat(int index) {

		uiAutomation("tableViews()[0].buttons()[\"New search\"]").click();
		uiAutomation("tableViews()[0].buttons()[\"Rent\"]").click();
		uiAutomation("tableViews()[0].visibleCells()[" + index + "]").click();
		waitMsec(500);
		return new SearchPageObject(driver);
	}

	public SearchPageObject where(String location) {

		uiAutomation("tableViews()[0].cells()[\"Where\"]").click();
		IOSElement searchBar = (IOSElement) driver
				.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().navigationBar().searchBars()[0].searchBars()[0]");
		searchBar.setValue(location);
		waitSec(2);
		uiAutomation("tableViews()[0].visibleCells()[0]").click();
		waitMsec(500);

		return new SearchPageObject(driver);
	}

	public SearchPageObject cityArea(String area) {

		uiAutomation("tableViews()[0].cells()[\"City areas\"]").click();
		waitSec(1);
		uiAutomation("tableViews()[0].cells()[\"" + area + "\"]").click();
		done();
		waitMsec(500);
		return new SearchPageObject(driver);
	}

	public SearchPageObject radius(int index) {

		uiAutomation("tableViews()[0].cells()[\"Radius\"]").click();
		waitMsec(500);
		uiAutomation("tableViews()[0].visibleCells()[" + index + "]").click();
		waitMsec(500);

		return new SearchPageObject(driver);
	}

	public SearchPageObject search() {

		uiAutomation("buttons()[0]").click();
		waitMsec(500);

		return this;
	}

}
