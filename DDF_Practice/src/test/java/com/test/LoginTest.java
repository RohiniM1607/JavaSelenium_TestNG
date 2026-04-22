package com.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ddfpractice.DataProviderExcel;

public class LoginTest {
	WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/");
    }

    @DataProvider(name = "validData")
    public Object[][] validData() throws IOException {

        DataProviderExcel dp = new DataProviderExcel();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
            allData[0]
        };
    }

    @DataProvider(name = "invalidData")
    public Object[][] invalidData() throws IOException {

        DataProviderExcel dp = new DataProviderExcel();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
            allData[1]
        };
    }

    @Test(dataProvider = "validData")
    public void loginWithValidData(String username, String password)
            throws InterruptedException {

        driver.findElement(By.id("login2")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);

        WebElement welcomeText = driver.findElement(By.id("nameofuser"));

        Assert.assertTrue(welcomeText.isDisplayed());
        System.out.println("Valid Login Passed for: " + username);
    }

    @Test(dataProvider = "invalidData")
    public void loginWithInvalidData(String username, String password)
            throws InterruptedException {

        driver.findElement(By.id("login2")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);

        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        Assert.assertEquals(alertText, "Wrong password.");

        driver.switchTo().alert().accept();

        System.out.println("Invalid Login Passed for: " + username);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
