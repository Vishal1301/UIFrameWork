package com.test.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.pages.HomePage;
import com.test.pages.MenuPage;
import com.test.utils.TestBase;

/**
 *
 * @author Vishal Limbani
 *
 *This test class validate add to cart functionality for Mamacita brand
 */
public class MamacitaCartTest extends TestBase {

	@Test(description = "To check add to cart functionality for single and multiple products of Mamacita brand", enabled = true)
	public void mamacitaAddtoCart() throws InterruptedException {

		HomePage homePage = new HomePage(driver);
		MenuPage clubkitchenRestaurantMenuPage = new MenuPage(driver);

		homePage.clickOnAgreeCookiesButton();
		//System.out.println("Printing stored Cookies :: " + driver.manage().getCookies());

		homePage.clickOnToTheMenuButton(); // Clicking on Menu button
		homePage.enterDeliveryAddress("Semperstrasse 44, 1180 Vienna, Austria"); // entering delivery address
		Thread.sleep(3000);
		homePage.clickOnToTheMenuSearchButton(); // clicking on menu search button
		Thread.sleep(5000);
		clubkitchenRestaurantMenuPage.clickOnMenuButton(); // clicking on menu button
		Thread.sleep(2000);
		clubkitchenRestaurantMenuPage.clickOnMamacitaLogoButton(); //clicking on Mamacita logo button
		Thread.sleep(5000);
		String price = clubkitchenRestaurantMenuPage.clickOnProductToAddToCart("Avocado Crush Burrito"); // adding 'avocado crush burrito' to cart
		System.out.println("Price of product is :: " + price); // printing product price
		Thread.sleep(2000);

		clubkitchenRestaurantMenuPage.clickOnAddToCartButton(); // click on add to cart button
		Thread.sleep(2000);
		String cartPrice = clubkitchenRestaurantMenuPage.getTotalCartPrice(); // get total cart price
		System.out.println("Cart price is :: " + cartPrice);
		String pp = clubkitchenRestaurantMenuPage.getAllCartProductsTotal(); // Getting total of all the products values
		System.out.println("Total Cart price is :: " + pp);
		Assert.assertEquals(pp, cartPrice, "Actual total product price does not match with total cart price, Actual price :: "
				+ pp + "|| Cart price :: " + cartPrice); // Asserting actual vs expected price
	}
}
