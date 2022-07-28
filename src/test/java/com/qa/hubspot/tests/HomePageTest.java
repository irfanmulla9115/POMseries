package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest {

	Properties prop;
	WebDriver driver;

	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;

	@BeforeTest
	public void setup() {
		basepage = new BasePage();
		prop = basepage.init_prop(); // stored all the config file elements in prop
		driver = basepage.init_driver(prop); // sent all the elements through prop...check the basepage.java
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homepage.getHomePageTitle();
		System.out.println("title of homepage is:"+title);
		Assert.assertEquals(title, Constants.HOMEPAGE_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifygetAccountNameTest() {
		String accountName = homepage.getAccountName();
		System.out.println("Logged account name is:"+accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	
	
	
}
