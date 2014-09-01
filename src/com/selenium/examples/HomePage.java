package com.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
	private DriverSelenium driverSelenium;
	private WebElement userName;
	private WebElement signOffButton;
	

	public HomePage(DriverSelenium driverSelenium) {
		this.driverSelenium = driverSelenium;
	}

	public DriverSelenium getDriverSelenium() {
		return driverSelenium;
	}

	public WebElement getUserName() {
		userName = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("userAcctTab_MainMenu")));
	   // userName = driverSelenium.getDriver().findElement(By.id("userAcctTab_MainMenu"));
		return userName;
	}
	
	public String getUserNameValue() {
		getUserName();
		return userName.getText();		
	}

	
	public WebElement getSignOffButton() {
		getUserName().click();
	    //signOffButton = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.last > a")));
	    signOffButton = driverSelenium.getDriver().findElement(By.cssSelector("li.last > a"));
		return signOffButton;
	}

	
	public boolean isDisplayed(){
		getUserName();
		getSignOffButton();
		if (userName.isDisplayed() && signOffButton.isDisplayed() &&
				userName.isEnabled() && signOffButton.isEnabled()){
			return true;
		} 
		return false;
	}
	
	public void signOff(){
		getUserName();
		getSignOffButton();
		if (isDisplayed()){
			signOffButton.click();
			System.out.println("Signed off!!!");
		}
	}
	
}
