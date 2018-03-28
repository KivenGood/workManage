package com.xawl.Service.imp;

import com.xawl.Dao.PracticeworkDao;
import com.xawl.Dao.UserDao;
import com.xawl.Pojo.Coe;
import com.xawl.Pojo.Practicework;
import com.xawl.Pojo.User;
import com.xawl.Service.PracticeworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

@Service
public class PracticeworkServiceImp implements PracticeworkService {
    @Resource
    PracticeworkDao practiceworkDao;

    @Override
    public List<Practicework> getPracticework(Practicework practicework) {
        return practiceworkDao.getPracticework(practicework);
    }

    @Override
    public void insertPracticework(Practicework practicework) {
        practiceworkDao.insertPracticework(practicework);
    }

    @Override
    public void updatePracticeworkById(Practicework practicework) {
        practiceworkDao.updatePracticeworkById(practicework);
    }

    @Override
    public void deletePracticeworkById(Integer id) {
        practiceworkDao.deletePracticeworkById(id);
    }

    @Override
    public String exportPracticework(HttpServletRequest request, Practicework practicework) {
        practicework.setPass(2);
        List<Practicework> practiceworkList = practiceworkDao.getPracticework(practicework);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + practicework.getTerm() + "学期实践工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("职称");
        rows.createCell(2).setCellValue("实践名称");
        rows.createCell(3).setCellValue("班级");
        rows.createCell(4).setCellValue("班级人数");
        rows.createCell(5).setCellValue("周数");
        rows.createCell(6).setCellValue("课时");
        rows.createCell(7).setCellValue("见习名称");
        rows.createCell(8).setCellValue("班级");
        rows.createCell(9).setCellValue("班级人数");
        rows.createCell(10).setCellValue("天数");
        rows.createCell(11).setCellValue("课时");
        rows.createCell(12).setCellValue("名称（课程设计、学年论文）");
        rows.createCell(13).setCellValue("班级");
        rows.createCell(14).setCellValue("人数");
        rows.createCell(15).setCellValue("周数");
        rows.createCell(16).setCellValue("课时");
        rows.createCell(17).setCellValue("合计课时");
        //  int uid;//用户id
        System.out.println("practiceworkList.size():" + practiceworkList.size());
        //Integer uid = null;//用户id，控制excl何时进行下一行
        int i = 0;//控制lessonList的行
        for (int row = 1; row <= practiceworkList.size(); row++) {
            System.out.println("User.name:" + practiceworkList.get(row - 1).getUser().getName());
            rows.createCell(0).setCellValue(practiceworkList.get(row - 1).getUser().getName());//当前用户姓名
            rows.createCell(1).setCellValue(practiceworkList.get(row - 1).getUser().getLevel());//当前用户职称
        }
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            //  practiceworkDao.updatePassByPassAndType(4,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path + fileName;
    }

    @Override
    public String exportThesisework(HttpServletRequest request) {
        Practicework practicework = new Practicework();
        practicework.setPass(2);
        practicework.setType(3);
        List<Practicework> practiceworkList = practiceworkDao.getPracticework(practicework);
        System.out.println("practiceworkList.size():" + practiceworkList.size());
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + practicework.getTerm() + "学期毕设工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("职称");
        rows.createCell(2).setCellValue("指导人数");
        rows.createCell(3).setCellValue("指导论文课时系数");
        rows.createCell(4).setCellValue("论文课时总数");
        rows.createCell(5).setCellValue("指导答辩人数");
        rows.createCell(6).setCellValue("答辩课时");
        rows.createCell(7).setCellValue("标准课时");
        //  int i = 0;//控制lessonList的行
        for (int row = 1; row <= practiceworkList.size(); row++) {
            rows = sheet.createRow(row);
            System.out.println("User.name:" + practiceworkList.get(row - 1).getUser().getName());
            rows.createCell(0).setCellValue(practiceworkList.get(row - 1).getUser().getName());//当前用户姓名
            rows.createCell(1).setCellValue(practiceworkList.get(row - 1).getUser().getLevel());//当前用户职称
            rows.createCell(2).setCellValue(practiceworkList.get(row - 1).getCid() + Integer.valueOf(practiceworkList.get(row - 1).getLname()));//指导答辩人数
            rows.createCell(3).setCellValue(practiceworkList.get(row - 1).getCid() + "*" + Coe.thesisGuide + "+" + practiceworkList.get(row - 1).getLname() + "*" + Coe.thesisGuideL);//指导论文课时系数,工科和理科带实验为15，理科为12
            rows.createCell(4).setCellValue(practiceworkList.get(row - 1).getCid() * Coe.thesisGuide + Integer.valueOf(practiceworkList.get(row - 1).getLname()) * Coe.thesisGuideL);//论文课时总数
            rows.createCell(5).setCellValue(practiceworkList.get(row - 1).getNum());//指导答辩人数
            rows.createCell(6).setCellValue(practiceworkList.get(row - 1).getNum() * Coe.thesisReply);//答辩课时
            rows.createCell(7).setCellValue(practiceworkList.get(row - 1).getClasshours());//标准课时
        }
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            //  practiceworkDao.updatePassByPassAndType(4,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path + fileName;
    }
}
