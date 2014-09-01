package com.selenium.examples;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSelenium {
	private WebDriver driver;
	private WebDriverWait driverWait;
	private String link;

	public DriverSelenium() {
		this("https://www.surveymonkey.com/user/sign-in/", 20);
	}
	
	public DriverSelenium(String link, long timeOutInSecond) {
		//ProfilesIni allProfiles = new ProfilesIni();
		//FirefoxProfile fp = new FirefoxProfile();
		//fp = allProfiles.getProfile("GeoUSRedwoodCity");
				// set something on the profile...
					//DesiredCapabilities dc = DesiredCapabilities.firefox();
					//dc.setCapability(FirefoxDriver.PROFILE, fp);
		//driver = new FirefoxDriver(fp);
		driver = new FirefoxDriver();		
		driverWait = new WebDriverWait(driver, timeOutInSecond);
		this.link = link;
		  // 
	    driver.get(link);
	   
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getDriverWait() {
		return driverWait;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public void quitDriver (){
		driver.quit();
	}

}
