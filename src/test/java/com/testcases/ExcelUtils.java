package com.testcases;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {

    private static XSSFSheet ExcelSheet;
    private static XSSFWorkbook ExcelWorkbook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //method to set the file path and to open the Excel file, pass excel Path and SheetName as arguments
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            //open excel file
            FileInputStream ExcelFile = new FileInputStream(Path);

            //access required data
            ExcelWorkbook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWorkbook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    //method to read  the test data from the excel cell, pass parameters as Row number and Column number
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //method to write in the Excel cell, pass parameters as Row number and Column number
    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception{
        try{
            Row = ExcelSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if(Cell == null){
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            }
            else{
                Cell.setCellValue(Result);
            }

            //constant variables test data path and test data file
            FileOutputStream fileOut = new FileOutputStream(Constant.pathTestData + Constant.fileTestData);
            ExcelWorkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e)
        {
            throw (e);
        }
    }
}