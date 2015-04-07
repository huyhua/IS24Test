package com.nvg.IS24.appium.pageObject;

import io.appium.java_client.ios.IOSDriver;

public abstract class PageElementObjectBase extends PageObjectBase {
	protected IOSDriver driver;

	public PageElementObjectBase(IOSDriver driver) {
		super(driver);
		this.driver = driver;
	}
    
}
