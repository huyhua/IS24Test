package com.nvg.IS24.appium.pageObject;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.accessibilityId;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.for_tags;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomation;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.uiAutomations;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitMsec;
import static com.nvg.IS24.appium.IS24Test.Core.Helpers.waitSec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

public class SearchPageObject extends MasterPageObject {

	public int hitNumber;
	public CityAreaPageObject cityArea;
	public RadiusPageObject radius;

	public SearchPageObject(AppiumDriver driver) {
		super(driver);
		setPageIdentifier(for_tags("UIANavigationBar"));
		cityArea = new CityAreaPageObject(driver);
		radius = new RadiusPageObject(driver);
		hitNumber = getSearchResult();
	}

	public SearchPageObject open() {

		menuPage.open().search();

		return new SearchPageObject(driver);
	}

	public SearchPageObject buyWhat(int index) {

		accessibilityId("Search_BtnWhat").click();
		uiAutomation("tableViews()[0].buttons()[\"Buy\"]").click();
		uiAutomation("tableViews()[0].visibleCells()[" + index + "]").click();
		waitMsec(500);
		return new SearchPageObject(driver);
	}

	public SearchPageObject rentWhat(int index) {

		accessibilityId("Search_BtnWhat").click();
		uiAutomation("tableViews()[0].buttons()[\"Rent\"]").click();
		uiAutomation("tableViews()[0].visibleCells()[" + index + "]").click();
		waitMsec(500);
		return new SearchPageObject(driver);
	}

	public SearchPageObject where(String location) {

		accessibilityId("Search_BtnWhere").click();
		IOSElement searchBar = (IOSElement) ((IOSDriver) driver)
				.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().navigationBar().searchBars()[0].searchBars()[0]");
		searchBar.setValue(location);
		waitSec(2);
		uiAutomation("tableViews()[0].visibleCells()[0]").click();
		waitMsec(500);

		return new SearchPageObject(driver);
	}

	public class CityAreaPageObject extends PageElementObjectBase {

		public CityAreaPageObject(AppiumDriver driver) {
			super(driver);
		}

		public CityAreaPageObject open() {

			uiAutomation("tableViews()[0].cells()[2]").click();
			waitSec(1);
			return new CityAreaPageObject(driver);
		}

		public List<MobileElement> getDistricts() {
			return uiAutomations("tableViews()[0].cells().withPredicate(\"name beginswith 'District'\")");
		}

		public List<MobileElement> getAll() {
			return uiAutomations("tableViews()[0].cells()");
		}

		public List<MobileElement> getStreet() {
			List<MobileElement> all = uiAutomations("tableViews()[0].cells()");
			all.forEach((cell) -> {
				if (cell.getText().contains("District")) {
					all.remove(cell);
				}
			});

			return all;
		}

		public CityAreaPageObject select(String area) {

			uiAutomation("tableViews()[0].cells()[\"" + area + "\"]").click();
			done();
			waitMsec(500);
			return this;
		}
	}

	public SearchPageObject cityArea(String area) {

		cityArea.open().select(area);

		return new SearchPageObject(driver);
	}

	public class RadiusPageObject extends PageElementObjectBase {

		public RadiusPageObject(AppiumDriver driver) {
			super(driver);

		}

		public RadiusPageObject open() {

			accessibilityId("Search_BtnRadius").click();
			waitMsec(500);
			return this;
		}

		public List<MobileElement> getRadius() {
			return uiAutomations("tableViews()[0].visibleCells()");
		}

		public RadiusPageObject select(int index) {

			uiAutomation("tableViews()[0].visibleCells()[" + index + "]")
					.click();
			waitMsec(700);
			return this;
		}

	}

	public SearchPageObject radius(int index) {

		radius.open().select(index);
		return new SearchPageObject(driver);
	}

	public SearchPageObject search() {

		accessibilityId("Search_BtnSearch").click();
		waitMsec(500);

		return this;
	}

	public SearchPageObject resetSearch() {
		NavItem("rightButton()").click();
		((IOSDriver) driver)
				.findElementByIosUIAutomation(
						"UIATarget.localTarget().frontMostApp().alert().buttons()[\"Reset\"]")
				.click();
		return new SearchPageObject(driver);
	}

	public int getSearchResult() {
		String text = accessibilityId("Search_BtnSearch").getText();
		try {
			return Integer.parseInt(text.split(" ")[1].replace(",", "")
					.replace("'", ""));
		} catch (IndexOutOfBoundsException e) {
			return 0;
		}
	}

}
