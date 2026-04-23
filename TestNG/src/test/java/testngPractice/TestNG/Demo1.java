/*
 * Creating and run the first TestNG program with more than one @Test
 */

package testngPractice.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Demo1 {
	public WebDriver driver;
	
	@Test
	public void TestGoogle() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("TestNG", Keys.ENTER);
		System.out.println(driver.getTitle());
		driver.close();
	}
	
	@Test
	public void TestYoutube() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		driver.findElement(By.xpath("//input[@name='search_query']")).sendKeys("TestNG", Keys.ENTER);
		System.out.println(driver.getTitle());
		driver.close();
	}
	
	
}
