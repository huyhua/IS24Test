package com.nvg.IS24.appium.IS24Test.Core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.saucerest.SauceREST;

public class AppiumSetup implements SauceOnDemandSessionIdProvider {

	protected AppiumDriver driver;

	public static WebElement wait(By locator) {
		return Helpers.wait(locator);
	}

	private boolean runOnSauce = System.getProperty("sauce") != null;
	private boolean jenkins = System.getProperty("jenkins") != null;
	private String platform = System.getProperty("platform", "ios").toLowerCase();

	/**
	 * Authenticate to Sauce with environment variables SAUCE_USER_NAME and
	 * SAUCE_API_KEY
	 **/

	private SauceOnDemandAuthentication auth = new SauceOnDemandAuthentication();

	/** Report pass/fail to Sauce Labs **/
	// false to silence Sauce connect messages.
	public @Rule SauceOnDemandTestWatcher reportToSauce = new SauceOnDemandTestWatcher(
			this, auth, false);

	@Rule
	public TestRule printTests = new TestWatcher() {
		protected void starting(Description description) {
			System.out.print("  test: " + description.getMethodName());
		}

		protected void finished(Description description) {
			final String session = getSessionId();

			if (session != null) {
				System.out.println(" " + "https://saucelabs.com/tests/"
						+ session);
			} else {
				System.out.println();
			}
		}
	};

	private String sessionId;

	/** Keep the same date prefix to identify job sets. **/
	private static Date date = new Date();

	@Before
	public void setup() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("name", "IS24" + date);
		capabilities.setCapability("appium-version", "1.3.7");
		capabilities.setCapability("platformVersion", "8.2");
		capabilities.setCapability("platformName", "ios");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		String userDir;
		String localApp;
		URL serverURL;

		if (jenkins) {
			throw new IllegalArgumentException("Not Implemented");
		} else {
			userDir = System.getProperty("user.dir");
			localApp = "ImmoScout24.zip";
			if (platform.equals("ios")) {
				localApp = "ImmoScout24.zip";
			} else if (platform.equals("android")) {
				localApp = "ImmoScout24.apk";
			} else {
				throw new IllegalArgumentException("Invalid platform");
			}
		}

		if (runOnSauce) {
			String user = auth.getUsername();
			String key = auth.getAccessKey();

			// Upload app to Sauce Labs
			SauceREST rest = new SauceREST(user, key);

			rest.uploadFile(new File(userDir, localApp), localApp);

			capabilities.setCapability("app", "sauce-storage:" + localApp);
			serverURL = new URL("http://" + user + ":" + key
					+ "@ondemand.saucelabs.com:80/wd/hub");
			driver = getDriver(serverURL, capabilities);

		} else {

			String appPath = Paths.get(userDir, localApp).toAbsolutePath()
					.toString();
			capabilities.setCapability("app", appPath);
			serverURL = new URL("http://127.0.0.1:4723/wd/hub");
			driver = getDriver(serverURL, capabilities);
		}

		sessionId = driver.getSessionId().toString();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Helpers.init(driver);

	}

	@After
	public void tearDown() throws Exception {
		if (driver != null)
			driver.quit();
	}

	public String getSessionId() {
		return runOnSauce ? sessionId : null;
	}

	private AppiumDriver getDriver(URL serverURL,
			DesiredCapabilities capabilities) {
		if (platform.equals("ios")) {
			return new IOSDriver(serverURL, capabilities);
		} else if (platform.equals("android")) {
			return new AndroidDriver(serverURL, capabilities);
		} else {
			throw new IllegalArgumentException("Invalid platform");
		}
	};
}
