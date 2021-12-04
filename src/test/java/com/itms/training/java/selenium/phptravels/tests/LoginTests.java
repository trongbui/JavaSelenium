package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.selenium.phptravels.pages.LoginPage;
import org.apache.commons.logging.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Test
    public void loginSuccessTest() {
        String email = "itms.coaching@gmail.com";
        String password = "123456";

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

    }

    @Test
    public void loginFailTest() {
        String email = "xxx.itms.coaching@gmail.com";
        String password = "123456";

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getErrorMessage(), "Wrong credentials. try again!");
    }

    @Test (dataProvider = "InvalidAccounts")
    public void loginFailsTest(String email, String password) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getErrorMessage(), "Wrong credentials. try again!");
    }

    @DataProvider (name = "InvalidAccounts")
    public Object[][] invalidAccount() {
        return new Object[][]
                {
                        {"xxx.itms.coaching@gmail.com", "123456"},
                        {"", "123456"},
                        {"itms.coaching@gmail.com",""},
                        {"", ""}
                };
    }

}