package com.saucedemo.interview.mypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By userName = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	private By errorMsg = By.xpath("//h3[@data-test='error']");
	
	public WebElement getUsername() {
		return getElement(userName);
	}
	
	public WebElement getPassword() {
		return getElement(password);
	}
	
	public WebElement getLoginBtn() {
		return getElement(loginBtn);
	}
	
	public String getErrorMsg() {
		return getText(errorMsg);
	}
	
	public ProductsPage doLogin(String username, String password) {
		getUsername().clear();
		getUsername().sendKeys(username);
		getPassword().clear();
		getPassword().sendKeys(password);
		getLoginBtn().click();
		return getInstance(ProductsPage.class);
	}

}
