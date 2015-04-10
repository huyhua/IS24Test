package com.nvg.IS24.appium.pageObject;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_text;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomations;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

public class SearchListPageObject extends MasterPageObject {

	public List<MobileElement> items;

	public SearchListPageObject(AppiumDriver driver) {
		super(driver);
		setPageIdentifier(for_text("results"));
		items = getActiveItems();

	}

	public SearchListPageObject open() {

		waitForPage();
		return new SearchListPageObject(driver);
	}

	public SearchListPageObject sort(int index) {

		NavItem("buttons()[\"Sorting\"]").click();
		waitMsec(400);
		uiAutomation("tableViews()[1].cells()[" + index + "]").click();
		waitMsec(500);
		return new SearchListPageObject(driver);
	}

	private List<MobileElement> getActiveItems() {
		return uiAutomations("tableViews()[0].cells()");
	}

	public SearchListPageObject clickItem(int index) {

		try {
			items.get(index).click();
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("index needs to be onscreen");
		}
		return this;
	}

}
