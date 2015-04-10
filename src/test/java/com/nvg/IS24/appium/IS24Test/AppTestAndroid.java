package com.nvg.IS24.appium.IS24Test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nvg.IS24.appium.IS24Test.Core.TestBase;

public class AppTestAndroid extends TestBase {
	
	@Override
	public void setPlatform(String platform) {

		super.setPlatform("android");
	}
	

	@Test
	public void Test() {
		try {
			driver.findElement(By.name("Update Available"));
			driver.findElement(By.name("Dismiss")).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No Update form availabe");
		}
		try {
			driver.findElement(By.name("English")).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No language selection screen available");
		}

		try {
			WebDriverWait hangon = new WebDriverWait(driver, 10);
			hangon.until(ExpectedConditions.presenceOfElementLocated(By
					.id("ch.autoscout24.autoscout24.alpha:id/imgAds")));
			driver.findElementById(
					"ch.autoscout24.autoscout24.alpha:id/btnClose").click();
		} catch (Exception e) {
			System.out.println("No Ads");
		}

		try {
			driver.findElement(By.name("Update Available"));
			driver.findElement(By.name("Dismiss")).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No Update form availabe");
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}
