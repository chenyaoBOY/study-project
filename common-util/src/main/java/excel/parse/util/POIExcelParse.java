package excel.parse.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenyao
 * @date 2020/7/13 17:34
 * @description
 */
public class POIExcelParse {

    public static void main(String[] args) throws FileNotFoundException {


        InputStream inputStream = new FileInputStream("E:\\Java\\file\\PayU.xlsx");
        Workbook book = getWorkBook(inputStream, "PayU.xlsx");
        Sheet sheet = book.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(2);
//        double numericCellValue = cell.getNumericCellValue();
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);

    }

    public static Workbook getWorkBook(InputStream is, String filePath) {
        //xls-2003, xlsx-2007
        try {
            if (filePath.toLowerCase().endsWith("xlsx")) {
                return new XSSFWorkbook(is);
            } else if (filePath.toLowerCase().endsWith("xls")) {
                return new HSSFWorkbook(is);
            } else {
                throw new RuntimeException("excel解析异常");
            }
        } catch (IOException e) {
            throw new RuntimeException("excel解析异常", e);
        } finally {
//            IOUtils.closeQuietly(is);//需要common-io包
        }
    }
}
