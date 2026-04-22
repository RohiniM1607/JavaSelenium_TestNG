package com.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ddfpractice.TutorialsNinjaDataProvider;

public class TutorialsNinjaTest {
	WebDriver driver;
	
	@BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
    }

    @DataProvider(name = "validData")
    public Object[][] validData() throws IOException {

        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
            allData[0]
        };
    }
    
    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() throws IOException {
        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
            allData[1]
        };
    }
    
    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() throws IOException {
        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
            allData[2]
        };
    }
    
	@Test(dataProvider = "validData")
    public void loginWithValidData(String usermail, String password)
            throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        WebElement editAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Edit your account information']")));
        Assert.assertTrue(editAccount.isDisplayed(), "Login Successful");
        System.out.println("Valid Login Passed for " + usermail);
        
    }
	
	@Test(dataProvider = "invalidEmailData")
	public void loginWithInvalidEmail(String usermail, String password)   throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        WebElement invalidLoginWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
        Assert.assertTrue(invalidLoginWarning.isDisplayed(), "Login Failed");
        System.out.println("Invalid Login Passed for " + usermail);
    }
	
	@Test(dataProvider = "invalidPasswordData")
	public void loginWithInvalidPassword(String usermail, String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        WebElement invalidLoginWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
        Assert.assertTrue(invalidLoginWarning.isDisplayed(), "Login Failed");
        System.out.println("Invalid Login Passed for " + usermail);
    }
	
	@AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
