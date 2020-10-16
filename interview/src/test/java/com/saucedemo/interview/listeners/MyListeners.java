package com.saucedemo.interview.listeners;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.saucedemo.interview.mypagestest.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListeners implements ITestListener {
	public static WebDriver driver;
	protected static ExtentReports reports;
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	protected static ExtentTest test;
	String logText = "";

	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" on test start");
		test = reports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		logText = "Test method " + result.getMethod().getMethodName() + " Successful";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		System.out.println(result.getMethod().getMethodName()+" Success");
	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red> Exception Occurred, Click to see more details:"
				+ " </font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		WebDriver driver = ((BaseTest) result.getInstance()).driver;
		String screenShotPath = takeScreenshot(driver, result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<b><font color=red>" + "Screenshot of Failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (Exception e) {
			extentTest.get().fail("Test Failed, Cannot Attach Screenshot");
		}
		logText = "<b> Test Method " + result.getMethod().getMethodName() + " Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
		System.out.println(result.getMethod().getMethodName()+" Fail");
	}

	public void onTestSkipped(ITestResult result) {
		logText = "<b> Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
		System.out.println(result.getMethod().getMethodName()+" Skip");
	}

	public static String takeScreenshot(WebDriver driver, String ScreenShotName) {
		String fileName = getScreenshotName(ScreenShotName);
		String directory = System.getProperty("user.dir") + "/Screenshots/" + getDate() + "/";
		new File(directory).mkdirs();
		String path = directory + fileName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("*************************************");
			System.out.println("Screen Shot Stored at :" + path);
			System.out.println("*************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenshotName(String methodName) {
		Date date = new Date();
		String fileName = methodName + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

	public static String getDate() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_Y");
		String time = dateFormat.format(now);
		return time;
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on test sucess within percentage");
	}

	public void onStart(ITestContext context) {
		String fileName = "Automation Report_saucedemo_"
				+ new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-ms").format(new Date()) + ".html";
		String directory = System.getProperty("user.dir") + "/Extent_Reports/" + getDate() + "/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Saucedemo Automation Report");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Hostname", "Anandh - PC");
		reports.setSystemInfo("Tester Name", "Anandh");

	}

	public void onFinish(ITestContext context) {
		if (reports != null) {
			reports.flush();
		}

	}

}
