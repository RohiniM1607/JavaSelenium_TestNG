package com.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class Logger_TutorialsNinjaTest {

    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(Logger_TutorialsNinjaTest.class);

    @BeforeMethod
    public void setUp() {

        logger.info("========== Starting Test ==========");

        driver = new ChromeDriver();
        logger.info("Chrome browser launched");

        driver.manage().window().maximize();
        logger.info("Browser window maximized");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
        logger.info("TutorialsNinja website opened");
    }

    @DataProvider(name = "validData")
    public Object[][] validData() throws IOException {

        logger.info("Fetching valid login data from Excel");

        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
                allData[0]
        };
    }

    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() throws IOException {

        logger.info("Fetching invalid email data from Excel");

        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
                allData[1]
        };
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() throws IOException {

        logger.info("Fetching invalid password data from Excel");

        TutorialsNinjaDataProvider dp = new TutorialsNinjaDataProvider();
        Object[][] allData = dp.excelDataProvider();

        return new Object[][] {
                allData[2]
        };
    }

    @Test(dataProvider = "validData")
    public void loginWithValidData(String usermail, String password) throws InterruptedException {

        logger.info("Executing Valid Login Test");
        logger.info("Email Used: " + usermail);

        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        logger.debug("Clicked My Account");

        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        logger.debug("Clicked Login option");

        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        logger.debug("Entered Email");

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        logger.debug("Entered Password");

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        logger.info("Clicked Submit button");

        WebElement editAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Edit your account information']")));

        Assert.assertTrue(editAccount.isDisplayed(), "Valid login failed");

        logger.info("Valid Login Test Passed");
    }

    @Test(dataProvider = "invalidEmailData")
    public void loginWithInvalidEmail(String usermail, String password) throws InterruptedException {

        logger.info("Executing Invalid Email Login Test");
        logger.info("Email Used: " + usermail);

        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        logger.debug("Clicked My Account");

        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        logger.debug("Clicked Login option");

        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        logger.debug("Entered Invalid Email");

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        logger.debug("Entered Password");

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        logger.info("Clicked Submit button");

        WebElement invalidLoginWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='alert alert-danger alert-dismissible']")));

        Assert.assertTrue(invalidLoginWarning.isDisplayed(), "Warning message not displayed");

        logger.info("Invalid Email Login Test Passed");
        logger.warn("Login failed because of invalid email");
    }

    @Test(dataProvider = "invalidPasswordData")
    public void loginWithInvalidPassword(String usermail, String password) throws InterruptedException {

        logger.info("Executing Invalid Password Login Test");
        logger.info("Email Used: " + usermail);

        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        logger.debug("Clicked My Account");

        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        logger.debug("Clicked Login option");

        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(usermail);
        logger.debug("Entered Email");

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        logger.debug("Entered Invalid Password");

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        logger.info("Clicked Submit button");

        WebElement invalidLoginWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='alert alert-danger alert-dismissible']")));

        Assert.assertTrue(invalidLoginWarning.isDisplayed(), "Warning message not displayed");

        logger.info("Invalid Password Login Test Passed");
        logger.warn("Login failed because of invalid password");
    }

    @AfterMethod
    public void tearDown() {

        logger.info("Closing Browser");

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }

        logger.info("========== Test Finished ==========");
    }
}