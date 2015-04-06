package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.AppiumDriver;

public abstract class PageElementObjectBase extends PageObjectBase {
	protected AppiumDriver driver;

	public PageElementObjectBase(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
	}
    
}
