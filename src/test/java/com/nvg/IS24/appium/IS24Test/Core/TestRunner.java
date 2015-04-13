package com.nvg.IS24.appium.IS24Test.Core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.nvg.IS24.appium.IS24Test.AppTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({AppTest.class})
public class TestRunner {
	@BeforeClass
	public static void setUpClass() {
		System.out.println("Master setup");

	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("Master tearDown");
	}
}