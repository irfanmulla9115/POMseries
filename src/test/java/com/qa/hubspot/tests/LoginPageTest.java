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
//In Test class never write driver methods (eg:-driver.get,driver.click..etc) in any test class.
//pre conditions --> Test    --> actual vs expected --> post step
//@BeforeTest     --> @Test   --> Assert             --> @AfterTest

public class LoginPageTest {

	Properties prop;
	WebDriver driver;

	BasePage basepage;
	LoginPage loginpage;

	@BeforeTest
	public void setup() {
		basepage = new BasePage();
		prop = basepage.init_prop(); // stored all the config file elements in prop
		driver = basepage.init_driver(prop); // sent all the elements through prop...check the basepage.java
		loginpage = new LoginPage(driver);
	}

	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("Title of the login page is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Title not found...");

	}
	
	@Test(priority=2)
	public void loginTest(){
		HomePage homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getAccountName(), prop.getProperty("accountname"), "Login Failed...");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
