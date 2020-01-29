package com.test.config;

/**
 * @author Vishal Limbani
 *This is Object Class where all the web locators are declared.
 */
public class ObjectRepository {
	
	// This class contains locators of the Home Screen.
	protected static class HomeScreen {
		
		public static final String MenuButton_Xpath = "//a[@class='btn club-home-button shop-menu-btn']";
		public static final String TextBoxaddress_Xpath = "//input[@id='address-input']";
		public static final String SearchButtonForMenu_Xpath = "//input[@class='btn--honest blattgold--form-banner-submit']";
		public static final String agreeCookiesButton_Xpath = "//button[@class='agree-cookie']";
		
	}
	// This class contains locators of the Menu Screen.
	protected static class MenuScreen {
		
		public static final String menuButton_Xpath = "//a[contains(@class,'burger-menu btn')]";
		public static final String mamacitaLogo_Xpath = "//*[@title='Mamacita' and @class = 'navigation--link logo--link  mamacita']";
		public static final String ProductsList_Xpath = "//a[@class = 'product--title']";
		public static final String ProductsPriceList_Xpath = "//div[@class = 'product--price']";
		public static final String addToCartButton_Xpath = "//button[@id='topup-modal--close']";
		
		public static final String shoppingCartAmount_Xpath = "//span[@class = 'prices--articles-amount']";
		public static final String shoppingCartItemNameList_Xpath = "//span[contains(@class,'item--name')]";
		public static final String shoppingCartItemPriceList_Xpath = "//span[@class = 'item--price']";
	}

}
