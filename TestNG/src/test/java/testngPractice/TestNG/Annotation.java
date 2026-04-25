package testngPractice.TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotation {
	@BeforeSuite //Used for global setup/teardown(Example: DB connection, reporting setup), Runs once for entire suite
    public void beforeSuite() {
        System.out.println("Before Suite - Setup DB connection");
    }

    @BeforeTest   //Used for test-level setup(Example: Open/close browser, environment setup), Runs once per <test> block
    public void beforeTest() {
        System.out.println("Before Test - Open browser");
    }

    @BeforeClass    //Runs once per class, Used for class-level initialization (e.g., creating objects, loading config)
    public void beforeClass() {
        System.out.println("Before Class - Initialize test data");
    }

    @BeforeMethod    //Runs before & after each test method, (Example: Login before every test, Logout after every test)
    public void beforeMethod() {
        System.out.println("Before Method - Login");
    }

    @Test(priority = 1)  //Actual test case execution
    public void test1() {
        System.out.println("Test 1 - Verify homepage");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Test 2 - Verify search functionality");
    }

    @AfterMethod 
    public void afterMethod() {
        System.out.println("After Method - Logout");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class - Clear test data");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test - Close browser");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite - Close DB connection");
    }
}
