package com.webapp.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertionTest {
	
	// hard assert : Hard Assert throws an AssertException immediately when an
	// assert statement fails and test suite continues with next @Test method
	
	@Test
	void hardAssertTest() {
		System.out.println("--- Hard Assert methods was started ----");
		// assertEquals(true, false);  // Assertion Error.
		assertTrue(true);
		System.out.println("--- Hard Assert is completed. ----");
	}
	
	// soft assert :- Soft Assert collects errors during @Test.
	// Soft Assert does not throw an exception when an assert fails
	@Test
	void softAssertTest() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("--- Soft Assert methods was started ----");
		softAssert.assertEquals(true, false);
		assertTrue(true);
		System.out.println("--- Soft Assert is completed. ----");
	}
}
