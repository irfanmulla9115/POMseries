package com.qa.hubspot.pages;

import org.openqa.selenium.By;

import com.qa.hubspot.base.BasePage;

public class DealsPage extends BasePage{
	
	By deals =By.id("deals");
	
	public DealsPage() {
		System.out.println("Deals Page constructor");
		
	}
	public void createDeals(String name) {
		
		System.out.println("Deals method");
	}
	
	

}
