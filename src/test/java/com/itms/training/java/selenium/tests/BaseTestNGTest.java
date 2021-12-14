package com.itms.training.java.selenium.tests;

import org.testng.annotations.*;

public class BaseTestNGTest {

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

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("-------- Before Method --------");
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
