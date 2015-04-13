package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.AppiumDriver;

public abstract class PageElementObjectBase extends PageObjectBase {
	protected AppiumDriver driver;
	protected String platform;

	public PageElementObjectBase(AppiumDriver driver, String platform) {
		super(driver,platform);
		this.driver = driver;
		this.platform = platform;
	}

}
