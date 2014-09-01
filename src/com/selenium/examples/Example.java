package com.selenium.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Example {
	public static String loginName = "mytest001";
	public static String loginPass = "mytest001";

	public static void main(String[] args)  {
		System.out.println("Parametres of test cases: \n    Login: " + loginName + 
				"\n    Password: " + loginPass + "\n");
		System.out.println("Expected results: \n    1. Correct login \n    2. Equal arrays\n\n");
		System.out.println("Results: \n");
		DriverSelenium driverSelenium = new DriverSelenium();
		SignInPage signInPageElement = new SignInPage(driverSelenium);
		if (signInPageElement.isDisplayed() == true){
			signInPageElement.signIn(loginName, loginPass);
		}
		
		HomePage homePageElement = new HomePage(driverSelenium);
		if (homePageElement.isDisplayed() == true){
			if (homePageElement.getUserNameValue().equals(loginName)){
				System.out.println("1. Correct log in! " + homePageElement.getUserNameValue() + 
						" = " + loginName);
			}
			else{
				System.out.println("1. Incorrect log in... " + homePageElement.getUserNameValue() + 
						" <> " + loginName);
			}
		}	
		
		AddressBook	addressBook = new AddressBook(driverSelenium, "Gmail.csv");	
		
		  // Arrays comparing with own function
		if (addressBook.compareTwoArrays(addressBook.getArrayFromFileGmailCsv(), addressBook.getArrayFromSite())){
			System.out.println("2. arrayFromFileGmailCsv equal to arrayFromSite\n");
		} else{
			System.out.println("2. arrayFromFileGmailCsv NOT equal to arrayFromSite\n");
		}
		addressBook.deleteListOfContacts(); 
		
		driverSelenium.quitDriver(); 
		System.out.println("All TC were executed!");
	}
	

	public static ArrayList<TreeMap<String, String>> getArrayFromGmailCsv(int countOfParametrs){
		ArrayList<TreeMap<String, String>> arrayFromFile = new ArrayList<TreeMap<String, String>>();
				
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("c://My/JavaSelenium/ab_import_gmail_03.csv"));
		
			String line = "";
			Integer rowCount = -1;
		
			while ((line = in.readLine()) != null){
				rowCount++;
				if (rowCount >= 1){
					TreeMap<String, String> elementOfArrayFromFile = new TreeMap<String, String>();
					String[] arrayOfLine = line.split(",",-1);
					
					
					for (int i=0; i<arrayOfLine.length && i<countOfParametrs; i++) {
						String element = arrayOfLine[i];
						String key = "";
						switch(i+1) {
							case 1: key = "E-mail";
									break;
							case 2: key = "Given Name";
									break;
							case 3: key = "Family Name";
									break;
							case 4: key = "Custom 1";
									break;
							case 5: key = "Custom 2";
									break;
							case 6: key = "Custom 3";
									break;
							case 7: key = "Custom 4";
									break;
							case 8: key = "Custom 5";
									break;
							case 9: key = "Custom 6";
									break;
						}
						
						elementOfArrayFromFile.put(key, element.toLowerCase());
					}
					arrayFromFile.add(elementOfArrayFromFile);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arrayFromFile;
	}
	
	public static ArrayList<TreeMap<String, String>> getArrayFromSite(){
		DriverSelenium driverSelenium = new DriverSelenium();
		
		SignInPage signInPageElement = new SignInPage(driverSelenium);
		if (signInPageElement.isDisplayed() == true){
			signInPageElement.signIn(loginName, loginPass);
		} else {
			System.out.println("Page SignIn didn't display!");
		}
		
		System.out.println("Logged in!");
		
		  // Check for correct Log In
		HomePage homePageElement = new HomePage(driverSelenium);
		if (homePageElement.isDisplayed() == true){
			if (homePageElement.getUserNameValue().equals(loginName)){
				System.out.println("Correct log in! " + homePageElement.getUserNameValue() + 
						" = " + loginName);
			}
		}		
		
		driverSelenium.getDriver().get("https://www.surveymonkey.com/addressbook/");
				
		ArrayList<TreeMap<String, String>> arrayFromSite = new ArrayList<TreeMap<String, String>>();
		
		  // Get count of elements in table with contacts
		Integer countOfElementsInTable;
		countOfElementsInTable = Integer.parseInt(driverSelenium.getDriver().findElement(By.xpath("//span[2]/output")).getText());
		
		
		String key = null;
		String element = null;
		
		for (int i = 1; i <= countOfElementsInTable; i++){
			TreeMap<String, String> elementOfArrayFromSite = new TreeMap<String, String>();
			
			  // found the first row and click on it + get row id 
			String lineId;
			if (i == 1){
				driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//td[2]/span"))).click();
				lineId = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//td[2]/span"))).getAttribute("data-eid").substring(6);
			} else{
				driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//tr["+i+"]/td[2]/span"))).click();
				lineId = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//tr["+i+"]/td[2]/span"))).getAttribute("data-eid").substring(6);
			}
			
			  // Get E-mail
			key = "E-mail";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_email"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
			
								
			  // Get Given Name
			key = "Given Name";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_first_name"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
		
			
			  // Get Family Name
			key = "Family Name";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_last_name"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
			
			
			  // Get Custom 1
			key = "Custom 1";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_custom_value1"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
			
			
			  // Get Custom 2
			key = "Custom 2";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_custom_value2"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
			
			
			  // Get Custom 3
			key = "Custom 3";
			element = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit_" +lineId+ "_custom_value3"))).getAttribute("value");
			elementOfArrayFromSite.put(key, element.toLowerCase());
			
			// Put TreeMap in ArrayList
			arrayFromSite.add(elementOfArrayFromSite);
			
			
			  // Close window with contact details
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("edit-contact-cancel-btn"))).click();
		}
		
			// Sign off
		driverSelenium.quitDriver();
		
		return arrayFromSite;
	}
	
	// Function compare two arrays.
	// Return true if arrays are equal
	// Return fals if arrays aren't equal
	public static boolean compareTwoArrays(ArrayList<TreeMap<String, String>> array1, ArrayList<TreeMap<String, String>> array2){
		  // If both arrays are empty, return true. 
		  // If array1 is empty and array2 isn't, return false 
		  // And visa versa 
		if (array1 == null){
				if (array2 == null){
					return true;
				} else{
					return false;
				}
		} else if (array2 == null){
			return false;
		}
		
		// if arrays' length are different, return false
		if (array1.size() != array2.size())
			return false;
		
		  // Choose each element of array "array1" in array "array2". 
		  // If at least one of them don't found - return false
		TreeMap<String, String> element = new TreeMap<String, String>();
		Iterator<TreeMap<String, String>> iterator = array1.iterator();
		while (iterator.hasNext()){
			element = iterator.next();
			if (!array2.contains(element))
				return false;
		}
		return true;
	}

}
