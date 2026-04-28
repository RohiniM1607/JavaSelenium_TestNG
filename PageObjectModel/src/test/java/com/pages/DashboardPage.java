package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
	WebDriver driver;
	
	By dashboardTitle = By.xpath("//h6[text()='Dashboard']");
	
	

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getHomePageTitle() {
		return driver.findElement(dashboardTitle).getText();
	}
}
