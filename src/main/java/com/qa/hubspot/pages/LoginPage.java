package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;


//In page class never write Assertions-Its the rule of POM(refer blog by martin fowler on Google.com)
public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	// 1.By Locators:
	By username = By.id("input-email");
	By password = By.id("input-password");
	By loginButton = By.xpath("//input[@value='Login']");
	
	// 2. Constructor of the LoginPage class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3.Page Actions/Methods
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	// why homepage is written?--> bcz it is the principle og PageObject model that
	// when the method is landing to any other page then it should return the object
	// of that page.
	// In this scenario after login you will be landing on Homepage hence homepage
	// object is returned

	public HomePage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new HomePage(driver);
	}

}
