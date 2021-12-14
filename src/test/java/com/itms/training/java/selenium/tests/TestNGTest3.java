package com.itms.training.java.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGTest3 extends BaseTestNGTest {

    @Test
    public void test1() {
        System.out.println("*** test1 ***");
        Assert.assertEquals(1, 1);
        Assert.assertEquals(1, 2);
        Assert.assertTrue(false);
    }

    @Test
    public void test2() {
        System.out.println("*** test2 ***");
        Assert.assertEquals(1, 1);
        Assert.assertEquals(1, 2, "Exception: this test should be failed");
        Assert.assertTrue(true);
    }

    @Test
    public void test3() {
        System.out.println("*** test3 ***");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 1, "Exception: this test should not be failed");
        softAssert.assertEquals(1, 2, "Exception: this test should be failed");
        softAssert.assertEquals(1, 3, "Exception: this test should be failed too");
        softAssert.assertTrue(true);

        softAssert.assertAll();
    }
}
