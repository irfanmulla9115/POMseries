package com.qa.hubspot.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class ContactsPage {

	WebDriver driver;
	ElementUtil elementUtil;
	By newAddress = By.xpath("//a[text()='New Address']");

	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By address1 = By.id("input-address-1");
	By city = By.id("input-city");
	By postCode = By.id("input-postcode");
	By continueBtn = By.className("btn btn-primary");

	// Constructor of contacts
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getAddressPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.ADDRESS_PAGE_HEADER, 10);
	}

	public void createAddressBook(String firstname, String lastname, String Address1, String City, String postcode) {
		elementUtil.waitForElementToBeClickable(newAddress, 15);
		elementUtil.doClick(newAddress);

		elementUtil.waitForElementToBePresent(firstName, 5).sendKeys(firstname);
		elementUtil.waitForElementToBePresent(lastName, 5).sendKeys(lastname);
		elementUtil.waitForElementToBePresent(address1, 5).sendKeys(Address1);
		elementUtil.waitForElementToBePresent(city, 5).sendKeys(City);
		elementUtil.waitForElementToBePresent(postCode, 5).sendKeys(postcode);

//		elementUtil.waitForElementToBePresent(continueBtn, 5);
//		elementUtil.doClick(continueBtn);

	}
}
