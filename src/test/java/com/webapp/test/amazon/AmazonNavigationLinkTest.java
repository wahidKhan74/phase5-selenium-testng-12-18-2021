package com.webapp.test.amazon;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AmazonNavigationLinkTest {
	
	// step1: formulate a test url & driver path
	String siteUrl = "https://www.amazon.in/";
	String driverPath = "drivers/chromedriver";
	WebDriver driver;
	SoftAssert softAssert;
	
	@BeforeMethod
	public void beforeMethod() {
		// step2: set selenium system properties
		System.setProperty("webdriver.chrome.driver", driverPath);
		// step3: instantiate selenium web-driver
		driver = new ChromeDriver();
		// step4: launch browser
		driver.get(siteUrl);
		softAssert = new SoftAssert();
	}

	@AfterMethod
	public void afterMethod() {
		// step6: close driver
		driver.close();
	}

	@Test
	public void testMobileNavLink() throws InterruptedException {
		// find mobile link 
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(3)"));
		mobileLink.click();
		String expected ="Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		String actual = driver.getTitle();
		
		Thread.sleep(3000);
		softAssert.assertEquals(expected, actual);
	}
	
	
	@Test
	public void testBestSellerNavLink() throws InterruptedException {
		// find mobile link 
		WebElement sellerLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(2)"));
		sellerLink.click();
		String expected ="Amazon.in Bestsellers: The most popular items on Amazon";
		String actual = driver.getTitle();
		Thread.sleep(3000);
		softAssert.assertEquals(expected, actual);
	}
	
	@Test
	public void testElectronicsNavLink() throws InterruptedException {
		// find mobile link 
		WebElement electroLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(5)"));
		electroLink.click();
		String expected ="Electronics Store: Buy Electronics products Online at Best Prices in India at Amazon.in";
		String actual = driver.getTitle();
		Thread.sleep(3000);
		softAssert.assertEquals(expected, actual);
	}

}
