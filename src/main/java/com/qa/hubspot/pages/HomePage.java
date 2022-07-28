package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	By accountName = By.xpath("//div[@id='content']/h2[1]");
	By addressBook = By.linkText("Address Book");
	
	//Constructor of homepage
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		
	}
	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.HOMEPAGE_PAGE_TITLE, 10);
	}
	

	public String getAccountName() {
		elementUtil.waitForElementToBePresent(accountName, 10);
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	public ContactsPage goToAddressBook() {
		clickOnAddressbook();
		return new ContactsPage(driver);
	}
	
	
	private void clickOnAddressbook() {
		elementUtil.waitForElementToBeVisible(addressBook,10);
		elementUtil.doClick(addressBook);
	}
	
	

}
