package com.xawl.Service.imp;

import com.xawl.Dao.DbSumDao;
import com.xawl.Pojo.DbSum;
import com.xawl.Service.DbSumService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DbSumServiceImp implements DbSumService {
    @Resource
    DbSumDao dbSumDao;

    @Transactional
    @Override
    public String exportDbSum(HttpServletRequest request, DbSum dbSum) {
        dbSum.setPass(1);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = "信工" + a.get(Calendar.YEAR) + "年" + "工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("汇总");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("职工号");
        rows.createCell(1).setCellValue("姓名");
        rows.createCell(2).setCellValue("职称");
        rows.createCell(3).setCellValue("课程1");
        rows.createCell(4).setCellValue("课程2");
        rows.createCell(5).setCellValue("实践1");
        rows.createCell(6).setCellValue("实践2");
        rows.createCell(7).setCellValue("毕业设计");
        rows.createCell(8).setCellValue("考务1");
        rows.createCell(9).setCellValue("考务2");
        rows.createCell(10).setCellValue("毕设");
        rows.createCell(11).setCellValue("合计");
        rows.createCell(12).setCellValue("签字");
        rows.createCell(13).setCellValue("备注");
        List<DbSum> dbSumList = dbSumDao.getDbSum(dbSum);
        System.out.println("dbSumList.size():" + dbSumList.size());
        int uid=0;//控制表格的换行
        int i = 0;//控制dbSumList的行
        for (int row = 1; row <= dbSumList.size(); row++) {
            Double pclassSum = 0.0;//总课时
            uid= dbSumList.get(i).getUid();
            rows = sheet.createRow(row);

            System.out.println("lessonworkList.get(i):" + i);
        }
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            xlsStream.close();
            //lessonworkDao.updateLessonworkByPass(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "files/" + fileName;
    }
}
