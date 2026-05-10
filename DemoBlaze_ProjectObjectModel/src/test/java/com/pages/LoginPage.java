package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="login2")
	public WebElement loginBtn;
	
	@FindBy(id="loginusername")
	public WebElement username;
	
	@FindBy(id="loginpassword")
	public WebElement password;
	
	@FindBy(xpath="//button[text()='Log in']")
	public WebElement loginSubmit;
	
	@FindBy(xpath = "//a[text()='Welcome RohiniM']")
	private WebElement loginText;
	
	public void OpenLogin() {
		loginBtn.click();
	}
	
	public void Login(String strUserName, String strPassword) {
		wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(strUserName);
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(strPassword);
		wait.until(ExpectedConditions.elementToBeClickable(loginSubmit)).click();
	}

	public String verifyLogin() {
		return wait.until(ExpectedConditions.visibilityOf(loginText)).getText();
	}
}
