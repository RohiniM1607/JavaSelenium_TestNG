package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	public BasePage(WebDriver driver2) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
