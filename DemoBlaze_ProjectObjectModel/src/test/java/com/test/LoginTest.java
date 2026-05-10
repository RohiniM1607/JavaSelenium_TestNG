package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.LoginPage;

public class LoginTest extends BaseTest{
	@Test(dataProvider = "excelData", dataProviderClass = TestData.class)
	public void loginTest(String type, String username, String password) {

		objLogin = new LoginPage(driver);

		objLogin.OpenLogin();
		objLogin.Login(username, password);

		String actualText = objLogin.verifyLogin();

		if (type.equalsIgnoreCase("valid")) {
			Assert.assertTrue(actualText.contains("Welcome"));
		} else {
			Assert.assertFalse(actualText.contains("Welcome"));
		}
	}
}
