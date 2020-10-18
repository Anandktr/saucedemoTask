package com.saucedemo.interview.mypages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	private By humburgerIcon = By.xpath("//button[text()='Open Menu']");
	private By closeIcon = By.xpath("//button[text()='Close Menu']");
	private By inventoryItems = By.xpath("//div[@class='inventory_item']");
	private By inventoryProdName = By.className("inventory_item_name");
	private By inventoryName = By.className("inventory_details_name");
	private By addToCartBtn = By.xpath("//button[text()='ADD TO CART']");
	private By removeCartBtn = By.xpath("//button[text()='REMOVE']");
	private By cartIconCount = By.xpath("//div[@id='shopping_cart_container']//span");
	private By backBtn = By.xpath("//button[@class='inventory_details_back_button']");
	private By sortDropdown = By.xpath("//select[@class='product_sort_container']");
	private By ctnShoppingbtn = By.xpath("//a[text()='Continue Shopping']");
	private By imgEle = By.xpath("//img[@class='inventory_item_img']");	

	public WebElement getHumburgerIcon() {
		return getElement(humburgerIcon);
	}

	public WebElement getCloseIcon() {
		return getElement(closeIcon);
	}

	public WebElement getInventoryName() {
		return getElement(inventoryName);
	}

	public List<WebElement> getInventoryItems() {
		return getElements(inventoryItems);
	}

	public List<WebElement> getInventoryProdName() {
		return getElements(inventoryProdName);
	}
	
	public List<WebElement> getImageEles() {
		return getElements(imgEle);
	}
	
	public WebElement getInventoryProdNameCartPage() {
		return getElement(inventoryProdName);
	}

	public WebElement getAddToCartBtn() {
		return getElement(addToCartBtn);
	}

	public WebElement getRemoveCartBtn() {
		return getElement(removeCartBtn);
	}

	public WebElement getCartIconCount() {
		return getElement(cartIconCount);
	}
	
	public WebElement getCtnShoppingbtn() {
		return getElement(ctnShoppingbtn);
	}

	public WebElement getBackBtn() {
		return getElement(backBtn);
	}

	public WebElement getSortDropdown() {
		return getElement(sortDropdown);
	}

	// --Click humburger icon and close function--//
	public boolean clickLeftPaneandClose() {
		try {
			getHumburgerIcon().click();
			Thread.sleep(1000);
			getCloseIcon().click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// --Get Invetory Items details function--//
	public boolean getInvetoryItemsname(String input) {
		List<WebElement> inventoryItems = getInventoryProdName();
		List<String> itemNames = new ArrayList<>();
		// Clicking a product
		for (WebElement inventoryItem : inventoryItems) {
			if (inventoryItem.getText().equals(input)) {
				inventoryItem.click();
				return true;
			} else {
				itemNames.add(inventoryItem.getText());
			}
		}

		// To verify list of product is asc/dec
		if (input.equals("a to z")) {
			if (sortingOrderofList(itemNames).equals("List asc order")) {
				return true;
			} else
				return false;
		} else if (input.equals("z to a")) {
			if (sortingOrderofList(itemNames).equals("List dec order")) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public String sortingOrderofList(List<String> input) {
		List<String> tempasc = new ArrayList<>();
		List<String> tempdec = new ArrayList<>();
		tempasc.addAll(input);
		tempdec.addAll(input);
		Collections.sort(tempasc);
		if (tempasc.equals(input)) {
			return "List asc order";
		} else if (input.equals(tempdec)) {
			return "List dec order";
		} else
			return "Not in order";
	}

	// --Click back button function--//
	public void clickBackBtn() {
		getBackBtn().click();
	}

	// --Add to card function--//
	public boolean productDetailsandAddToCart(String itemNameToCart) {

		if (getInventoryName().getText().equals(itemNameToCart))
			return true;
		else 
			return false;
	}

	
	  // --Verify the cart product is correct function--// 
	public boolean productInCart(String itemNameToCart) throws InterruptedException { 
		if(getCartIconCount().getText().equals("1")) {
			driver.get("https://www.saucedemo.com/cart.html");
			Thread.sleep(1000);
			if(getInventoryProdNameCartPage().getText().equals(itemNameToCart)) {
				return true;
			}
			else {
				return false;
			}
	  } else 
		  return false; 
	}
	 

	public void sortProduct(String sortBy) throws InterruptedException {
		listSelect(getSortDropdown(), sortBy);
		Thread.sleep(3000);

	}
	
	//To verify images on the page is broken
	public boolean verifyImageStatus() {
		List<WebElement> images = getImageEles();
		HttpClient client;
		HttpGet request;
		HttpResponse response;
		int invalidImageCount=0;
		for(WebElement image:images) {
		try {
			client = HttpClientBuilder.create().build();
			request = new HttpGet(image.getAttribute("src"));
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		if(invalidImageCount==images.size()) {
			return false;
		}
		else
			return true;
	}

}
