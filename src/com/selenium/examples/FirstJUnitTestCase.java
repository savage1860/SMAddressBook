package com.selenium.examples;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;


public class FirstJUnitTestCase {
	public static String loginName;
	public static String loginPass;
	public static DriverSelenium driverSelenium;
	public static AddressBook addressBook;
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "com.selenium.examples.FirstJUnitTestCase");            
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("This is setUpBeforeClass()");
		
		loginName = "mytest001";
		loginPass = "mytest001";
		System.out.println("Parametres of test cases: \n    Login: " + loginName + 
				"\n    Password: " + loginPass + "\n\n");
		driverSelenium = new DriverSelenium();
		SignInPage signInPageElement = new SignInPage(driverSelenium);
		if (signInPageElement.isDisplayed() == true){
			signInPageElement.signIn(loginName, loginPass);
		}
		
		HomePage homePageElement = new HomePage(driverSelenium);
		if (homePageElement.isDisplayed() == true){
			if (homePageElement.getUserNameValue().equals(loginName)){
				System.out.println("Correct log in! " + homePageElement.getUserNameValue() + 
						" = " + loginName);
			}
		}		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driverSelenium.quitDriver(); 
		System.out.println("All TC were executed!");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("This is setUp()");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("This is tearDown()");
	}


//	@Test
//	public void test1logIn() {
//		SignInPage signInPageElement = new SignInPage(driverSelenium);
//		if (signInPageElement.isDisplayed() == true){
//			signInPageElement.signIn(loginName, loginPass);
//			System.out.println("Logged in!");
//		} else {
//			System.out.println("Page SignIn didn't display!");
//		}
//		
//		assertEquals("Comparison test fails", 2, 2);
//	}
	
	
	@Test
	public void listOfContactsFromGmailCsv() {
		addressBook = new AddressBook(driverSelenium, "Gmail.csv");
		
		  // 
		if (addressBook.compareTwoArrays(addressBook.getArrayFromFileGmailCsv(), addressBook.getArrayFromSite())){
			System.out.println("arrayFromFile equal to arrayFromSite");
		} else{
			System.out.println("arrayFromFile NOT equal to arrayFromSite");
		}
		
	//	assertTrue(addressBook.compareTwoArrays(addressBook.getArrayFromFileGmailCsv(), addressBook.getArrayFromSite()));
	}
	
	
	
	
}
