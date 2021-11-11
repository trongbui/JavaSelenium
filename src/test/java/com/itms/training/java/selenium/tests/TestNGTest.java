package com.itms.training.java.selenium.tests;

import org.testng.annotations.Test;

public class TestNGTest {

    @Test
    public void testAddInt() {
        int a = 10;
        int b = 20;
        System.out.println(a + b);
    }

    @Test
    public void testAddString() {
        String a = "Hello";
        String b = "World";
        System.out.println(a + b);
    }
}
