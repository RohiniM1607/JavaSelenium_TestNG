package testng.Demo_CommandLineTest;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoBlazeParallel {
	public WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.out.println("Starting Parallel Execution...");
    }

    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless");   // keep commented to see parallel browsers

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        System.out.println("Browser opened on Thread: " + Thread.currentThread().getId());
    }

    @Test
    public void validation() {
        System.out.println("validation running on Thread: " + Thread.currentThread().getId());

        login("RohiniM", "Rohini_16", "Login Successful");
    }

    @Test
    public void InvalidUserName() {
        System.out.println("InvalidUserName running on Thread: " + Thread.currentThread().getId());

        login("Rohini", "Rohini_16", "Login Failed (Invalid Username)");
    }

    @Test
    public void InvalidPassword() {
        System.out.println("InvalidPassword running on Thread: " + Thread.currentThread().getId());

        login("RohiniM", "Rohini_1", "Login Failed (Invalid Password)");
    }

    public void login(String username, String password, String expectedMessage) {

        driver.findElement(By.id("login2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys(username);

        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            if (alert != null) {
                System.out.println(expectedMessage + " on Thread: " + Thread.currentThread().getId());
                alert.accept();
            }

        } catch (Exception e) {

            WebElement home = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Welcome')]")));

            if (home.getText().equals("Welcome RohiniM")) {
                System.out.println(expectedMessage + " on Thread: " + Thread.currentThread().getId());
            }
        }
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
        System.out.println("Browser closed on Thread: " + Thread.currentThread().getId());
    }
}
