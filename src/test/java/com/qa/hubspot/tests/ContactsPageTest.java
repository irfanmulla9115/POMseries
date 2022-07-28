package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {
	
	Properties prop;
	WebDriver driver;

	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactsPage;

	@BeforeTest
	public void setup() {
		basepage = new BasePage();
		prop = basepage.init_prop(); // stored all the config file elements in prop
		driver = basepage.init_driver(prop); // sent all the elements through prop...check the basepage.java
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
		contactsPage = homepage.goToAddressBook();
		
	}
	
	@Test(priority=1)
	public void verifyAddressPageTitle() {
		String title =contactsPage.getAddressPageTitle();
		System.out.println("Contacts Page title:"+title);
		Assert.assertEquals(title, Constants.ADDRESS_PAGE_HEADER);
	}
	
	@DataProvider
	public void getContactsTestData() {
		
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_TESTDATA_SHEETNAME);
		
	}
	
	@Test(priority=2, dataProvider = "getContactsTestData")
	public void createaddressBook(String firstname1,String lastname1,String address11,String city1, String postcode1) {
		contactsPage.createAddressBook(firstname1,lastname1,address11,city1,postcode1);
	}
	
	
//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}
	
	
}
