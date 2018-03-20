package com.xawl.Service.imp;

import com.xawl.Dao.LessonworkDao;
import com.xawl.Pojo.Lessonwork;
import com.xawl.Service.LessonworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
@Service
public class LessonworkServiceImp implements LessonworkService {

    @Resource
    LessonworkDao lessonworkDao;

    @Override
    public List<Lessonwork> getLessonwork(Lessonwork lessonwork) {
        return lessonworkDao.getLessonwork(lessonwork);
    }

    @Override
    public void insertLessonwork(Lessonwork lessonwork) {
        lessonworkDao.insertLessonwork(lessonwork);

    }

    @Override
    public void updateLessonworkById(Lessonwork lessonwork) {

        lessonworkDao.updateLessonworkById(lessonwork);
    }

    @Override
    public void deleteLessonworkById(Integer id) {
        lessonworkDao.deleteLessonworkById(id);

    }

    @Override
    public String exportTestwork(HttpServletRequest request, Lessonwork lessonwork) {
        lessonwork.setPass(2);
        List<Lessonwork> lessonworkList = lessonworkDao.getLessonwork(lessonwork);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + lessonwork.getTerm() + "学期课程工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("职称");
        rows.createCell(2).setCellValue("课程名字（普通）");
        rows.createCell(3).setCellValue("任课班级");
        rows.createCell(4).setCellValue("班级人数");
        rows.createCell(5).setCellValue("拆班/合班");
        rows.createCell(6).setCellValue("计划学时");
        rows.createCell(7).setCellValue("系数");
        rows.createCell(8).setCellValue("标准课时");
        rows.createCell(9).setCellValue("课程名字（实验）");
        rows.createCell(10).setCellValue("任课班级");
        rows.createCell(11).setCellValue("班级人数");
        rows.createCell(12).setCellValue("实验课时");
        rows.createCell(13).setCellValue("标准课时");
        rows.createCell(14).setCellValue("课时合计");
        //  int uid;//用户id
        System.out.println("testworkList.size():" + lessonworkList.size());
        for (int row = 1; row <= lessonworkList.size(); row++) {//控制行

        }
        return fileName;
    }
}