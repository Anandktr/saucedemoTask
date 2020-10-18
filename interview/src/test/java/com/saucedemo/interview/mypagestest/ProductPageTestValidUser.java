package com.saucedemo.interview.mypagestest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.interview.mypages.LoginPage;
import com.saucedemo.interview.mypages.ProductsPage;

public class ProductPageTestValidUser extends BaseTest {

	@Test(priority=0)
	public void clicktheProduct() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		product = loginPage.doLogin(getPropertyData("validusername"), getPropertyData("password"));
		extentTest.get().info("Login with "+getPropertyData("validusername")+" and "+getPropertyData("password"));
		Assert.assertEquals(product.getInvetoryItemsname(getPropertyData("productName")), true);
		extentTest.get().pass("Successfully click the product: "+getPropertyData("productName"));
	}
	
	@Test(priority=1)
	public void verifyProductDetailsandAddToCart() {
		extentTest.get().info("Going to click Addtocart button");
		product.getAddToCartBtn().click();
		extentTest.get().info("Clicked the Addtocart button");
		Assert.assertEquals(product.productDetailsandAddToCart(getPropertyData("productName")), true);
		extentTest.get().pass("Successfully Addtocart the product and details of the product is correct.");
	}
	
	@Test(priority=2)
	public void verifyProductInCart() throws InterruptedException {
		extentTest.get().info("Going to verify the product in cart");
		Assert.assertEquals(product.productInCart(getPropertyData("productName")), true);
		extentTest.get().pass("Correct product present in cart");
	}
	
	@Test(priority=3)
	public void verifySortOperation() throws InterruptedException {
		product.getCtnShoppingbtn().click();
		extentTest.get().info("Product page displayed succesfully");
		product.sortProduct(getPropertyData("sortBy"));
		extentTest.get().info("Products are sorted by "+ getPropertyData("sortBy"));
		Assert.assertEquals(product.getInvetoryItemsname("z to a"), true);
		extentTest.get().pass("Products are sorted successfully");
	}
	
}
