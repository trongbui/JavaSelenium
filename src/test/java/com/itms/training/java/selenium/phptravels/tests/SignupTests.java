package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.selenium.phptravels.pages.components.HeaderTopBar;
import com.itms.training.java.selenium.phptravels.pages.SignupPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    @Parameters("browserName")
    @Test
    public void signUpTests(String browserName) throws InterruptedException {
        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
        headerTopBar.cookieGotIt();

        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup("ITMS", "Coaching", "123456789", "itms.coaching@gmail.com", "123456", "Customer");
    }
}
