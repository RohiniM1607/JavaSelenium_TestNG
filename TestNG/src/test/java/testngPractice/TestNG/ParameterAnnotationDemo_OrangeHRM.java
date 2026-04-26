package testngPractice.TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ParameterAnnotationDemo_OrangeHRM {
	WebDriver driver;
	
	@Parameters({"browserName", "websiteURL"})
	
	@BeforeTest
	public void InitializeBrowser(String browserName, String url){
		switch(browserName.toLowerCase()) {
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
		driver.get(url);
	}
	
	@Test
	public void TestBrowser() {
		System.out.println("Browser Opened");
	}
	
	
	@Test
	public void TestApplication() {
		System.out.println("Application Opened");
	}
	
	@AfterTest
	public void ClosingBrowser() {
		driver.quit();
	}
	
}
