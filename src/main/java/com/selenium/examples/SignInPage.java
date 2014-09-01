package com.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SignInPage {
	private WebElement userName;
	private WebElement password;
	private WebElement signInButton;
	private DriverSelenium driverSelenium;
	
	
	
	public SignInPage(DriverSelenium driverSelenium) {
		this.driverSelenium = driverSelenium;
	}

	public WebElement getUserName() {
		this.userName = driverSelenium.getDriver().findElement(By.id("username"));
		return userName;
	}
	
	public void setUserNameValue(String userName) {
		this.userName = driverSelenium.getDriverWait().until(
				ExpectedConditions.elementToBeClickable(By.id("username")));
	    this.userName.sendKeys(userName);
	}
	
	public WebElement getPassword() {
		this.password = driverSelenium.getDriver().findElement(By.id("password"));
		return password;
	}
	
	public void setPasswordValue(String password) {
		this.password = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("password")));
	    this.password.sendKeys(password);
	}
	
	
	public WebElement getSignInButton() {
		this.signInButton = driverSelenium.getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='sign_in_form']/fieldset/button")));
	    return signInButton;
	}

	public DriverSelenium getDriverSelenium() {
		return driverSelenium;
	}

	public void signIn (String userName, String password){
		setUserNameValue(userName);
		setPasswordValue(password);
		getSignInButton();
			
		signInButton.click();
	}
	
	public boolean isDisplayed(){
		getUserName();
		getPassword();
		getSignInButton();
		if (userName.isDisplayed() && password.isDisplayed() && signInButton.isDisplayed() &&
				userName.isEnabled() && password.isEnabled() && signInButton.isEnabled()){
			return true;
		}
		return false;
	}

}
