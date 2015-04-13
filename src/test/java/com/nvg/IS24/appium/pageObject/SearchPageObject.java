package com.nvg.IS24.appium.pageObject;

import static com.nvg.SupportClasses.Helpers.accessibilityId;
import static com.nvg.SupportClasses.Helpers.childElement;
import static com.nvg.SupportClasses.Helpers.for_tags;
import static com.nvg.SupportClasses.Helpers.resourceId;
import static com.nvg.SupportClasses.Helpers.tag;
import static com.nvg.SupportClasses.Helpers.text;
import static com.nvg.SupportClasses.Helpers.uiAutomation;
import static com.nvg.SupportClasses.Helpers.uiAutomations;
import static com.nvg.SupportClasses.Helpers.waitMsec;
import static com.nvg.SupportClasses.Helpers.waitSec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

import org.openqa.selenium.By;

public class SearchPageObject extends MasterPageObject {

	public int hitNumber;
	public CityAreaPageObject cityArea;
	public RadiusPageObject radius;

	public SearchPageObject(AppiumDriver driver, String platform) {
		super(driver, platform);
		switch (platform) {
		case "ios":
			setPageIdentifier(for_tags("UIANavigationBar"));
			break;
		case "android":
			setPageIdentifier(By.id("android:id/content"));
			break;
		}

		cityArea = new CityAreaPageObject(driver, platform);
		radius = new RadiusPageObject(driver, platform);
		hitNumber = getSearchResult();
	}

	public SearchPageObject open() {

		menuPage.open().search();

		return new SearchPageObject(driver, platform);
	}

	public SearchPageObject buyWhat(int index) {

		switch (platform) {
		case "ios":
			accessibilityId("Search_BtnWhat").click();
			uiAutomation("tableViews()[0].buttons()[\"Buy\"]").click();
			uiAutomation("tableViews()[0].visibleCells()[" + index + "]")
					.click();
			waitMsec(500);

			break;
		case "android":
			accessibilityId("Search_BtnWhat").findElementByAccessibilityId(
					"SpinnerText").click();
			text("Buy").click();
			MobileElement Wrapper = resourceId("android:id/list");
			childElement(Wrapper, By.id("android:id/text1")).get(index).click();

			break;
		}

		return new SearchPageObject(driver, platform);
	}

	public SearchPageObject rentWhat(int index) {

		switch (platform) {
		case "ios":
			accessibilityId("Search_BtnWhat").click();
			uiAutomation("tableViews()[0].buttons()[\"Rent\"]").click();
			uiAutomation("tableViews()[0].visibleCells()[" + index + "]")
					.click();
			waitMsec(500);
			break;

		case "android":
			accessibilityId("Search_BtnWhat").findElementByAccessibilityId(
					"SpinnerText").click();
			text("Buy").click();
			MobileElement Wrapper = resourceId("android:id/list");
			childElement(Wrapper, By.id("android:id/text1")).get(index).click();
			break;
		}
		return new SearchPageObject(driver, platform);
	}

	public SearchPageObject where(String location) {

		switch (platform) {
		case "ios":
			accessibilityId("Search_BtnWhere").click();
			IOSElement searchBar = (IOSElement) ((IOSDriver) driver)
					.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().navigationBar().searchBars()[0].searchBars()[0]");
			searchBar.setValue(location);
			waitSec(2);
			uiAutomation("tableViews()[0].visibleCells()[0]").click();
			waitMsec(500);
			break;

		case "android":
			accessibilityId("ContentText").click();
			tag("android.widget.EditText").sendKeys(location);
			waitSec(2);
			((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.ENTER);
			break;
		}

		return new SearchPageObject(driver, platform);
	}

	public class CityAreaPageObject extends PageElementObjectBase {

		public CityAreaPageObject(AppiumDriver driver, String platform) {
			super(driver, platform);
		}

		public CityAreaPageObject open() {
			switch (platform) {
			case "ios":
				uiAutomation("tableViews()[0].cells()[2]").click();
				waitSec(1);
				break;

			case "android":
				break;
			}

			return new CityAreaPageObject(driver, platform);
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

			switch (platform) {
			case "ios":
				uiAutomation("tableViews()[0].cells()[\"" + area + "\"]")
						.click();
				rightButton();
				waitMsec(500);
				break;

			case "android":
				break;
			}

			return this;
		}
	}

	public SearchPageObject cityArea(String area) {

		cityArea.open().select(area);

		return new SearchPageObject(driver, platform);
	}

	public class RadiusPageObject extends PageElementObjectBase {

		public RadiusPageObject(AppiumDriver driver, String platform) {
			super(driver, platform);

		}

		public RadiusPageObject open() {
			switch (platform) {
			case "ios":
				accessibilityId("Search_BtnRadius").click();
				waitMsec(500);
				break;
			case "android":
				break;
			}
			accessibilityId("Search_BtnRadius").click();
			waitMsec(500);
			return this;
		}

		public List<MobileElement> getRadius() {
			return uiAutomations("tableViews()[0].visibleCells()");
		}

		public RadiusPageObject select(int index) {
			switch (platform) {
			case "ios":
				uiAutomation("tableViews()[0].visibleCells()[" + index + "]")
						.click();
				waitMsec(700);
				break;

			case "android":
				break;
			}

			return this;
		}

	}

	public SearchPageObject radius(int index) {

		radius.open().select(index);
		return new SearchPageObject(driver, platform);
	}

	public SearchPageObject search() {

		accessibilityId("Search_BtnSearch").click();
		waitMsec(500);

		return this;
	}

	public SearchPageObject resetSearch() {
		switch (platform) {
		case "ios":
			rightButton();
			((IOSDriver) driver)
					.findElementByIosUIAutomation(
							"UIATarget.localTarget().frontMostApp().alert().buttons()[\"Reset\"]")
					.click();
			break;

		case "android":
			accessibilityId("Search_BtnReset").click();
			resourceId("android:id/button1").click();
			break;
		}

		return new SearchPageObject(driver, platform);
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
