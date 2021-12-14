package com.itms.training.java.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    /**
     * Read an Excel WorkSheet into JSONArray
     * @param filePath
     * @param sheetName
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static JSONArray readExcel(String filePath, String sheetName) throws IOException, InvalidFormatException {
        File excelFile = new File(filePath);

        // Create output JSONArray
        JSONArray allData = new JSONArray();

        // Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

        // Get desired sheet from the workbook
        XSSFSheet sheetValidAccount = workbook.getSheet(sheetName);

        // Iterate through each rows one by one
        Iterator<Row> rowIterator = sheetValidAccount.iterator();

        // Get header row
        Row headers = rowIterator.next();

        // Iterate each data row
        while (rowIterator.hasNext()){
            Row dataRow = rowIterator.next();
            JSONObject data = new JSONObject();

            // Iterate each cell in the row
            Iterator<Cell> cellIterator = dataRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                // Get header of the column
                String header = headers.getCell(columnIndex).getStringCellValue();

                switch (cell.getCellType()) {
                    case STRING:
                        data.put(header, cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        data.put(header, cell.getNumericCellValue());
                        break;
                    default:
                        data.put(header, cell.getStringCellValue());
                }
            }
            allData.put(data);
        }

        return allData;
    }
}
