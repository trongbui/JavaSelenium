package com.itms.training.java.selenium.tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGTest2 extends BaseTestNGTest {

    @Parameters({"suite-param-1", "suite-param-2"})
    @Test(priority = 3, groups = {"test1", "smoke"})
    public void test1(String param1, String param2) {
        System.out.println("*** test1 ***");
        System.out.println("suite-param-1" + " - " + param1);
        System.out.println("suite-param-2" + " - " + param2);
    }

    @Parameters({"test-param-A"})
    @Test(priority = 1, groups = {"test2", "smoke"})
    public void test2(@Optional("Optional") String paramA) {
        System.out.println("*** test2 ***");
        System.out.println("test-param-A" + " - " + paramA);
    }

    @Parameters({"test-param-C"})
    @Test (priority = 2, groups = {"test3", "regression"})
    public void test3(@Optional("Optional") String paramC) {
        System.out.println("*** test3 ***");
        System.out.println("test-param-C" + " - " + paramC);
    }
}
