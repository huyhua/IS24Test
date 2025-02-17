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

import com.nvg.SupportClasses.ConditionalIgnoreRule;
import com.nvg.SupportClasses.Helpers;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.saucerest.SauceREST;

public class AppiumSetup implements SauceOnDemandSessionIdProvider {

	/** Keep the same date prefix to identify job sets. **/
	private static Date date = new Date();

	public static WebElement wait(By locator) {
		return Helpers.wait(locator);
	}

	/**
	 * Authenticate to Sauce with environment variables SAUCE_USER_NAME and
	 * SAUCE_API_KEY
	 **/

	private SauceOnDemandAuthentication auth = new SauceOnDemandAuthentication();
	protected AppiumDriver driver;
	private boolean jenkins = System.getProperty("jenkins") != null;

	public String platform;
	
	@Rule
	public ConditionalIgnoreRule rule = new ConditionalIgnoreRule();

	@Rule
	public TestRule printTests = new TestWatcher() {
		protected void finished(Description description) {
			final String session = getSessionId();

			if (session != null) {
				System.out.println(" " + "https://saucelabs.com/tests/"
						+ session);
			} else {
				System.out.println();
			}
		}

		protected void starting(Description description) {
			System.out.print("  test: " + description.getMethodName());
		}
	};

	/** Report pass/fail to Sauce Labs **/
	// false to silence Sauce connect messages.
	public @Rule SauceOnDemandTestWatcher reportToSauce = new SauceOnDemandTestWatcher(
			this, auth, false);

	private boolean runOnSauce = System.getProperty("sauce") != null;

	private String sessionId;

	private AppiumDriver getDriver(URL serverURL,
			DesiredCapabilities capabilities) {

		switch (platform) {
		case "ios": {
			capabilities.setCapability("appium-version", "1.3.7");
			capabilities.setCapability("platformVersion", "8.2");
			capabilities.setCapability("platformName", "ios");
			capabilities.setCapability("deviceName", "iPhone Simulator");
			return new IOSDriver(serverURL, capabilities);
		}
			
		case "android":{
			capabilities.setCapability("name", "IS24" + date);
			capabilities.setCapability("appium-version", "1.3.7");
			capabilities.setCapability("platformVersion", "4.4");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("deviceName", "Android Simulator");
			capabilities.setCapability("avd", "AndroidSimulator");
			return new AndroidDriver(serverURL, capabilities);
		}
			
		default:
			throw new IllegalArgumentException("Invalid platform");
		}
	}

	public String getPlatform() {
		return platform;
	}

	public String getSessionId() {
		return runOnSauce ? sessionId : null;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Before
	public void setup() throws IOException, InterruptedException {
		
		setPlatform(System.getProperty("platform", "ios").toLowerCase());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		String userDir;
		String localApp;
		URL serverURL;

		if (jenkins) {
			throw new IllegalArgumentException("Not Implemented");
		} else {
			userDir = System.getProperty("user.dir");
			localApp = "ImmoScout24.zip";

			switch (platform) {
			case "ios":
				localApp = "ImmoScout24.zip";
				break;
			case "android":
				localApp = "ImmoScout24.apk";
				break;
			default:
				throw new IllegalArgumentException("Invalid platform");
			}

		}

		if (runOnSauce) {
			String user = auth.getUsername();
			String key = auth.getAccessKey();

			// Upload app to Sauce Labs
			SauceREST rest = new SauceREST(user, key);

			rest.uploadFile(new File(userDir, localApp), localApp);
			
			capabilities.setCapability("name", "IS24" + date);
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
}
