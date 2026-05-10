package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.pages.DashboardPage;
import com.pages.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public LoginPage objLogin;
	public DashboardPage objDashboard;
	
	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/");
	}
	
	@AfterClass
	public void close() {
		driver.close();
	}
}
