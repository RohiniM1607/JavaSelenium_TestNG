package TestNG_Assertion;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoBlazeAssert {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void validation() {

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys("RohiniM");

        driver.findElement(By.id("loginpassword")).sendKeys("Rohini_16");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String alertMessage = alert.getText();
            alert.accept();

            Assert.fail("Expected successful login, but got alert: " + alertMessage);

        } catch (org.openqa.selenium.TimeoutException e) {

            WebElement welcomeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//a[text()='Welcome RohiniM']")));

            Assert.assertEquals(welcomeText.getText(), "Welcome RohiniM");
            System.out.println("Login Successful - Assertion Passed");
        }
    }

    @Test
    public void InvalidUserName() {

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys("Rohini");

        driver.findElement(By.id("loginpassword")).sendKeys("Rohini_16");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();

        Assert.assertEquals(alertMessage, "Wrong password.");

        System.out.println("Invalid Username Assertion Passed");
        alert.accept();
    }

    @Test
    public void InvalidPassword() {

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys("RohiniM");

        driver.findElement(By.id("loginpassword")).sendKeys("Rohini_1");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();

        Assert.assertEquals(alertMessage, "Wrong password.");

        System.out.println("Invalid Password Assertion Passed");
        alert.accept();
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}