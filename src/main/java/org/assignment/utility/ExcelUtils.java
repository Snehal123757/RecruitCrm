package org.assignment.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    public static String readExcelData(String cellValueToRead) throws IOException {
        String projectDir = System.getProperty("user.dir");
        String dataFromExcel = null;

        File excelFile = new File(projectDir + "/src/main/resources/gmail.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();


        while (rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell.toString() + ";");
                System.out.println(cell.getCellType());
                if(cell.toString().equals(cellValueToRead)){
                    dataFromExcel = cell.toString();
                    break;
                }
            }
        }

        workbook.close();
        fis.close();
        return dataFromExcel;
    }

    public static void main(String[] args) throws IOException {
        String data = readExcelData("MAIL WITH ATTACHMENT");
        System.out.println(data);
    }
}

