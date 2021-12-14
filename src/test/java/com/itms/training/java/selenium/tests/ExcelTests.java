package com.itms.training.java.selenium.tests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelTests {

    @Test
    public void readExcelTests() throws IOException, InvalidFormatException {
        File excelFile = new File("src/test/resources/data/login_success/accounts.xlsx");
        String sheetName = "valid_accounts";

        JSONArray validAccounts = new JSONArray();

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

        //Get desired sheet from the workbook
        XSSFSheet sheetValidAccount = workbook.getSheet(sheetName);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheetValidAccount.iterator();
        Row headers = rowIterator.next();

        while (rowIterator.hasNext()){
            Row dataRow = rowIterator.next();
            JSONObject account = new JSONObject();

            Iterator<Cell> cellIterator = dataRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                String header = headers.getCell(columnIndex).getStringCellValue();
                switch (cell.getCellType()) {
                    case STRING:
                        account.put(header, cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        account.put(header, cell.getNumericCellValue());
                        break;
                    default:
                        account.put(header, cell.getStringCellValue());
                }
            }
            validAccounts.put(account);
        }

        System.out.println(validAccounts);

    }
}
