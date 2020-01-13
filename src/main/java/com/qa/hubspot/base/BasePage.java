package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	
	/**
	 * This method is used to initialize the driver on the basis of browser name
	 * @param browserName
	 * @return driver
	 */
	
	public WebDriver init_driver(String browserName) {
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			//WebDriver driver=new ChromeDriver();
			
		//	WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();		
		}
		else if(browserName.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();			
		}
		else if(browserName.equals("safari")) {
			
		}
		else {
			System.out.println(browserName + "Browser name is incorrect. Please pass the correct browser name ...");
		}
		
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		//driver.get("https://app.hubspot.com/login");// We will get the url in the LoginPageTest.java
		return driver;
	}
	
	/**
	 * This method is used to read the properties from the config.properties file
	 * @return prop
	 */
	public Properties init_properties() {
		prop=new Properties();// class to read data from properties file
		try {
			FileInputStream ip=new FileInputStream("C:\\Users\\abhpraka\\eclipse-workspace\\NaveenPOMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			//FileInputStream class will establish the connection
			prop.load(ip);//prop.load will load all the properties
		} catch (FileNotFoundException e) {
		    //e.printStackTrace();
			System.out.println("Config file is missing ... PLease check");
		}
		catch (IOException e) {
		    //e.printStackTrace();
			System.out.println("Properties could not be loaded ... PLease check");
		}
		return prop;
	}

}
