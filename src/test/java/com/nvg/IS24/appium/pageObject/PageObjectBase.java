package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;

import com.nvg.IS24.appium.IS24Test.Core.Helpers;

public abstract class PageObjectBase {

    protected By pageIdentifier;

	public void setPageIdentifier(By pageIdentifier) {
		this.pageIdentifier = pageIdentifier;
	}

	protected AppiumDriver driver;

	protected PageObjectBase(AppiumDriver driver) {
		this.driver = driver;
	}

	public void waitForPage() {
		Helpers.wait(pageIdentifier);
	}

}
