package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	public WebElement login;
	
	@FindBy(xpath="//h5[text()='Login']")
	public WebElement loginTitle;
	
	

	public void login(String strUsername, String strPassword) {
		username.sendKeys(strUsername);
		password.sendKeys(strPassword);
		login.click();
	}
	
	public String getLoginTitle() {
		return loginTitle.getText();
	}
}
