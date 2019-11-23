package cn.itsource.test.poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PoiTest {

    /**
     * 使用POI输出99乘法表
     * @throws Exception
     */
    @Test
    public void testWrite() throws Exception{
        //创建一个工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个工作表
        XSSFSheet sheet = wb.createSheet("99乘法表");
        for (int i = 1; i <= 9; i++) {
            //创建一行
            XSSFRow row = sheet.createRow(i-1);
            for (int j = 1; j <= i; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j-1);
                //给单元格填值
                cell.setCellValue(j + "*" + i + "=" + (i*j));
            }
        }
        //从内存中写出来
        FileOutputStream out = new FileOutputStream("99.xlsx");
        wb.write(out);
        out.close();
    }

    /**
     * 使用POI读取excel文件的数据
     * @throws Exception
     */
    @Test
    public void testRead() throws Exception{
        InputStream in = new FileInputStream("emp.xlsx");
        //通过输入流创建工作簿，直接读取excel文件的数据
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //先获取工作表
        XSSFSheet sheet = wb.getSheetAt(0);
        //再获取行数
        int rowNum = sheet.getLastRowNum();
        for (int i = 2; i <= rowNum; i++) {
            //获取当前行
            XSSFRow row = sheet.getRow(i);
            //获取列数
            short cellNum = row.getLastCellNum();

            for (int j = 0; j < cellNum; j++) {
                //获取单元格
                String value = row.getCell(j).getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.println();
        }
        in.close();
    }


}
