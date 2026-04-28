package com.test.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import com.test.utilities.DPExcel;

@Listeners(TestListener.class)
public class BaseTest {

    static WebDriver driver;
    Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
    	log.info("Starting browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Browser started successfully");
    }


    @Test(dataProvider = "validLoginData", dataProviderClass = DPExcel.class)
    public void validLoginTest(String email, String password) {

        log.info("Valid Login Test");
        log.info("Email: " + email);

        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        try {
            Assert.assertTrue(
                driver.findElements(By.linkText("Logout")).size() > 0,
                "Login failed"
            );

            log.info("Login successful");

        } catch (AssertionError e) {
            log.error("Valid login failed", e);
            throw e;
        }
    }


    @Test(dataProvider = "invalidLoginData", dataProviderClass = DPExcel.class)
    public void invalidLoginTest(String email, String password) throws Exception {
    	
    	log.warn("Invalid Login Test started");
        log.warn("Testing with Email: " + email);

        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        try {
            Assert.assertTrue(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed());
            log.info("Error message displayed");

        } catch (Exception e) {
            log.error("Invalid login not show error message", e);
            throw e;
        }
    }

 
    @Test(dataProvider = "searchData", dataProviderClass = DPExcel.class)
    public void searchTest(String keyword) {
    	log.info("Search Test started");
        log.info("Keyword: " + keyword);

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.name("search")).sendKeys(keyword);
        driver.findElement(By.cssSelector(".btn-default")).click();
        Assert.fail("Force failure");

        try {
            Assert.assertTrue(driver.findElements(By.xpath("//div[@class='product-thumb']")).size() >= 0);
            log.info("Search executed");

        } catch (AssertionError e) {
            log.error("Search failed", e);
            throw e;
        }
    }

    @AfterMethod
    public void close() {
    	log.info("Closing browser");
        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }
}