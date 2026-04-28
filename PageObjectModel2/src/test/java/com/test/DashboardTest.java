package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.DashboardPage;
import com.pages.LoginPage;

public class DashboardTest extends BaseTest{
	@Test
	public void dashboardTest() {
		objLogin = new LoginPage(driver);
		objLogin.login("Admin", "admin123");
		objDashboard = new DashboardPage(driver);
        Assert.assertTrue(objDashboard.getHomePageTitle().contains("Dashboard"));
	}
}
