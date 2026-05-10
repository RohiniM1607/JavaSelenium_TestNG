package com.tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Logger log = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        driver.set(new ChromeDriver(options));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://www.demoblaze.com/");

        log.info("Browser launched");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
        log.info("Browser closed");
    }
}