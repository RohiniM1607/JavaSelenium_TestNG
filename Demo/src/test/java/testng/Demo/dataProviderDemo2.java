package testng.Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class dataProviderDemo2 {
	public WebDriver driver;
	public void setUp() {
		System.out.println("Start the test");
		driver=new ChromeDriver();
		driver.get("https://www.bing.com/");
		driver.manage().window().maximize();
		}
	
	@Test(dataProvider = "testData", dataProviderClass = DPDemo.class)
	public void search(String keyWord) {
		WebElement txBoxElement=driver.findElement(By.id("sb_form_q"));
		txBoxElement.sendKeys(keyWord);
		System.out.println("KeyWord entered is : "+keyWord);
		txBoxElement.sendKeys(Keys.ENTER);
		System.out.println("search result is displayed");
	}
	
	@AfterMethod
	public void tearDown() {
            driver.quit();
            System.out.println("End the test");
        }
}
