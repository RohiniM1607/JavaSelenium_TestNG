package testngPractice.TestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrangeHRM_DemoTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@Test
	public void LaunchApplication() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Application launched");
	}
	
	@Test
	public void LoginWithCredentials() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		System.out.println("Login credentials entered");
	}
	
	@Test
	public void NavigateToMyinfoTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='My Info']"))).click();
		System.out.println("Navigated to my info tab");
	}
	
	@Test
	public void VerifyMyinfo() {
	    System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']"))).isDisplayed());
	    System.out.println(driver.findElement(By.xpath("//h6[text()='Personal Details']")).isDisplayed());
	    System.out.println("My info verified");
	    driver.close();
	}
	
	@Test
	public void VerifyLogin() {
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='oxd-userdropdown-img']"))).isDisplayed());
		System.out.println("Login verified");
		
	}
}
