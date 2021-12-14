package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.dto.Account;
import com.itms.training.java.selenium.phptravels.pages.LoginPage;
import com.itms.training.java.selenium.phptravels.tests.dataprovider.AccountDataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    /**
     * Login Test with hard-code test data
     */
    @Test
    public void loginTest() {
        String email = "itms.coaching@gmail.com";
        String password = "123456";
        webDriver.get("https://www.phptravels.net/login");

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);
    }

    /**
     * Login test using data provider in different class
     * @param account
     */
    @Test(dataProvider = "JSONValidAccounts", dataProviderClass = AccountDataProviders.class)
    public void loginSuccessTest(Account account) {
        webDriver.get("https://www.phptravels.net/login");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(account.getEmail(), account.getPassword());
    }

    /**
     * Login test fail with hard-code test data
     */
    @Test
    public void loginFailTest() {
        String email = "xxx.itms.coaching@gmail.com";
        String password = "123456";
        webDriver.get("https://www.phptravels.net/login");

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getErrorMessage(), "Wrong credentials. try again!");
    }

    /**
     * Login test fail using data provider in the same class
     * @param email
     * @param password
     */
    @Test (dataProvider = "InvalidAccounts")
    public void loginFailTests(String email, String password) {
        webDriver.get("https://www.phptravels.net/login");

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getErrorMessage(), "Wrong credentials. try again!");
    }

    /**
     * Login test fail using data provider in different class
     * @param account
     */
    @Test (dataProvider = "JSONInvalidAccounts", dataProviderClass = AccountDataProviders.class)
    public void loginFailTest(Account account) {
        webDriver.get("https://www.phptravels.net/login");

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(account.getEmail(), account.getPassword());

        Assert.assertEquals(loginPage.getErrorMessage(), "Wrong credentials. try again!");
    }

    /**
     * Data provider with invalid accounts information
     * @return
     */
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
