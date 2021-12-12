package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.dto.Account;
import com.itms.training.java.selenium.phptravels.pages.DashBoardPage;
import com.itms.training.java.selenium.phptravels.pages.LoginPage;
import com.itms.training.java.selenium.phptravels.pages.SignupPage;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderTopBar;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {

    @Test(dataProvider = "accounts")
    public void hotelBooking(Account account) {
        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup(account);

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(account.getEmail(), account.getPassword());

        DashBoardPage dashBoardPage = new DashBoardPage(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(DashBoardPage.title));
    }
}
