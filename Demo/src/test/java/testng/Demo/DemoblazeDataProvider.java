package testng.Demo;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoblazeDataProvider {

    WebDriver driver;
    WebDriverWait wait;

    @DataProvider(name = "loginData")
    public Object[][] dataProviderFunc() {
        return new Object[][] {
            {"RohiniM", "Rohini_16", "valid"},
            {"rohiniM", "Rohini_16", "invalidUserName"},
            {"RohiniM", "Rohini", "invalidPassword"}
        };
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Start the test");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoblaze.com/");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String testType) {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys(username);

        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        if (testType.equalsIgnoreCase("valid")) {

            String actualUserText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")))
                    .getText();

            String expectedUserText = "Welcome RohiniM";

            System.out.println("Login Success Text: " + actualUserText);

            Assert.assertEquals(actualUserText, expectedUserText,
                    "Valid login failed!");

        } 
        else if (testType.equalsIgnoreCase("invalidUserName")) {

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlert = alert.getText();
            String expectedAlert = "User does not exist.";
            System.out.println("Alert Message: " + actualAlert);
            Assert.assertEquals(actualAlert, expectedAlert, "Incorrect alert message for invalid username!");
            alert.accept();

        } 
        else if (testType.equalsIgnoreCase("invalidPassword")) {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualAlert = alert.getText();
            String expectedAlert = "Wrong password.";
            System.out.println("Alert Message: " + actualAlert);
            Assert.assertEquals(actualAlert, expectedAlert, "Incorrect alert message for invalid password!");

            alert.accept();
        }

        System.out.println("Test executed for: " + testType);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("End the test");
    }
}