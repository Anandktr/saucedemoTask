package com.saucedemo.interview.mypagestest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.interview.mypages.LoginPage;

public class ProductPageTestInvalidUser extends BaseTest {

	@Test(priority=0)
	public void clicktheProductwithProbUser() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		product = loginPage.doLogin(getPropertyData("invalidusername"), getPropertyData("password"));
		extentTest.get().info("Login with "+getPropertyData("invalidusername")+" and "+getPropertyData("password"));
		Assert.assertEquals(product.getInvetoryItemsname(getPropertyData("productName")), true);
		extentTest.get().pass("Successfully click the product: "+getPropertyData("productName"));
	}
	
	@Test(priority=1)
	public void verifyProductDetailsandAddToCartwithProbUser() {
		extentTest.get().info("Verify the product details and click Addtocart button");
		product.getAddToCartBtn().click();
		extentTest.get().info("Clicked the Addtocart button");
		if(!product.productDetailsandAddToCart(getPropertyData("productName"))) {
			extentTest.get().fail("Product name is not match in product details page.");
		}
		Assert.assertEquals(product.productDetailsandAddToCart(getPropertyData("productName")), true);
		extentTest.get().pass("Successfully Addtocart the product");
	}

	@Test(priority=2)
	public void verifyProductInCartwithProbUser() throws InterruptedException {
		extentTest.get().info("Going to verify the product in cart");
		if(!product.productInCart(getPropertyData("productName"))) {
			extentTest.get().fail("Product name is not match in cart page.");
		}
		Assert.assertEquals(product.productInCart(getPropertyData("productName")), true);
		extentTest.get().pass("Product present in cart");
	}
	
	@Test(priority=3)
	public void verifySortOperation() throws InterruptedException {
		product.getCtnShoppingbtn().click();
		extentTest.get().info("Product page displayed succesfully");
		product.sortProduct(getPropertyData("sortBy"));
		extentTest.get().info("Products are sorted by "+ getPropertyData("sortBy"));
		if(!product.getInvetoryItemsname("z to a")) {
			extentTest.get().fail("Products are not sorted");
		}
		Assert.assertEquals(product.getInvetoryItemsname("z to a"), true);
		extentTest.get().pass("Products are sorted successfully");
	}
	
	@Test(priority=4)
	public void verifyImageStatus() throws InterruptedException {
		extentTest.get().info("Verify the images are load successfully");
		if(!product.verifyImageStatus()) {
			extentTest.get().fail("Images are not loaded");
		}
		Assert.assertEquals(product.verifyImageStatus(), true);
		extentTest.get().pass("Images are loaded successfully");
	}
}
