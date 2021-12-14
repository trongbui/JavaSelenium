package com.itms.training.java.selenium.phptravels.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itms.training.java.dto.Account;
import com.itms.training.java.selenium.phptravels.pages.SignupPage;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderTopBar;
import com.itms.training.java.selenium.phptravels.tests.dataprovider.AccountDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    /**
     * Sign up with Valid account reading from Excel data provider
     * @param account
     * @throws InterruptedException
     * @throws JsonProcessingException
     */
    @Test(dataProvider = "ExcelValidAccounts", dataProviderClass = AccountDataProviders.class)
    public void signUpObjectTests(Account account) throws InterruptedException, JsonProcessingException {
        webDriver.get("https://www.phptravels.net/login");

        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
        SignupPage signupPage = headerTopBar.openSignupPage();

        signupPage.signup(account);
    }

    /**
     * Sign up with Invalid account and verify required messages
     * @param account
     */
    @Test(dataProvider = "JSONInvalidAccounts", dataProviderClass = AccountDataProviders.class)
    public void signUpFail(Account account) {
        webDriver.get("https://www.phptravels.net/login");
        String message = "Please fill out this field.";

        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup(account);

        if (account.getFirstName().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbFirstName()), message);
        } else if (account.getLastName().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbLastName()), message);
        } else if (account.getPhone().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbPhone()), message);
        }
    }
}
