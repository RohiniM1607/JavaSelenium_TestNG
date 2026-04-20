package testng.Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestNGParameternixationDemo {
	public WebDriver driver;
	By UserName=By.name("sowndariya");
	By password=By.name("Sow@911!");
	
	By loginBtn=By.xpath("//button[text()='Log in']");
	By loginTitle=By.xpath("");
	
	 @BeforeMethod
	 @Parameters("browser")
	 
	 public void parameterisedTest(String browser) {
		 if(browser.equalsIgnoreCase("FireFox")){
			 FirefoxOptions options=new FirefoxOptions();
			 
		 }
		 else if(browser.equalsIgnoreCase("chrome")) {
			 ChromeOptions options=new ChromeOptions();
			 options.addArguments("--start-maximize");
			 driver = new ChromeDriver(options);
			 System.out.println("Browser Started: "+browser);
		 }
		 
		
	 }
	 
	 
	
}
