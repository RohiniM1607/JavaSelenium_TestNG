package testng.Demo;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class DemoBlaze {
	public WebDriver driver;
  
  
  @Test 
  public void validation() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("RohiniM");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Rohini_16");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed");
			}
		}  // When no alert appears within the wait time, alert line throws a TimeOutException(Wait condition was not satisfied)
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome RohiniM']")));

			if (home.getText().equals("Welcome RohiniM")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
 
  @Test 
  public void InvalidUserName() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("Rohini");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Rohini_16");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed(Invalid UserName)");
			}
		}  // When no alert appears within the wait time, alert line throws a TimeOutException(Wait condition was not satisfied)
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome RohiniM']")));

			if (home.getText().equals("Welcome RohiniM")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
  @Test 
  public void InvalidPassword() {
	  driver.findElement(By.xpath("//a[@id='login2']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginusername']"))).sendKeys("RohiniM");
	  driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Rohini_1");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				alert.accept();
				System.out.println("Login Failed(Invalid Password)");
			}
		}  // When no alert appears within the wait time, alert line throws a TimeOutException(Wait condition was not satisfied)
		catch (Exception e) {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Welcome RohiniM']")));

			if (home.getText().equals("Welcome RohiniM")) {
				System.out.println("Login Successful");
			}
		}
	  
  }
  
  @BeforeTest
  public void beforeTest() {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--start-maximized");
      //options.addArguments("--headless");
      driver = new ChromeDriver(options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      
  }
  @BeforeMethod
 public void beforeMethod() {
	 driver.get("https://www.demoblaze.com/");
 }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
