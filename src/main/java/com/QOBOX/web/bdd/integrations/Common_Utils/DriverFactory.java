package com.QOBOX.web.bdd.integrations.Common_Utils;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverFactory {
	private static DriverFactory instance = null;
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	

	private DriverFactory() {

	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}


	public final void setWebDriver(String browser) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		switch (browser.toLowerCase()) {

		case "chrome":
			System.out.println("Browser in driverfactory");
			ChromeOptions chOptions = new ChromeOptions();
			capabilities.setCapability("tg:udid", "103");
			capabilities.setCapability("tg:userToken", "w94fjhgbdamztjyw9ud1cnx98lfqel8t");
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("tg:projectName", "JioMart");
			
//			Map<String, Object> chromePrefs = new HashMap<String, Object>();
//			chromePrefs.put("credentials_enable_service", false);
//			chOptions.setExperimentalOption("prefs", chromePrefs);
//			chOptions.addArguments("--no-sandbox");
//			//chOptions.addArguments("--headless"); // !!!should be enabled for Jenkins
//			chOptions.addArguments("--disable-dev-shm-usage"); // !!!should be enabled for Jenkins
//			chOptions.addArguments("--window-size=1920x1080"); // !!!should be enabled for Jenkins
//			chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");
//			chOptions.setCapability(ChromeOptions.CAPABILITY, chOptions);
//			chOptions.addArguments("--remote-allow-origins=*");
////			chOptions. addArguments("--headless");
//			chOptions.setCapability("applicationCacheEnabled", false);
//			System.setProperty("webdriver.chrome.silentOutput", "true");
			webDriver.set(new RemoteWebDriver(new URL("http://poc3.testgrid.io/browserrun35417/wd/hub"),capabilities));
			getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			getWebDriver().manage().window().maximize();
			System.out.println("Browser initiated");
			break;


		case "firefox":
			
			webDriver.set(new FirefoxDriver());
			getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			getWebDriver().manage().window().maximize();
			break;

		case "edge":
			
			webDriver.set(new EdgeDriver());
			getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			getWebDriver().manage().window().maximize();
			break;

		case "ie":
			
			webDriver.set(new InternetExplorerDriver());
			getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			getWebDriver().manage().window().maximize();
			break;
			
		case "chromemobilemode":
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", ConfigReader.getValue("ChromeMobileModeDeviceName"));
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			webDriver.set(new ChromeDriver(chromeOptions));
			getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		}
	}

	

	
	public WebDriver getWebDriver() {
		return webDriver.get();
		
	}

	

	
}