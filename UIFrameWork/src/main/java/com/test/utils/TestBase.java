package com.test.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.test.config.ObjectRepository;
/**
 *@author Vishal Limbani
 *
 */

public class TestBase extends ObjectRepository{


	public static WebDriver driver;
	protected static final Logger log = Logger.getLogger(TestBase.class);
	public static String currentMethodName;
	public static WebDriverWait wait = null;
	public static final PropertiesCache cache = PropertiesCache.getInstance();
	public static String url = cache.getProperty("url");
	public static String ScreenshotsPath = cache.getProperty("Screenshots");
	public static FileInputStream fis = null;
	public FileOutputStream fos = null;
	public static final String CurrentUserDirectory = System.getProperty("user.dir");
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;


// Actions to performed before starting the suite
	
	@BeforeSuite
	public static void Setup() throws InterruptedException, IOException {
		
		log.info(":::::::::::::::::::: Launching webdriver ::::::::::::::::::::");
		try {

			System.setProperty("webdriver.chrome.driver", cache.getProperty("driverPath") + "chromedriver.exe");

			log.info(" =================================================== ");
			log.info("Url is :: " + url);
			log.info(" =================================================== ");

			driver = new ChromeDriver();
			e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			

		} catch (WebDriverException e) {
			driver.close();
		}
	}

// Actions to be performed before execution of a class
	@BeforeClass
	public static void setApplicationLoginPage() throws InterruptedException {
		//String currectClassNameToBeExecuted = this.getClass().getSimpleName();
		//log.info("class Name " + currectClassNameToBeExecuted);
		try {
			driver.navigate().to(url);
		} catch (NoSuchElementException e) {
			log.info("Unable to move login page. trying to logout application");
		}
	}


// Actions to performed after end of execution of a class
	@AfterClass
	public void tearDown() {
		log.info("Tests Ended from class :::::" + this.getClass().getSimpleName());
	}

// Actions to performed before Method
	@BeforeMethod
	public static void beforeMethodCalled(Method method) throws InterruptedException {

		currentMethodName = method.getName();
		log.info("Testcase name is :::::: " + method.getName());
		log.info(method.getName() + " Started ::::");

	}

// Actions to performed after execution of Method
	@AfterMethod()
	public static void afterMethodCalled(Method method) throws InterruptedException, IOException {

		log.info("Testcase execution completed :::::: " + method.getName());
	}

// Actions to performed after execution of suite
	@AfterSuite
	public static void suiteEndReached() throws IOException, InterruptedException {

		log.info("Logger Info:: Inside suiteEndReached Method");
		log.info("Suite ended");
		driver.quit();
	}

// Method to enter value in a web element
	public static void sendKeys(String text, WebElement we, String objectName) throws InterruptedException {

		we.clear();
		we.sendKeys(text);
	}
// Method to send keys from keyboard
	public static void sendKeysFromKeyBoard(Keys key, WebElement we, String objectName) throws InterruptedException {
		we.sendKeys(key);
	}
	

}