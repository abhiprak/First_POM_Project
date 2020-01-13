package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class HomePageTest {
	
	BasePage basePage;//  create the BasePage reference as the method init_driver is non static method
	Properties prop;//create a properties class reference
	WebDriver driver;//create the reference of WebDriver to store the value which is being returned by the init_driver method.
	LoginPage loginPage;
	HomePage homePage;//create HomePage class reference at the class level
	
	@BeforeMethod
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
		//The ultimate target is to get the HomePage class reference, so that we can execute the HomePageclass methods.
		//In the below line of code, we are getting he HomePage Class Reference:
		homePage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title=homePage.getHomePageTitle();
		System.out.println("Home Page Title is: " +title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "The Home Page title is missing");
	}
	//Below mentioned @Test comprises of two assertions:
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		Assert.assertTrue(homePage.isHeaderPresent(),"Home Page Header is not Present");
		String header=homePage.getHomePageHeaderValue();
		System.out.println("Home Page header is: " +header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER, "Home Page Header value is incorrect");
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() {
		Assert.assertTrue(homePage.isAccountNamePresent(),"Home Page Header is not Present");
		String accountName=homePage.getAccountNameValue();
		System.out.println("The name of the user who has logged in is: " +accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"), "Account Name is incorrect");
	}	
	
	@AfterMethod
	public void tearDown() {
		if (driver!=null) {
			driver.quit();
		}
	}

}
