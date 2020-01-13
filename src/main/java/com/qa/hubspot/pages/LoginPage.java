package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	
	//1. Create Page Objects or Object Repository in the form of By locators
	
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	
	//2. Constructor of Page Class
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		elementUtil.waitForPageTitle(Constants.LOGIN_PAGE_TITLE);
	}
	
	//3. page actions or page methods
	
	public String getPageTitle() {
		String title = elementUtil.waitForPageTitle(Constants.LOGIN_PAGE_TITLE);
		//String title = driver.getTitle();
		System.out.println("Login Page title is " +title);
		return title;
	}
	
	public boolean verifySignUpLink() {
		return elementUtil.isElementDisplayed(signUpLink);
		//return driver.findElement(signUpLink).isDisplayed();
	}
	
	public HomePage doLogin(String username, String pwd) {
		System.out.println("Credentials are:" +username + " and " +pwd);
		elementUtil.doSendKeys(emailId, username);
	//	driver.findElement(emailId).sendKeys(username);
		elementUtil.doSendKeys(password, pwd);;
	//	driver.findElement(password).sendKeys(pwd);
		elementUtil.doClick(loginButton);
	//	driver.findElement(loginButton).click();
		// This method will return the object of the HomePage.java ==> which is ==> new HomePage() ==>
		//and so the Return type of the method will be "HomePage"
		
		//Write the following line of code to remove the try/catch block:
		
		elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE);
	//	try {
	//		Thread.sleep(6000);
	//	} catch (InterruptedException e) {
	//		e.printStackTrace();
	//	}
		return new HomePage(driver);
	}
}
