package com.webapp.test.group;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BrowserBasedGroupTest {

	String amazonUrl = "https://www.amazon.in/";
	String facebookUrl = "https://www.facebook.com/";

	String chromePath = "drivers/chromedriver";
	String firefoxPath = "drivers/geckodriver";

	WebDriver driverOne;
	WebDriver driverTwo;
	WebDriverWait wait;
	
	// test group chrome only 
	@Test(groups = "ChromeOnly")
	public void launchChromeTest() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driverOne = new ChromeDriver();
		driverOne.get(amazonUrl);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods="launchChromeTest", priority=0)
	public void testHomePageTitle() {
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driverOne.getTitle();
		assertEquals(expected, actual);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods="launchChromeTest", priority=1)
	void testHomepageSoruceUrl() {
		assertEquals(amazonUrl, driverOne.getCurrentUrl());
	}

	@Test(groups = "ChromeOnly", dependsOnMethods="launchChromeTest", priority=2)
	public void testSearchData() throws InterruptedException {
		// find search box
		WebElement searchBox = driverOne.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Ipohne 12 max pro");
		searchBox.submit();
		String expected = "Amazon.in : Ipohne 12 max pro";
		String actual = driverOne.getTitle();
		Thread.sleep(3000);
		assertEquals(expected, actual);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods="launchChromeTest", priority=3)
	public void closeChrome() {
		driverOne.close();
	}

	// test group frirefox
	@Test(groups = "FirefoxOnly")
	public void launchFireFoxTest() {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driverTwo = new FirefoxDriver();
		wait = new WebDriverWait(driverTwo, Duration.ofSeconds(40));
		driverTwo.get(facebookUrl);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="launchFireFoxTest",priority=0)
	public void testFaceBookHomePage() {
		String expected ="Facebook - Login or sign up";
		assertEquals(driverTwo.getTitle(), driverTwo.getTitle());
	
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="launchFireFoxTest",priority=1)
	public void testFieldsLogin() {
		WebElement email = driverTwo.findElement(By.id("email"));
		WebElement password = driverTwo.findElement(By.id("pass"));
		WebElement submit = driverTwo.findElement(By.name("login"));
		assertEquals(true, email.isDisplayed());
		assertEquals(true, password.isDisplayed());
		assertEquals(true, submit.isDisplayed());
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="launchFireFoxTest",priority=2)
	public void testFailureLogin() {
		WebElement email = driverTwo.findElement(By.id("email"));
		WebElement password = driverTwo.findElement(By.id("pass"));
		WebElement login = driverTwo.findElement(By.name("login"));
		
		email.sendKeys("abc@gmail.com");
		password.sendKeys("abc@123");
		login.submit();
		// Conditional timeouts
		// WebElement error = driver.findElement(By.cssSelector("#error_box > div.fsl.fwb.fcb"));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", error.getText());
		
		// WebElement error2 = driver.findElement(By.cssSelector("#error_box > div:nth-child(2)"));
		WebElement error2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div:nth-child(2)")));
		assertEquals("Invalid username or password", error2.getText());
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods="launchFireFoxTest", priority=3)
	public void closeFireFoxTest() {
		driverTwo.close();
	}
}
