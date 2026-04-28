package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test(priority = 0)
	public void loginTest() {
		objLogin = new LoginPage(driver);
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.contains("Login"));
	}
}
