package com.itms.training.java.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTestNGTest {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private final int pageLoadTimeout = 60;
    private final int timeOut = 60;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("-- Before Suite --");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("---- Before Test ----");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("------ Before Class ------");
    }

    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(String browserName) {
        System.out.println("-------- Before Method --------");
        webDriver = WebDriverManager.getInstance(browserName).create();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        webDriver.manage().window().maximize();

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut));
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("-------- After Method --------");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("------ After Class ------");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("---- After Test ----");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("-- After Suite --");
    }

    @DataProvider(name = "DataProvider1")
    public Object[][] dataProvider1() {
        return new Object[][]{{"a"}, {"b"}, {"c"}};
    }
}
