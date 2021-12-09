package com.itms.training.java.selenium.phptravels.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.Account;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderTopBar;
import com.itms.training.java.selenium.phptravels.pages.SignupPage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SignupTests extends BaseTest {

    @Test(dataProvider = "valid_accounts")
    public void signUpTests(JSONObject account) throws InterruptedException, JsonProcessingException {
        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
//        headerTopBar.cookieGotIt();

        SignupPage signupPage = headerTopBar.openSignupPage();
        // passing direct values
//        signupPage.signup(
//                "ITMS",
//                "Coaching",
//                "123456789",
//                "itms.coaching@gmail.com",
//                "123456",
//                "Customer");

        // passing value from JSONObject
//        signupPage.signup(
//                account.getString("firstName"),
//                account.getString("lastName"),
//                account.getString("phone"),
//                account.getString("email"),
//                account.getString("password"),
//                account.getString("accountType")
//        );

        // passing value from Account object
        ObjectMapper mapper = new ObjectMapper();
        Account acc = mapper.readValue(account.toString(), Account.class);
        signupPage.signup(acc);
    }

    @Test(dataProvider = "accounts")
    public void signUpObjectTests(Account account) throws InterruptedException, JsonProcessingException {
        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
//        headerTopBar.cookieGotIt();
        SignupPage signupPage = headerTopBar.openSignupPage();

        signupPage.signup(account);
    }

    @Test(dataProvider = "invalid_accounts")
    public void signUpFail(Account account) {
        String message = "Please fill out this field.";

        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
//        headerTopBar.cookieGotIt();
        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup(account);

        if (account.getFirstName().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbFirstName()), message);
        } else if (account.getLastName().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbLastName()), message);
        } else if (account.getPhone().equals("")) {
            Assert.assertEquals(signupPage.getRequiredMessage(signupPage.getTbPhone()), message);
        }
    }

    @DataProvider(name = "valid_accounts")
    public Object[][] validAccount() throws IOException {
        File fileValidAccounts = new File("src/test/resources/data/login_success/valid_accounts.json");
        JSONArray jsonArrayValidAccounts = new JSONArray(Files.readFile(fileValidAccounts));
        Object[][] validAccounts = new Object[jsonArrayValidAccounts.length()][];
        for (int i = 0; i < jsonArrayValidAccounts.length(); i++) {
            Object[] account = new Object[]{jsonArrayValidAccounts.getJSONObject(i)};
            validAccounts[i] = account;
        }
        return validAccounts;
    }

    // Move to BaseTest to reuse in LoginTests
//    @DataProvider(name = "accounts")
//    public Object[][] accounts() throws IOException {
//        File fileValidAccounts = new File("src/test/resources/data/login_success/valid_accounts.json");
//        ObjectMapper mapper = new ObjectMapper();
//        List<Account> accountList = mapper.readValue(fileValidAccounts, new TypeReference<List<Account>>() {});
//        Object[][] validAccounts = new Object[accountList.size()][];
//        for (int i = 0; i < accountList.size(); i++) {
//            Object[] account = new Object[]{accountList.get(i)};
//            validAccounts[i] = account;
//        }
//        return validAccounts;
//    }

    @DataProvider(name = "invalid_accounts")
    public Object[][] invalid_accounts() throws IOException {
        File fileValidAccounts = new File("src/test/resources/data/login_success/invalid_accounts.json");
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
