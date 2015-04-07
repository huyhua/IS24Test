package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;

import com.nvg.IS24.appium.IS24Test.Core.Helpers;

public abstract class PageObjectBase {

    protected By pageIdentifier;

	public void setPageIdentifier(By pageIdentifier) {
		this.pageIdentifier = pageIdentifier;
	}

	protected IOSDriver driver;

	protected PageObjectBase(IOSDriver driver) {
		this.driver = driver;
	}

	public void waitForPage() {
		Helpers.wait(pageIdentifier);
	}

}
