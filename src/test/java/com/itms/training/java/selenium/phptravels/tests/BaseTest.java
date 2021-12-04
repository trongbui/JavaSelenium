package com.itms.training.java.selenium.phptravels.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    protected WebDriver webDriver;
    private final int pageLoadTimeout = 60;

    @Parameters("browserName")
    @BeforeTest
    public void beforeTest(String browserName) {
        webDriver = WebDriverManager.getInstance(browserName).create();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        webDriver.manage().window().maximize();

        webDriver.get("https://www.phptravels.net/login");

    }

    @AfterTest
    public void afterTest() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}