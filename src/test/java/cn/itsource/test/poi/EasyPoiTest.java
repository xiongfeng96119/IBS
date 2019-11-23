package cn.itsource.test.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyPoiTest {

    @Test
    public void testExport() throws Exception{
        /**
         * 导出参数
         *  title       导出的excel文件第一行显示一个标题
         *  sheetName   工作表名称
         */
        ExportParams exportParams = new ExportParams("员工数据", "员工数据列表");
        //这里创建一个List集合，手动添加几个PoiEmployee对象，今后要换成从数据库中直接查询而来
        List<PoiEmployee> list = new ArrayList<>();
        PoiEmployee employee01 = new PoiEmployee("张三丰","123@qq.com", 22,1);
        PoiEmployee employee02 = new PoiEmployee("张四丰","123@qq.com", 23,2);
        PoiEmployee employee03 = new PoiEmployee("张五丰","123@qq.com", 26,2);
        PoiEmployee employee04 = new PoiEmployee("张七丰","123@qq.com", 20,1);

        employee01.setDept(new PoiDept(1L,"IT部"));
        employee02.setDept(new PoiDept(2L,"财务部"));
        employee03.setDept(new PoiDept(3L,"市场部"));
        employee04.setDept(new PoiDept(1L,"IT部"));


        list.add(employee01);
        list.add(employee02);
        list.add(employee03);
        list.add(employee04);


        //调用easypoi的工具类的方法直接导出，得到一个工作簿
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, PoiEmployee.class, list);
        //从内存中写出来
        FileOutputStream out = new FileOutputStream("员工数据列表.xlsx");
        workbook.write(out);
        out.close();
    }

    /**
     * 导出部门数据
     * @throws Exception
     */
    @Test
    public void testExportDepts() throws Exception{
        /**
         * 导出参数
         *  title       导出的excel文件第一行显示一个标题
         *  sheetName   工作表名称
         */
        ExportParams exportParams = new ExportParams("部门数据", "部门数据列表");
        //这里创建一个List集合，手动添加几个PoiEmployee对象，今后要换成从数据库中直接查询而来
        List<PoiDept> list = new ArrayList<>();
        list.add(new PoiDept(1L,"IT部"));
        list.add(new PoiDept(2L,"财务部"));
        list.add(new PoiDept(3L,"市场部"));


        //调用easypoi的工具类的方法直接导出，得到一个工作簿
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, PoiDept.class, list);
        //从内存中写出来
        FileOutputStream out = new FileOutputStream("部门数据列表.xlsx");
        workbook.write(out);
        out.close();
    }

    /**
     * 使用easypoi导入数据
     * @throws Exception
     */
    @Test
    public void testImport() throws Exception{
        /**
         * 导入参数
         *  titleRows标题占几行
         *  headRows表头占几行
         */
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx")获取JavaWeb项目的webapp目录下的路径
        List<PoiEmployee> list = ExcelImportUtil.importExcel(new File("员工数据列表.xlsx"), PoiEmployee.class, params);
        list.forEach(emp-> System.out.println(emp));
    }

}
