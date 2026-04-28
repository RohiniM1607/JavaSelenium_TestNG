package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	
	By userName = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By login = By.xpath("//button[normalize-space()='Login']");
	By loginTitle = By.xpath("//h5[text()='Login']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setUserName(String strUsername) {
		driver.findElement(userName).sendKeys(strUsername);
	}
	
	public void setPassword(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}
	
	public void clickLogin() {
		driver.findElement(login).click();
	}
	
	public String getLoginTitle() {
		return driver.findElement(loginTitle).getText();
	}

	public void login(String strUsername, String strPassword) {
		this.setUserName(strUsername);
		this.setPassword(strPassword);
		this.clickLogin();
	}
}
