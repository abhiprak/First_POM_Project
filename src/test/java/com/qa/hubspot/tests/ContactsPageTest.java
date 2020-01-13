package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	
	BasePage basePage;//  create the BasePage reference as the method init_driver is non static method
	Properties prop;//create a properties class reference
	WebDriver driver;//create the reference of WebDriver to store the value which is being returned by the init_driver method.
	LoginPage loginPage;
	HomePage homePage;//create HomePage class reference at the class level
	ContactsPage contactsPage;//Create a ContactsPage class reference
	
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
		contactsPage=homePage.goToContactsPage();
	}
	
	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String title=contactsPage.getContactsPageTitle();
		System.out.println("Conatcts page title is: " +title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data=ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void createConatctsTest(String email, String firstName, String lastName, String jobTitle) throws InterruptedException {
		
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
		
		//contactsPage.createNewContact("test@gmail.com", "abhi", "praka", "QA");
		//We can declare the input parameters in our config.properties file and call the same from there.
		//But if we want to test it with multiple values, we need to fetch the values from say an excel sheet
		//and then use the @DataProvider concept to fetch the data and give it to the createNewContact() method.
	}
	
	
	
	
	
	
	
	@AfterMethod
	public void tearDown() {
		if (driver!=null) {
			driver.quit();
		}
	}


}
