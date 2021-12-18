package com.webapp.test.amazon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AmazonHomepageTest {

	// step1: formulate a test url & driver path
	String siteUrl = "https://www.amazon.in/";
	String driverPath = "drivers/chromedriver";
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// step2: set selenium system properties
		System.setProperty("webdriver.chrome.driver", driverPath);
		// step3: instantiate selenium web-driver
		driver = new ChromeDriver();
		// step4: launch browser
		driver.get(siteUrl);
	}

	@AfterMethod
	public void afterMethod() {
		// step6: close driver
		driver.close();
	}

	@Test
	public void testHomePageTitle() {
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}
	
	@Test
	void testHomepageSoruceUrl() {
		assertEquals(siteUrl, driver.getCurrentUrl());
	}

	@Test
	public void testSearchData() {
		// find search box
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Ipohne 12 max pro");
		searchBox.submit();
		String expected = "Amazon.in : Ipohne 12 max pro";
		String actual = driver.getTitle();
		assertEquals(expected, actual);
	}

}
