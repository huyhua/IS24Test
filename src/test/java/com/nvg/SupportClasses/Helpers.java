package com.nvg.SupportClasses;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Helpers {

	private static AppiumDriver driver;
	private static WebDriverWait driverWait;

	public static MobileElement accessibilityId(String id) {
		return w(driver.findElementByAccessibilityId(id));
	}

	/**
	 * Press the back button *
	 */
	public static void back() {
		driver.navigate().back();
	}

	public static MobileElement button(String text) {
		return element(for_button_text(text));
	}

	/**
	 * return all child elements of an element by locator
	 */
	public static List<MobileElement> childElement(MobileElement element,
			By locator) {
		List<WebElement> list = element.findElements(locator);
		return w(list);
	}

	/**
	 * Use the keyboard to delete text instead of Appium Default method
	 */
	public static void clearText(By locator) {
		MobileElement element = element(locator);
		String text = element.getText();

		for (int i = 0; i < text.length(); i++) {
			driver.getKeyboard().sendKeys(Keys.BACK_SPACE);
		}
	}

	/**
	 * Return an element by locator *
	 */
	public static MobileElement element(By locator) {
		return w(driver.findElement(locator));
	}

	/**
	 * Return a list of elements by locator *
	 */
	public static List<MobileElement> elements(By locator) {
		return w(driver.findElements(locator));
	}

	/**
	 * Return button that matches the text
	 */
	public static By for_button_text(String text) {
		String up = text.toUpperCase();
		String down = text.toLowerCase();
		return By
				.xpath("//UIAButton[@visible=\"true\" and (contains(translate(@name,\""
						+ up
						+ "\",\""
						+ down
						+ "\"), \""
						+ down
						+ "\") or contains(translate(@hint,\""
						+ up
						+ "\",\""
						+ down
						+ "\"), \""
						+ down
						+ "\") or contains(translate(@label,\""
						+ up
						+ "\",\""
						+ down
						+ "\"), \""
						+ down
						+ "\") or contains(translate(@value,\""
						+ up
						+ "\",\""
						+ down + "\"), \"" + down + "\"))]");
	}

	/**
	 * Return a tag name locator *
	 */
	public static By for_tags(String tagName) {
		return By.className(tagName);
	}
	
	public static By for_id(String id) {
		return By.id(id);
	}

	/**
	 * Return a static text locator by xpath index *
	 */
	public static By for_text(int xpathIndex) {
		return By.xpath("//UIAStaticText[" + xpathIndex + "]");
	}

	/**
	 * Return a static text locator that contains text *
	 */
	public static By for_text(String text) {
		String up = text.toUpperCase();
		String down = text.toLowerCase();
		return By
				.xpath("//*[@visible=\"true\" and (contains(translate(@name,\""
						+ up + "\",\"" + down + "\"), \"" + down
						+ "\") or contains(translate(@hint,\"" + up + "\",\""
						+ down + "\"), \"" + down
						+ "\") or contains(translate(@label,\"" + up + "\",\""
						+ down + "\"), \"" + down
						+ "\") or contains(translate(@value,\"" + up + "\",\""
						+ down + "\"), \"" + down + "\"))]");
	}

	/**
	 * 
	 * @param tag
	 * @param text
	 * @return Mobile Element that matches both tag and text name
	 */
	public static By for_text_and_tag(String tag, String text) {
		String up = text.toUpperCase();
		String down = text.toLowerCase();
		return By.xpath("//" + tag
				+ "[@visible=\"true\" and (contains(translate(@name,\"" + up
				+ "\",\"" + down + "\"), \"" + down
				+ "\") or contains(translate(@hint,\"" + up + "\",\"" + down
				+ "\"), \"" + down + "\") or contains(translate(@label,\"" + up
				+ "\",\"" + down + "\"), \"" + down
				+ "\") or contains(translate(@value,\"" + up + "\",\"" + down
				+ "\"), \"" + down + "\"))]");
	}

	/**
	 * Return a static text locator by exact text *
	 */
	public static By for_text_exact(String text) {
		return By.xpath("//*[@visible='true' and (@name='" + text
				+ "' or @hint='" + text + "' or @label='" + text
				+ "' or @value='" + text + "')]");
	}

	/**
	 * Initialize the webdriver. Must be called before using any helper methods.
	 * *
	 */
	public static void init(AppiumDriver webDriver) {
		driver = webDriver;
		int timeoutInSeconds = 60;
		// must wait at least 60 seconds for running on Sauce.
		// waiting for 30 seconds works locally however it fails on Sauce.
		driverWait = new WebDriverWait(webDriver, timeoutInSeconds);
	}

	/**
	 * Return a list of elements by tag name *
	 */
	public static List<MobileElement> tags(String tagName) {
		return elements(for_tags(tagName));
	}
	
	public static MobileElement tag(String tagname) {
		return element(for_tags(tagname));
	}
	
	public static MobileElement xpath(String path){
		return element(By.xpath(path));
	}
	
	public static List<MobileElement> xpaths(String path){
		return elements(By.xpath(path));
	}
	
	public static MobileElement resourceId(String id) {
		return element(for_id(id));
	}
	
	public static List<MobileElement> resourceIds(String id) {
		return elements(for_id(id));
	}

	@SuppressWarnings("serial")
	public static void tapElementExact(By locator) {
		final double x = element(locator).getCenter().getX();
		final double y = element(locator).getCenter().getY();
		driver.executeScript("mobile: tap", new HashMap<String, Double>() {
			{
				put("tapCount", 1.0);
				put("touchCount", 1.0);
				put("duration", 0.3);
				put("x", x);
				put("y", y);
			}
		});
	}

	/**
	 * Return a static text element by xpath index *
	 */
	public static MobileElement text(int xpathIndex) {
		return element(for_text(xpathIndex));
	}

	/**
	 * Return a static text element that contains text *
	 */
	public static MobileElement text(String text) {
		return element(for_text(text));
	}

	/**
	 * Return a static text element by exact text *
	 */
	public static MobileElement text_exact(String text) {
		return element(for_text_exact(text));
	}

	public static List<MobileElement> text_exacts(String text) {
		return elements(for_text_exact(text));
	}

	public static MobileElement textAndTag(String tag, String text) {
		return element(for_text_and_tag(tag, text));
	}

	public static List<MobileElement> textAndTags(String tag, String text) {
		return elements(for_text_and_tag(tag, text));
	}

	public static List<MobileElement> texts(String text) {
		return elements(for_text(text));
	}

	public static void tryAction(Consumer<AppiumDriver> action) {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			action.accept(driver);
		} catch (Exception e) {
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	/**
	 * Rather than the set value method, this fills text using Keyboard. Slow
	 * but sure.
	 */
	public static void typeKeyboard(String text) {
		String[] fracture = text.split("");
		for (int i = 0; i < fracture.length; i++) {
			driver.getKeyboard().sendKeys(fracture[i]);
		}
	}

	/**
	 * Return element using uiAutomation strategy. Valid for iOS only
	 */
	public static MobileElement uiAutomation(String path) {
		return w(((IOSDriver) driver)
				.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().mainWindow()."
						+ path));
	}

	public static List<MobileElement> uiAutomations(String path) {
		return w(((IOSDriver) driver)
				.findElementsByIosUIAutomation("UIATarget.localTarget().frontMostApp().mainWindow()."
						+ path));
	}
	
	/**
	 * Return element using Google uiAutomator. Valid for android test only
	 */
	public static MobileElement googleAutomator(String path) {
		return w(((AndroidDriver)driver).findElementByAndroidUIAutomator(path));
	}
	
	public static List<MobileElement> googleAutomators(String path) {
		return w(((AndroidDriver)driver).findElementsByAndroidUIAutomator(path)); 
	}

	
	/**
	 * Wrap WebElement in MobileElement *
	 */
	private static List<MobileElement> w(List<WebElement> elements) {
		List<MobileElement> list = new ArrayList<MobileElement>(elements.size());
		for (WebElement element : elements) {
			list.add(w(element));
		}

		return list;
	}

	/**
	 * Wrap WebElement in MobileElement *
	 */
	private static MobileElement w(WebElement element) {
		return (MobileElement) element;
	}

	/**
	 * Wait 30 seconds for locator to find an element *
	 */
	public static MobileElement wait(By locator) {
		return w(driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(locator)));
	}

	/**
	 * Wait 60 seconds for locator to find all elements *
	 */
	public static List<MobileElement> waitAll(By locator) {
		return w(driverWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(locator)));
	}

	/**
	 * Sleep the thread for an amount of time
	 */
	public static void waitMsec(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sleep the thread for an amount of time
	 */
	public static void waitSec(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
