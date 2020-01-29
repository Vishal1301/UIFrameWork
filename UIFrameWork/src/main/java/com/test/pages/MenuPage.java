package com.test.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.utils.TestBase;
import com.test.utils.TestUtil;

/**
 * @author Vishal Limbani
 *
 */
public class MenuPage extends TestBase {
// Creating instance for WebDriver Interface
	static WebDriver driver;
//Initializing page factory
	public MenuPage(WebDriver driver) {
		MenuPage.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Web Element for elements on the Menu page
	@FindBy(how = How.XPATH, using = MenuScreen.menuButton_Xpath)
	public WebElement menuButton;

	@FindBy(how = How.XPATH, using = MenuScreen.mamacitaLogo_Xpath)
	public WebElement mamacitaLogo;

	@FindBy(how = How.XPATH, using = MenuScreen.ProductsList_Xpath)
	public List<WebElement> restaurantProducts;
	
	@FindBy(how = How.XPATH, using = MenuScreen.shoppingCartAmount_Xpath)
	public WebElement shoppingCartAmount;
	
	@FindBy(how = How.XPATH, using = MenuScreen.ProductsPriceList_Xpath)
	public List<WebElement> restaurantProductsPrice;
	
	@FindBy(how = How.XPATH, using = MenuScreen.shoppingCartItemPriceList_Xpath)
	public List<WebElement> shoppingCartItemPriceList;
	
	@FindBy(how = How.XPATH, using = MenuScreen.addToCartButton_Xpath)
	public WebElement addToCartButton;
	
//Method to click on menu button	
	public void clickOnMenuButton() {
		
		try {
			menuButton.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on menu button" + e);
		}
	}

//Method to click on Mamacita Logo Button	
	public void clickOnMamacitaLogoButton() {
	
		try {
			mamacitaLogo.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on mamacita logo button" + e);
		}
	}
	
//Method to click on the products to add it to cart
	public String clickOnProductToAddToCart(String product) {
		

		String price = "";
		try {
			
			for (int i = 0; i < restaurantProducts.size(); i++) {
					if (restaurantProducts.get(i).getText().equalsIgnoreCase(product)) {
						System.out.println("Product found is :: " + restaurantProducts.get(i).getText());
						Thread.sleep(3000);
						restaurantProducts.get(i).click();
						price = restaurantProductsPrice.get(i).getText();
				}
			}
		} catch (Exception e) {
			log.info("Exception occured while adding product" + e);
		}
		return price;
	}
	
//Method to click on add to cart button
	public void clickOnAddToCartButton() {
		
		try {
			addToCartButton.click();
		} catch (Exception e) {
			log.info("Exception occured while clicking on add to cart button" + e);
		}
	}
	
//Method to get total amount
	public String getTotalCartPrice() {
		
		String cartAmount = "";
		try {
			cartAmount = shoppingCartAmount.getText();
		} catch (Exception e) {
			log.info("Exception occured while getting Shopping cart amount" + e);
		}
		return cartAmount;
	}
	
//Method to get total of all products price
	public String getAllCartProductsTotal() {
		
		String totalValue = "";
		int a = 0, b = 0, c = 0, d = 0;
		try {
			for (int i = 0; i < shoppingCartItemPriceList.size(); i++) {
				
				String s = shoppingCartItemPriceList.get(i).getText();
				
				String[] s1 = s.split(",");
				
				String[] s2 = s1[1].split(" ");
				
				a = a + Integer.parseInt(s1[0]);
				
				b = b + Integer.parseInt(s2[0]);
			}
			
			c = b % 100;
			d = b / 100;
			
			a = a + d;
			
			totalValue = Integer.toString(a) + "," + Integer.toString(c) + " €";
			
		} catch (Exception e) {
			log.info("Exception occured while getting Shopping cart amount" + e);
		}
		return totalValue;
	}
	
}
