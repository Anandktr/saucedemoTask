package com.saucedemo.interview.mypages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	public static void getPage(String url) {
		driver.get(url);
	}
	
	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public void sendkeys(By locator, String keysToSend) {
		getElement(locator).sendKeys(keysToSend);
		
	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForWebElement(locator);
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error occured while creating element: " + locator.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		try {
			waitForWebElement(locator);
			elements = driver.findElements(locator);
			return elements;
		} catch (Exception e) {
			System.out.println("Some error occured while creating element: " + locator.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public String getText(By locator) {
		return getElement(locator).getText();
	}

	@Override
	public void waitForWebElement(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Unable to find the element: " + locator.toString());
		}
	}

	@Override
	public void waitForclickableWebElement(By locator) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			System.out.println("Unable to find the element: " + locator.toString());
		}
	}
	
	public void listSelect(WebElement element, String listVal) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(listVal);
	}
	
	public void javascriptClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
	}

}
