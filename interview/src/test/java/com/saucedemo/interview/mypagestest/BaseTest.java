package com.saucedemo.interview.mypagestest;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.saucedemo.interview.listeners.MyListeners;
import com.saucedemo.interview.mypages.BasePage;
import com.saucedemo.interview.mypages.LoginPage;
import com.saucedemo.interview.mypages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends MyListeners{
	
	public Page page;

	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setup(String browser) {
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} 
		
		else if (browser.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		
		else {
			System.out.println("Wrong browser define in the xml");
		}
		
		driver.manage().window().maximize();
		driver.get(getPropertyData("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		page = new BasePage(driver);
		
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	public String getCurrentdate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public String getPropertyData(String obj) {
		FileInputStream propfile;
		Properties prop;
		String value = "";
		try {
			prop = new Properties();
			propfile = new FileInputStream(".\\src\\test\\java\\com\\saucedemo\\interview\\property\\data.properties");
			prop.load(propfile);
			value = prop.getProperty(obj);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
