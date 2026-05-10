package com.tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.utilities.Data_Provider;

public class LoginTest extends BaseTest {

    LoginPage objLogin;

    @BeforeMethod
    public void init() {
        objLogin = new LoginPage(getDriver());
    }

    @Test(dataProvider = "ValidexcelData", dataProviderClass = Data_Provider.class)
    public void validLogin(String username, String password) {

        objLogin.login(username, password);

        String actual = objLogin.getLogintext();
        Assert.assertTrue(actual.contains("Welcome"));
    }

    @Test(dataProvider = "InValidexcelData", dataProviderClass = Data_Provider.class)
    public void invalidLogin(String username, String password) {

        objLogin.login(username, password);

        Alert alert = getDriver().switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }
}