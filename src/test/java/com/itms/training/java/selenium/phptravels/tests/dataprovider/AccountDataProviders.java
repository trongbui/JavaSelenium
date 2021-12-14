package com.itms.training.java.selenium.phptravels.tests.dataprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.Account;
import com.itms.training.java.utils.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AccountDataProviders {

    /**
     * Data provider reads valid accounts from json file to Account object
     * @return
     * @throws IOException
     */
    @DataProvider(name = "JSONValidAccounts")
    public static Object[][] jsonValidAccounts() throws IOException {
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

    /**
     * Data provider reads invalid accounts from json file to Account object
     * @return
     * @throws IOException
     */
    @DataProvider(name = "JSONInvalidAccounts")
    public static Object[][] jsonInvalidAccounts() throws IOException {
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

    /**
     * Data provider reads valid accounts from Excel file to Account object
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    @DataProvider(name = "ExcelValidAccounts")
    public static Object[][] excelValidAccounts() throws IOException, InvalidFormatException {
        JSONArray jsonArray = ExcelUtils.readExcel("src/test/resources/data/login_success/accounts.xlsx", "valid_accounts");
        ObjectMapper mapper = new ObjectMapper();

        List<Account> accountList = mapper.readValue(jsonArray.toString(), new TypeReference<List<Account>>() {});
        Object[][] validAccounts = new Object[accountList.size()][];
        for (int i = 0; i < accountList.size(); i++) {
            Object[] account = new Object[]{accountList.get(i)};
            validAccounts[i] = account;
        }
        return validAccounts;
    }

    /**
     * Data provider reads valid accounts from json file to json object
     * @return
     * @throws IOException
     */
    @DataProvider(name = "accounts")
    public Object[][] accounts() throws IOException {
        File fileValidAccounts = new File("src/test/resources/data/login_success/valid_accounts.json");
        JSONArray jsonArrayValidAccounts = new JSONArray(Files.readFile(fileValidAccounts));
        Object[][] validAccounts = new Object[jsonArrayValidAccounts.length()][];
        for (int i = 0; i < jsonArrayValidAccounts.length(); i++) {
            Object[] account = new Object[]{jsonArrayValidAccounts.getJSONObject(i)};
            validAccounts[i] = account;
        }
        return validAccounts;
    }
}
