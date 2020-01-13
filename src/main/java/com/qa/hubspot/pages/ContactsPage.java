package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	 
	//1. Create Page Objects or Object Repository in the form of By locators
	
		By createContactButton = By.xpath("//span[text()='Create contact']");
		By createContactFormButton = By.xpath("//li//div[text()='Create contact']");
		By email = By.xpath("//input[@data-field='email']");
		By firstName = By.xpath("//input[@data-field='firstname']");
		By lastName = By.xpath("//input[@data-field='lastname']");
		By jobTitle = By.xpath("//input[@data-field='jobtitle']");
		
		
	//2. Create Constructor of Contacts Page Class
		
		public ContactsPage(WebDriver driver) {
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}
		
	//3. Page actions or page methods
		
		public String getContactsPageTitle() {
			return elementUtil.waitForPageTitle(Constants.CONTACTS_PAGE_TITLE);
		}
		
		public void createNewContact(String mail, String fname, String lname, String jtitle) {
			elementUtil.waitForElementPresent(createContactButton);
			elementUtil.doClick(createContactButton);
			elementUtil.waitForElementPresent(email);
			elementUtil.doSendKeys(email, mail);
			elementUtil.waitForElementPresent(firstName);
			elementUtil.doSendKeys(firstName, fname);
			elementUtil.waitForElementPresent(lastName);
			elementUtil.doSendKeys(lastName, lname);
			elementUtil.waitForElementPresent(jobTitle);
			elementUtil.doActionsSendKeys(jobTitle, jtitle);
			//elementUtil.doSendKeys(jobTitle, jtitle);
			elementUtil.waitForElementPresent(createContactFormButton);
			elementUtil.doActionsClick(createContactFormButton);
		}
		
		
}
