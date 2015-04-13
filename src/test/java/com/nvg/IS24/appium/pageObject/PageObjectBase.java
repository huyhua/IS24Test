package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;

import com.nvg.SupportClasses.Helpers;

public abstract class PageObjectBase {

	protected By pageIdentifier;
	protected String platform;

	public void setPageIdentifier(By pageIdentifier) {
		this.pageIdentifier = pageIdentifier;
	}

	protected AppiumDriver driver;

	protected PageObjectBase(AppiumDriver driver, String platform) {
		this.driver = driver;
		this.platform = platform;
	}

	public void waitForPage() {
		Helpers.wait(pageIdentifier);
	}

}
