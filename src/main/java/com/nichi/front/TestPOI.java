package com.nichi.front;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI {
    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            System.out.println("Apache POI is working correctly!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
