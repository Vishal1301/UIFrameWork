package com.test.pages;

import static org.junit.Assert.assertArrayEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.utils.TestBase;
import com.test.utils.TestUtil;

/**
 * @author Vishal Limbani
 * 
 */
public class HomePage extends TestBase {

// Creating instance for WebDriver Interface
	static WebDriver driver;

//Initializing page factory
	public HomePage(WebDriver driver) {
		HomePage.driver = driver;
		PageFactory.initElements(driver, this);
	}
//Web Element for elements on the Home page

	@FindBy(how = How.XPATH, using = HomeScreen.MenuButton_Xpath)
	public WebElement toTheMenuButton;

	@FindBy(how = How.XPATH, using = HomeScreen.TextBoxaddress_Xpath)
	public WebElement addressTextBox;

	@FindBy(how = How.XPATH, using = HomeScreen.SearchButtonForMenu_Xpath)
	public WebElement toTheMenuSearchButton;

	@FindBy(how = How.XPATH, using = HomeScreen.agreeCookiesButton_Xpath)
	public WebElement agreeCookiesButton;

// Method to click on the menu button
	public void clickOnToTheMenuButton() {

		try {
			toTheMenuButton.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on To the menu button" + e);
		}
	}
//Method to enter delivery address
	public void enterDeliveryAddress(String address) {

		try {
			sendKeys(address, addressTextBox, "Enter delivery address");
		} catch (Exception e) {
			log.info("Exception occured while entering delivery address" + e);
		}
	}

	//Method to click on search button
	public void clickOnToTheMenuSearchButton() {

		try {
			toTheMenuSearchButton.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on To the menu search button" + e);
		}
	}
// Method to click on agree button for cookies
	public void clickOnAgreeCookiesButton() {

		try {
			TestUtil.explicitWait(agreeCookiesButton, "Agree cookies button");
			agreeCookiesButton.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on Agree cookies button" + e);
		}
	}

}
