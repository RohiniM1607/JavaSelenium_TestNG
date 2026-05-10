package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath="//a[text()=\"Log in\"]")
    public WebElement loginbtn;

    @FindBy(xpath="//input[@id=\"loginusername\"]")
    public WebElement username;

    @FindBy(xpath="//input[@id=\"loginpassword\"]")
    public WebElement password;

    @FindBy(xpath="//a[@id=\"nameofuser\"]")
    public WebElement login_text;

    @FindBy(xpath="//button[text()='Log in']")
    public WebElement loginSubmitBtn;

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginbtn)).click();
    }

    public void setUserName(String strUserName) {
        wait.until(ExpectedConditions.visibilityOf(username)).clear();
        username.sendKeys(strUserName);
    }

    public void setPassword(String strPassword) {
        wait.until(ExpectedConditions.visibilityOf(password)).clear();
        password.sendKeys(strPassword);
    }

    public void clickLoginSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitBtn)).click();
    }

    public String getLogintext() {
        return wait.until(ExpectedConditions.visibilityOf(login_text)).getText();
    }

    public void login(String strUserName, String strPassword) {
        clickLoginLink();
        setUserName(strUserName);
        setPassword(strPassword);
        clickLoginSubmit();
    }
}