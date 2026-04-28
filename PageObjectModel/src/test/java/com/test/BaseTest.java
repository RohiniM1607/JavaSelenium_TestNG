package com.test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.pages.DashboardPage;
import com.pages.LoginPage;

public class BaseTest {
	public static WebDriver driver;
	LoginPage objLogin;
	DashboardPage objDashboard;
	
	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@AfterClass
	public void close() {
		driver.close();
	}

}
