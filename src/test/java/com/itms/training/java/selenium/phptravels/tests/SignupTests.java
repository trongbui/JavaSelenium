package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.selenium.phptravels.pages.HeaderTopBar;
import com.itms.training.java.selenium.phptravels.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Timespan;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SignupTests extends BaseTest {

    @Parameters("browserName")
    @Test
    public void signUpTests(String browserName) throws InterruptedException {
        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);

        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup("ITMS", "Coaching", "123456789", "itms.coaching@gmail.com", "123456", "Supplier");
    }
}
