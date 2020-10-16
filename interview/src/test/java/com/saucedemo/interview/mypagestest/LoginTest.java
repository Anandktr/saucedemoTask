package com.saucedemo.interview.mypagestest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.interview.mypages.LoginPage;
import com.saucedemo.interview.mypages.ProductsPage;

public class LoginTest extends BaseTest {
	
	
	@Test(priority=0)
	public void loginWithoutUserNameandPassword() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.doLogin("", "");
		Assert.assertEquals(loginPage.getErrorMsg(), "Epic sadface: Username is required");
	}
	
	@Test(priority=1)
	public void loginInvalid() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		loginPage.doLogin("qwerty", "qwerty");
		Assert.assertEquals(loginPage.getErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
	}

	@Test(priority=2)
	public void loginValid() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		ProductsPage product = loginPage.doLogin(getPropertyData("validusername"), getPropertyData("password"));
		Assert.assertEquals(product.clickLeftPaneandClose(), true);
	}


}
