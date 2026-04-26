package testngPractice.TestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterAnnotation_OrangeHRM {
	WebDriver driver;
	WebDriverWait wait;
	
	@Parameters("browserName")
	@Test
	//public void GetBroser(@Optional("chrome") String browser)
	public void GetBrowser(String browser) {
		switch(browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.err.print("Invalid Browser name");
			break;
		}
		driver.manage().window().maximize();
	}
	
	@Parameters("websiteURL")
	@Test
	public void LaunchApplication(String url) {
		driver.get(url);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Application launched");
	}
	
	@Parameters({"username", "password"})
	@Test
	public void LoginWithCredentials(String username, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
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
