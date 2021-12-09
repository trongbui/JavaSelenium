package com.itms.training.java.selenium.phptravels.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.Account;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private final int pageLoadTimeout = 60;
    private final int timeOut = 60;

    @Parameters("browserName")
    @BeforeTest
    public void beforeTest(String browserName) {
        webDriver = WebDriverManager.getInstance(browserName).create();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        webDriver.manage().window().maximize();

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut));
        webDriver.get("https://www.phptravels.net/login");

    }

    @AfterTest
    public void afterTest() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @DataProvider(name = "accounts")
    public Object[][] accounts() throws IOException {
        File fileValidAccounts = new File("src/test/resources/data/login_success/valid_accounts.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList = mapper.readValue(fileValidAccounts, new TypeReference<List<Account>>() {});
        Object[][] validAccounts = new Object[accountList.size()][];
        for (int i = 0; i < accountList.size(); i++) {
            Object[] account = new Object[]{accountList.get(i)};
            validAccounts[i] = account;
        }
        return validAccounts;
    }
}
