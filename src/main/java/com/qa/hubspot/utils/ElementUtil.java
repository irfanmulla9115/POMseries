package com.qa.hubspot.utils;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		//jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		//jsUtil.flash(element);
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doActionsSendKeys(By locator, String value) {

		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();

	}
	
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doActionsClickKeys(By locator, String value) {

		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void getDropdownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("Total valuesin dropdown:" + optionsList.size());
		for (int i = 0; i < optionsList.size(); i++) {
			System.out.println(optionsList.get(i).getText());
		}
	}

	public void selectDropdown(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void selectDropdownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> valueList = select.getOptions();
		System.out.println(valueList.size());
		for (int i = 0; i < valueList.size(); i++) {
			if (valueList.get(i).getText().equals(value)) {
				valueList.get(i).click();
				break;
			}
		}
	}

	public void doSelectValueFromDropdownWithoutSelect(String locator, String locatorValue, String value) {
		List<WebElement> valuesList = null;
		if (locator.equals("xpath")) {
			valuesList = driver.findElements(By.xpath(locatorValue));
		} else if (locator.equals("css")) {
			valuesList = driver.findElements(By.cssSelector(locatorValue));
		}
		System.out.println(valuesList.size());
		for (int i = 0; i < valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}

	}

//wait custom methods : wait utils 
	public List<WebElement> visibilityofAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
		return driver.findElements(locator);
	}

	public WebElement waitForElementToBePresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}

	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return getElement(locator);
	}

	public WebElement waitForElementVisibilityLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator);
	}

	public String waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}

	public void waitAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}

	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

}
