package com.itms.training.java.selenium.tests;

import org.testng.annotations.Test;

public class TestNGTest1 extends BaseTestNGTest {

    @Test(description = "TestNGTest1.test1", dataProvider = "DataProvider1", groups = {"test1", "regression", "smoke"})
    public void test1(String str) {
        System.out.println("*** test1 ***" + " - " +str);
    }

    @Test(description = "TestNGTest1.test2", dataProvider = "DataProvider1", dataProviderClass = TestDataProvider.class, groups = {"test2", "smoke"})
    public void test2(int num) {
        System.out.println("*** test2 ***" + " - " + num);
    }

    @Test(description = "TestNGTest1.test2")
    public void test3() {
        System.out.println("*** test3 ***");
    }
}
