package com.saucedemo.interview.mypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage{

	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	private By humburgerIcon = By.xpath("//button[text()='Open Menu']");
	private By closeIcon = By.xpath("//button[text()='Close Menu']");
	
	public WebElement getHumburgerIcon() {
		return getElement(humburgerIcon);
	}
	
	public WebElement getCloseIcon() {
		return getElement(closeIcon);
	}
	
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

}
