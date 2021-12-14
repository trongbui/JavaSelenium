package com.itms.training.java.selenium.tests;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "DataProvider1")
    public Object[][] dataProvider1() {
        return new Object[][]{{1}, {2}, {3}};
    }
}
