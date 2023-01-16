package framework.data;

import framework.config.Config;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataReader {
    private static ExcelDataReader reader;                   //creating static method to read this
    private String excelLocation = Config.getProperty("data.location");     //give excel location
    private Workbook workbook;   //workbook is coming from apache poi. we need to read excel so we have to define instance of it


    private ExcelDataReader() {     //constructor
        try {
            workbook = new XSSFWorkbook(excelLocation);  //to read excel
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object[]> getDataFromSheet(String sheetName, boolean skipHeader) {  //matching this with data provider syntax
                                                                    //skipHeader - because don't want to read header of excel
        List<Object[]> data = new ArrayList<>();                    //Why list? list will hold all the rows of excel and obj array will hold all the cells

        if (reader == null) {
            reader = new ExcelDataReader();                         //if reader null then create a workbook
        }
        if (sheetName == null) {                                    //Error handling
            throw new RuntimeException("Sheet Name can't be null");
        }
        Sheet sheet = reader.workbook.getSheet(sheetName);          //get sheet from the workbook
        if (sheet == null) {
            System.out.println("Couldn't get sheet with name - " + sheetName);
            System.out.println("Reading first Sheet");
            sheet = reader.workbook.getSheetAt(0);               //reading the first sheet
        }
        if (sheet != null) {
            Iterator<Row> rowIterator = sheet.rowIterator();         //read the row from sheet. Iterator will allow to loop through all the rows
            if (rowIterator.hasNext() && skipHeader) {               //if row iterator has any value and skipping the header
                rowIterator.next();                                  //we can skip the header. discard the first row
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();                         //read the data. next() returns the row
                Iterator<Cell> cellIterator = row.cellIterator();     //get all the cells
                List<Object> cellData = new ArrayList<>();            //While iterating through cells,collect the values of cell and store it

                while (cellIterator.hasNext()) {                      //Iterate through cell
                    Cell cell = cellIterator.next();                  //next() will give the cell
                    if (cell.getCellType() == CellType.STRING) {      //If type = string then read string value
                        String value = cell.getStringCellValue();
                        cellData.add(value);                          //add this value to my list
                    }
                    if (cell.getCellType() == CellType.NUMERIC) {
                        Double value = cell.getNumericCellValue();
                        cellData.add(value);
                    }
                    if (cell.getCellType() == CellType.BOOLEAN) {
                        Boolean value = cell.getBooleanCellValue();
                        cellData.add(value);
                    }
                }
                data.add(cellData.toArray());                         //take the data and add this to obj array
            }
        }
        return data;                                                  //return my data
    }
}


