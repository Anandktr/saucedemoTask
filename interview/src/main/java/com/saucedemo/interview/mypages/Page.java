package com.saucedemo.interview.mypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	static WebDriver driver;
	WebDriverWait wait;

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}

	public abstract String getPageTitle();

	public abstract void sendkeys(By locator, String keysToSend);

	// public abstract ReportPage get_Page(String url);

	public abstract WebElement getElement(By locator);

	public abstract void waitForWebElement(By locator);

	public abstract void waitForclickableWebElement(By locator);

	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
