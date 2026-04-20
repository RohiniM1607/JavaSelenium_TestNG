package TestNG_Assertion;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	
	@Test
	public void verifyTitle() {
		String expectedTitle = "Googles";
		String actualTitle = driver.getTitle();
		sa.assertEquals(actualTitle, expectedTitle, "Title is not same");
		sa.assertAll();
	}
	
	@BeforeTest
	public void setup() {
		
	}

}
