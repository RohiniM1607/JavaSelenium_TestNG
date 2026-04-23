package testngPractice.TestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo2_HardAssertion {
	public WebDriver driver;

	@Test
    public void TestYoutube() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");

        // assertEquals
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", 
                "YouTube URL mismatch");

        // assertTrue
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='search_query']"));
        Assert.assertTrue(searchBox.isDisplayed(), "YouTube search box is not displayed");

        // Search
        searchBox.sendKeys("TestNG", Keys.ENTER);
        
        Assert.fail("This test is intentionally failed");

        // assertTrue
        Assert.assertTrue(driver.getTitle().contains("TestNG"),
                "YouTube title does not contain TestNG");

        // assertFalse
        Assert.assertFalse(driver.getTitle().contains("Google"),
                "YouTube title should not contain Google");

        // assertNotNull
        Assert.assertNotNull(driver.getTitle(), "Title should not be null");

        // Example of assertNotEquals
        Assert.assertNotEquals(driver.getTitle(), "YouTube",
                "Title should change after search");

        driver.quit();
    }
    
	
}
