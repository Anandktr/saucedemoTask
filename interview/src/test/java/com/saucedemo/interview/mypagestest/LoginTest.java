package com.saucedemo.interview.mypagestest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.interview.mypages.LoginPage;
import com.saucedemo.interview.mypages.ProductsPage;

public class LoginTest extends BaseTest {
	
	
	@Test(priority=0)
	public void loginWithoutUserNameandPassword() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		extentTest.get().info("Login without user name and password");
		loginPage.doLogin("", "");
		extentTest.get().info("Application should diplay \"Epic sadface: Username is required\" ");
		Assert.assertEquals(loginPage.getErrorMsg(), "Epic sadface: Username is required");
		extentTest.get().pass("Error message displayed as expected.");
	}
	
	@Test(priority=1)
	public void loginwithInvalidCredentials() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		extentTest.get().info("Login with \"qwerty\" and \"qwerty\"");
		loginPage.doLogin("qwerty", "qwerty");
		extentTest.get().info("Application should diplay \"Epic sadface: Username and password do not match any user in this service\" ");
		Assert.assertEquals(loginPage.getErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
		extentTest.get().pass("Error message displayed as expected.");
	}

	@Test(priority=2)
	public void loginwithValidCredentials() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		extentTest.get().info("Login with "+getPropertyData("validusername")+" and "+getPropertyData("password"));
		ProductsPage product = loginPage.doLogin(getPropertyData("validusername"), getPropertyData("password"));
		extentTest.get().info("Application should navigate to Product page");
		Assert.assertEquals(product.clickLeftPaneandClose(), true);
		extentTest.get().pass("Application navigate to product page successfully");
	}


}
