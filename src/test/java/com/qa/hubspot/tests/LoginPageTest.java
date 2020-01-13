package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class LoginPageTest{
	
	//BM - T - AM: The browser will be launched and closed three times.
	//BT - T - AT: The browser will be launched and closed only once.
	
	BasePage basePage;//  create the BasePage reference as the method init_driver is non static method
	Properties prop;//create a properties class reference
	WebDriver driver;//create the reference of WebDriver to store the value which is being returned by the init_driver method.
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		basePage=new BasePage();//create object of BasePage class
		prop=basePage.init_properties();//init_properties method is returning a properties class reference
		String browser=prop.getProperty("browser");
		driver=basePage.init_driver(browser);//init_driver method is returning a driver, so we will store in a WebDriver reference.
		driver.get(prop.getProperty("url"));
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		loginPage=new LoginPage(driver);//If we do not initialize our loginPage reference, it will throw a NULL Pointer Exception.
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		//loginPage.getPageTitle();
		Assert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE, "Login Page title is incorrect");
		//for testing the failed test case in extent report through TestNG Listener:
		//Assert.assertEquals(loginPage.getPageTitle(), "hh", "Login Page title is incorrect");
	}
	
	@Test(priority=2)
	public void verifySignUpLink() {
		loginPage.verifySignUpLink();
		Assert.assertTrue(loginPage.verifySignUpLink(), "SignUp Link is incorrect");
	}
	
	@Test(priority=3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterTest
	public void tearDown() {
		if (driver!=null) {
			driver.quit();
		}
	}

}
