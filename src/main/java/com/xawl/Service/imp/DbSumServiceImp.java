package com.xawl.Service.imp;

import com.xawl.Dao.DbSumDao;
import com.xawl.Pojo.DbSum;
import com.xawl.Pojo.Lessonwork;
import com.xawl.Pojo.Practicework;
import com.xawl.Pojo.Testwork;
import com.xawl.Service.DbSumService;
import com.xawl.Service.LessonworkService;
import com.xawl.Service.PracticeworkService;
import com.xawl.Service.TestworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

@Service
public class DbSumServiceImp implements DbSumService {
    @Resource
    DbSumDao dbSumDao;

    @Resource
    LessonworkService lessonworkService;
    @Resource
    PracticeworkService practiceworkService;
    @Resource
    TestworkService testworkService;

    @Transactional
    @Override
    public String exportDbSum(HttpServletRequest request) {
        DbSum dbSum = new DbSum();
        dbSum.setPass(1);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = "信工" + a.get(Calendar.YEAR) + "年" + "工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        Lessonwork lessonwork = new Lessonwork();
        lessonwork.setPass(2);
        lessonwork.setTerm(1);
        lessonworkService.makeTestworkExcl(workbook, lessonwork);
        lessonwork.setTerm(2);
        lessonworkService.makeTestworkExcl(workbook, lessonwork);

        Practicework practicework = new Practicework();
        practicework.setPass(2);
        practicework.setTerm(1);
        practiceworkService.makePracticeworkExcl(workbook, practicework);
        practicework.setTerm(2);
        practiceworkService.makePracticeworkExcl(workbook, practicework);
        practiceworkService.makeThesiseworkExcl(workbook);

        Testwork testwork = new Testwork();
        testwork.setPass(2);
        testwork.setTerm(1);
        testworkService.makeTestworkExcl(workbook, testwork);
        testwork.setTerm(2);
        testworkService.makeTestworkExcl(workbook, testwork);
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
        rows.createCell(10).setCellValue("合计");
        rows.createCell(11).setCellValue("签字");
        rows.createCell(11).setCellValue("备注");
        List<DbSum> dbSumList = dbSumDao.getDbSum(dbSum);
        System.out.println("dbSumList.size():" + dbSumList.size());
        int uid = 0;//控制表格的换行
        int i = 0;//控制dbSumList的行
        for (int row = 1; row <= dbSumList.size(); row++) {
            System.out.println("i:" + i);
            System.out.println("uid:" + dbSumList.get(i).getUid());
            System.out.println();
            Double pclassSum = 0.0;//总课时
            uid = dbSumList.get(i).getUid();
            rows = sheet.createRow(row);
            rows.createCell(0).setCellValue(dbSumList.get(i).getUser().getTechno());//当前用户职工号
            rows.createCell(1).setCellValue(dbSumList.get(i).getUser().getName());//当前用户姓名
            rows.createCell(2).setCellValue(dbSumList.get(i).getUser().getLevel());//当前用户职称
            while (uid == dbSumList.get(i).getUid()) {
                System.out.println("dbSumList.get(i):" + i);
                System.out.println("dbSumList.get(i).getType()：" + dbSumList.get(i).getType());
                rows.createCell(2 + dbSumList.get(i).getType()).setCellValue(dbSumList.get(i).getPclass());
                pclassSum += dbSumList.get(i).getPclass();
                rows.createCell(10).setCellValue(pclassSum);
                i++;
                if (i >= dbSumList.size()) {
                    break;
                }
            }
            if (i >= dbSumList.size()) {
                break;
            }
        }
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            xlsStream.close();
            //dbSumDao.updateDbSumByPass(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "files/" + fileName;
    }


}
