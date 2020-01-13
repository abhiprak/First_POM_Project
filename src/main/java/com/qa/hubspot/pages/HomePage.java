package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
		//1. Create Page Objects or Object Repository in the form of By locators
	
		By header = By.className("private-page__title");
		By accountName = By.cssSelector("a#account-meny>span");
		By mainContactsLink = By.id("nav-primary-contacts-branch");
		By childContactsLink = By.id("nav-secondary-contacts");
				
		//2. Constructor of Page Class
		
		public HomePage(WebDriver driver) {
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}
		
		//3. page actions or page methods
		
		public String getHomePageTitle() {
			
			return elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE);
			//return driver.getTitle();
		}
		
		public boolean isHeaderPresent() {
			return elementUtil.isElementDisplayed(header);
			//return driver.findElement(header).isDisplayed();
		}
		
		public String getHomePageHeaderValue() {
			return elementUtil.doGetText(header);
			//return driver.findElement(header).getText();
		}
		
		public boolean isAccountNamePresent() {
			return elementUtil.isElementDisplayed(accountName);
			//return driver.findElement(accountName).isDisplayed();
		}
		
		public String getAccountNameValue() {
			return elementUtil.doGetText(accountName);
			//return driver.findElement(accountName).getText();
		}		
		
		public void clickOnContacts() {
			elementUtil.waitForElementPresent(mainContactsLink);
			elementUtil.doClick(mainContactsLink);
			elementUtil.waitForElementPresent(childContactsLink);
			elementUtil.doClick(childContactsLink);
		}
		
		public ContactsPage goToContactsPage() {
			clickOnContacts();//We can call clickOnContacts() method inside the goToContactsPage() method, 
			//since both are non-static, we can call method inside a method without creating the object.
			
			return new ContactsPage(driver);
		}
		
		
}
