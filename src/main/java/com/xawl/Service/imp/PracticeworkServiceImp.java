package com.xawl.Service.imp;

import com.xawl.Dao.PracticeworkDao;
import com.xawl.Pojo.Practicework;
import com.xawl.Service.PracticeworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        List<Practicework> practiceworkList= practiceworkDao.getPracticework(practicework);
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
        return null;
    }

    @Override
    public String exportThesisework(HttpServletRequest request) {
        Practicework practicework=new Practicework();
        practicework.setPass(2);
        practicework.setType(3);
        return null;
    }
}
