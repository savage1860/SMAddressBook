package com.selenium.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressBook {
	private WebElement email;
	private WebElement password;
	private WebElement signInButton;
	private DriverSelenium driverSelenium;
	private ArrayList<TreeMap<String, String>> arrayFromFileGmailCsv;
	private ArrayList<TreeMap<String, String>> arrayFromSite;
	public final String EMAIL="addressbook03";
	public final String PASSWORD="address1234567";
	public final String ADDRESSBOOKLINK="https://www.surveymonkey.com/addressbook/";

	public AddressBook(DriverSelenium driverSelenium, String typeOfFile) {
 		this.setDriverSelenium(driverSelenium);
		  // load Address Book page
		driverSelenium.getDriver().get(ADDRESSBOOKLINK);
		  // delete list of contacts
		deleteListOfContacts();
		
		switch (typeOfFile){
		case ("Gmail.csv"):
			loadContactsFromGmailCsv();
			break;
		}
		
		fillArrayFromGmailCsv(6);
		System.out.println("arrayFromFileGmailCsv: ");
		System.out.println(arrayFromFileGmailCsv);
		fillArrayFromSite();
		System.out.println("arrayFromSite: ");
		System.out.println(arrayFromSite);
		
	}

	public DriverSelenium getDriverSelenium() {
		return driverSelenium;
	}
	
	public void setDriverSelenium(DriverSelenium driverSelenium) {
		this.driverSelenium = driverSelenium;
	}
	
	public WebElement getEmail() {
		this.email = driverSelenium.getDriver().findElement(By.id("Email"));
		return email;
	}
	
	public void setEmailValue(String email) {
		this.email = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("Email")));
	    this.email.sendKeys(email);
	}
	
	public WebElement getPassword() {
		this.password = driverSelenium.getDriver().findElement(By.id("Passwd"));
		return password;
	}
	
	public void setPasswordValue(String password) {
		this.password = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("Passwd")));
	    this.password.sendKeys(password);
	}
	
	
	public WebElement getSignInButton() {
		this.signInButton = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("signIn")));
	    return signInButton;
	}

	public void signIn (String email, String password){
		setEmailValue(email);
		setPasswordValue(password);
		getSignInButton();
			
		signInButton.click();
	}


	public ArrayList<TreeMap<String, String>> getArrayFromFileGmailCsv() {
		return arrayFromFileGmailCsv;
	}

	public void setArrayFromFile(ArrayList<TreeMap<String, String>> arrayFromFileGmailCsv) {
		this.arrayFromFileGmailCsv = arrayFromFileGmailCsv;
	}

	public ArrayList<TreeMap<String, String>> getArrayFromSite() {
		return arrayFromSite;
	}
	
	public void setArrayFromSite(ArrayList<TreeMap<String, String>> arrayFromSite) {
		this.arrayFromSite = arrayFromSite;
	}
	
	public void fillArrayFromSite() {
		try {
			Thread.sleep(4000); // sleep for 4 sec (adding information lasts about 4sec)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//int countOfParametrs = 6;
		arrayFromSite = new ArrayList<TreeMap<String, String>>();
		
		  // Get count of elements from table with contacts
		Integer countOfElementsInTable;
		countOfElementsInTable = Integer.parseInt(driverSelenium.getDriver().findElement(By.xpath("//span[3]/output")).getText());
		//System.out.println(countOfElementsInTable);
		
		String key = null;
		String element = null;
		
		for (int i = 1; i <= countOfElementsInTable; i++){
			TreeMap<String, String> elementOfArrayFromSite = new TreeMap<String, String>();
			
			  // choose first row, get row's id + click on it
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
			
			
			  // Close window with details info of contact
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Cancel')]"))).click();
		}
	}

	
	public void fillArrayFromGmailCsv(int countOfParametrs){
		arrayFromFileGmailCsv = new ArrayList<TreeMap<String, String>>();
				
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("c://My/JavaSelenium/ab_import_gmail_03.csv"));
		
			String line = "";
			Integer rowCount = -1; // first row dob't use (it consists column's names)
		
			while ((line = in.readLine()) != null){
				rowCount++;
				if (rowCount >= 1){
					TreeMap<String, String> elementOfArrayFromFile = new TreeMap<String, String>();
					String[] arrayOfLine = line.split(",",-1);
					//System.out.print(arrayOfLine);
					
					
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
					arrayFromFileGmailCsv.add(elementOfArrayFromFile);
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
	}
	
	// Function compare two arrays.
	// Return true if arrays are equal
	// Return fals if arrays aren't equal
	public boolean compareTwoArrays(ArrayList<TreeMap<String, String>> array1, ArrayList<TreeMap<String, String>> array2){
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
	
	public boolean deleteListOfContacts(){
		Integer countOfElementsInTable;
		String countOfElementsString = driverSelenium.getDriver().findElement(By.xpath("//span[3]/output")).getText();
		if (countOfElementsString.equals("")){
			countOfElementsInTable = 0;
		} else {
			countOfElementsInTable = Integer.parseInt(countOfElementsString);
			System.out.println("Count of elements: " + countOfElementsString);
		}
		
		if (countOfElementsInTable != 0){
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("m-select-all"))).click();
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("m-multi-delete-btn"))).click();
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Delete')]"))).click();
			
			try {
				Thread.sleep(2000); // sleep for 2 sec (deleting lasts about 2sec)
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			countOfElementsInTable = Integer.parseInt(driverSelenium.getDriver().findElement(By.xpath("//span[3]/output")).getText());
			System.out.println("Count of elements after deletion: " + countOfElementsString);
			//countOfElementsInTable = Integer.parseInt(driverSelenium.getDriverWait().until(
			//		ExpectedConditions.elementToBeClickable(By.xpath("//span[3]/output"))).getText());
			if (countOfElementsInTable == 0){
				  // After deleting page don't reload
				  // Load Address Book page one more time
				driverSelenium.getDriver().get(ADDRESSBOOKLINK);
				System.out.println("List of Contacts has been deleted.");
			} else {
				System.out.println("List of Contacts has NOT been deleted!!!");
				return false;
			}
		}
		return true;
	}
	
	public boolean loadContactsFromGmailCsv(){
		  
		if (driverSelenium.getDriver().findElement(By.id("tab-multiple")) == null){
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("ab-add-contacts-t"))).click();
			driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("ab-import-contacts-opt"))).click();
		}
		
		driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ad_gmail > span > b"))).click();
		driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("gmail-connect"))).click();
		
		
		
		WebDriver popup = null;
		Iterator<String> windowIterator = driverSelenium.getDriver().getWindowHandles().iterator();
		while (windowIterator.hasNext()) {
			String windowHandle = windowIterator.next();
			
			popup = driverSelenium.getDriver().switchTo().window(windowHandle);
			//String win = driver.getCurrentUrl();
			//System.out.println(popup.getCurrentUrl());
			// tttt
			  // Check (page loading too long)
			  // It's posible to add counter for preventing overcycling
			if (popup.getCurrentUrl().startsWith("https://www.surveymonkey.com/addressbook/popup/")) {
				popup = null;
				windowIterator = driverSelenium.getDriver().getWindowHandles().iterator();
				continue;
			}
			//System.out.print(win);
			if (popup.getCurrentUrl().startsWith("https://accounts.google.com/ServiceLogin")){
				
				signIn(EMAIL, PASSWORD);
				driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("submit_approve_access"))).click();
				
			}
		}
		
		windowIterator = driverSelenium.getDriver().getWindowHandles().iterator();
		while (windowIterator.hasNext()) {
			String windowHandle = windowIterator.next();
			
			popup = driverSelenium.getDriver().switchTo().window(windowHandle);
			System.out.println(popup.getCurrentUrl());
			if (popup.getCurrentUrl().equals(ADDRESSBOOKLINK)){
				System.out.println(popup.getCurrentUrl());
				try {
					Thread.sleep(4000); // sleep for 4 sec (adding information last about 4sec)
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("importnow"))).click();
				
			//	driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='good']/div/p[2]/span"))).click();
				
			}
		}

		
		return true;
	}
	
}
