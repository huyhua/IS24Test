package com.nvg.IS24.appium.IS24Test.Core;

import static com.nvg.IS24.appium.IS24Test.Core.Helpers.*;
import io.appium.java_client.ios.IOSDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public abstract class TestBase extends AppiumSetup {
	
	public void startIOSMobileTest(Consumer<IOSDriver> action){
		
		bypassInitialScreens();
		action.accept(driver);
			
	};
	
	public void bypassInitialScreens(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			driver.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().alert().buttons()[\"Ignore\"]").click();;
		} catch (Exception e) {
			//If one of the 2 screens don't appear. Skip it instead of disband test
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			driver.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().alert().buttons()[\"Deutsch\"]").click();;
		} catch (Exception e) {
			//If one of the 2 screens don't appear. Skip it instead of disband test
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		waitSec(15);
		
	}

}
