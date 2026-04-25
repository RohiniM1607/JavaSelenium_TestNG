package testngPractice.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@Test
    public void TestYoutube() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");

        // assertEquals
        softassert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", 
                "YouTube URL mismatchh");

        // assertTrue
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='search_query']"));
        softassert.assertTrue(searchBox.isDisplayed(), "YouTube search box is not displayed");

        // Search
        searchBox.sendKeys("TestNG", Keys.ENTER);
        
        //softassert.fail("This test is intentionally failed");

        // assertTrue
        softassert.assertTrue(driver.getTitle().contains("TestNG"),
                "YouTube title does not contain TestNG");

        // assertFalse
        softassert.assertFalse(driver.getTitle().contains("Google"),
                "YouTube title should not contain Google");

        // assertNotNull
        softassert.assertNotNull(driver.getTitle(), "Title should not be null");

        // Example of assertNotEquals
        softassert.assertNotEquals(driver.getTitle(), "YouTube",
                "Title should change after search");

        driver.quit();
        
        softassert.assertAll();
    }
}
