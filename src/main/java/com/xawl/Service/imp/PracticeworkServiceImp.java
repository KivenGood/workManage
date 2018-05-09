package com.xawl.Service.imp;

import com.xawl.Dao.DbSumDao;
import com.xawl.Dao.DclassDao;
import com.xawl.Dao.PracticeworkDao;
import com.xawl.Dao.UserDao;
import com.xawl.Pojo.*;
import com.xawl.Service.PracticeworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class PracticeworkServiceImp implements PracticeworkService {
    @Resource
    PracticeworkDao practiceworkDao;
    @Resource
    DclassDao dclassDao;
    @Resource
    DbSumDao dbSumDao;
    @Resource
    UserDao userDao;

    @Override
    public List<Practicework> getPracticework(Practicework practicework) {
//        if(practicework.getPageNum()!=null&&practicework.getPageSize()!=null)
        //          PageHelper.startPage(practicework.getPageNum(),practicework.getPageSize());
        List<Practicework> practiceworkList = practiceworkDao.getPracticework(practicework);
        for (int i = 0; i < practiceworkList.size(); i++) {
            if (practiceworkList.get(i).getType() != 3) {
                Dclass dclass = new Dclass();
                dclass.setId(practiceworkList.get(i).getCid());
                System.out.println("Dclass.id:" + dclass.getId());
                dclass = dclassDao.getDclass(dclass).get(0);
                practiceworkList.get(i).setCname(dclass.getSeries() + dclass.getCname());//班级姓名
                practiceworkList.get(i).setCnum(dclass.getPnum());//班级人数
            }
        }
        return practiceworkList;
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
    @Transactional//这里加事物注解的原因是，同一个类中，无事物注解的方法中调用有事物注解的方法，事物不执行
    public String exportPracticework(HttpServletRequest request, Practicework practicework) {
        practicework.setPass(0);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + practicework.getTerm() + "学期实践工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook = makePracticeworkExcl(workbook, practicework);

        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            xlsStream.close();
            //  practiceworkDao.updatePassByPassAndType(4,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "files/" + fileName;
    }

    @Override
    @Transactional//这里加事物注解的原因是，同一个类中，无事物注解的方法中调用有事物注解的方法，事物不执行
    public String exportThesisework(HttpServletRequest request) {
            Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + 2 + "学期毕设工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook=makeThesiseworkExcl(workbook);
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            xlsStream.close();
            //  practiceworkDao.updatePassByPassAndType(4,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "files/" + fileName;
    }

    @Transactional
    public HSSFWorkbook makePracticeworkExcl(HSSFWorkbook workbook, Practicework practicework) {//excl的具体创建，分开是因为方便做总表时的创建；
        List<Practicework> practiceworkList = getPracticework(practicework);
        HSSFSheet sheet = workbook.createSheet("实践" + practicework.getTerm());
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("职称");
        rows.createCell(2).setCellValue("实践名称");
        rows.createCell(3).setCellValue("班级");
        rows.createCell(4).setCellValue("人数");
        rows.createCell(5).setCellValue("周数");
        rows.createCell(6).setCellValue("课时");
        rows.createCell(7).setCellValue("见习名称");
        rows.createCell(8).setCellValue("班级");
        rows.createCell(9).setCellValue("人数");
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
        int uid = 0;
        for (int row = 1; row <= practiceworkList.size(); row++) {
            rows = sheet.createRow(row);
            Double pclassSum = 0.0;//总课时

            DbSum dbSum = new DbSum();//给总表插入数据
            dbSum.setUid(practiceworkList.get(i).getUid());
            dbSum.setPass(0);
            dbSum.setStartedDate(new Timestamp(new Date().getTime()));
            dbSum.setType(2 + practicework.getTerm());

            uid = practiceworkList.get(i).getUid();
            if (practiceworkList.get(i).getType() == 3) {
                i++;
                if (i >= practiceworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    break;
                }
            }
            System.out.println("User.name:" + practiceworkList.get(i).getUser().getName());
            rows.createCell(0).setCellValue(practiceworkList.get(i).getUser().getName());//当前用户姓名
            rows.createCell(1).setCellValue(practiceworkList.get(i).getUser().getLevel());//当前用户职称
            if (practiceworkList.get(i).getType() == 1) {
                rows.createCell(2).setCellValue(practiceworkList.get(i).getLname());//实践名称
                rows.createCell(3).setCellValue(practiceworkList.get(i).getCname()
                        + practiceworkList.get(i).getCnum());//班级姓名
                rows.createCell(4).setCellValue(practiceworkList.get(i).getSnum());//实际指导人数
                rows.createCell(5).setCellValue(practiceworkList.get(i).getNum());
                rows.createCell(6).setCellValue(practiceworkList.get(i).getClasshours());//课时
                pclassSum += practiceworkList.get(i).getClasshours();
                i++;
                if (i >= practiceworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    break;
                }
                if (uid != practiceworkList.get(i).getUid()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    continue;
                }
            }
            if (practiceworkList.get(i).getType() == 2) {
                rows.createCell(7).setCellValue(practiceworkList.get(i).getLname());
                rows.createCell(8).setCellValue(practiceworkList.get(i).getCname()
                        + practiceworkList.get(i).getCnum());//班级姓名
                rows.createCell(9).setCellValue(practiceworkList.get(i).getSnum());//人数
                rows.createCell(10).setCellValue(practiceworkList.get(i).getNum());
                rows.createCell(11).setCellValue(practiceworkList.get(i).getClasshours());
                pclassSum += practiceworkList.get(i).getClasshours();
                i++;
                if (i >= practiceworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    break;
                }
                if (uid != practiceworkList.get(i).getUid()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    continue;
                }
            }
            if (practiceworkList.get(i).getType() == 4) {
                rows.createCell(12).setCellValue(practiceworkList.get(i).getLname());
                rows.createCell(13).setCellValue(practiceworkList.get(i).getCname()
                        + practiceworkList.get(i).getCnum());
                rows.createCell(14).setCellValue(practiceworkList.get(i).getSnum());
                rows.createCell(15).setCellValue(practiceworkList.get(i).getNum());
                rows.createCell(16).setCellValue(practiceworkList.get(i).getClasshours());
                pclassSum += practiceworkList.get(i).getClasshours();
                i++;
                if (i >= practiceworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    break;
                }
                if (uid != practiceworkList.get(i).getUid()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(17).setCellValue(pclassSum);
                    continue;
                }
            }
            dbSum.setPclass(pclassSum);
            dbSumDao.insertDbSum(dbSum);
            System.out.println("pclassSum:" + pclassSum);
            rows.createCell(17).setCellValue(pclassSum);
        }
        //如果最后没有把表分开制表，只是总表制表，则可以和别的类写成一样，直接改；
//        practiceworkDao.updatePassByPassAndType(1,1);
  //      practiceworkDao.updatePassByPassAndType(1,2);
    //    practiceworkDao.updatePassByPassAndType(1,4);
        return workbook;
    }

    @Transactional
    public HSSFWorkbook makeThesiseworkExcl(HSSFWorkbook workbook) {
        Practicework practicework = new Practicework();
        practicework.setPass(0);
        practicework.setType(3);
        List<Practicework> practiceworkList = getPracticework(practicework);
        System.out.println("practiceworkList.size():" + practiceworkList.size());
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("毕设");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("职称");
        rows.createCell(2).setCellValue("指导人数");
        rows.createCell(3).setCellValue("指导论文课时系数");
        rows.createCell(4).setCellValue("论文课时总数");
        rows.createCell(5).setCellValue("指导答辩人数");
        rows.createCell(6).setCellValue("答辩课时");
        rows.createCell(7).setCellValue("标准课时");
        for (int row = 1; row <= practiceworkList.size(); row++) {
            rows = sheet.createRow(row);
            System.out.println("User.name:" + practiceworkList.get(row - 1).getUser().getName());
            rows.createCell(0).setCellValue(practiceworkList.get(row - 1).getUser().getName());//当前用户姓名
            rows.createCell(1).setCellValue(practiceworkList.get(row - 1).getUser().getLevel());//当前用户职称
            rows.createCell(2).setCellValue(practiceworkList.get(row - 1).getCid() +
                    practiceworkList.get(row - 1).getSnum());//指导答辩人数
            rows.createCell(3).setCellValue(practiceworkList.get(row - 1).getCid() +
                    "*" + Coe.thesisGuide + "+" + practiceworkList.get(row - 1).getSnum() +
                    "*" + Coe.thesisGuideL);//指导论文课时系数,工科和理科带实验为15，理科为12
            rows.createCell(4).setCellValue(practiceworkList.get(row - 1).getCid() *
                    Coe.thesisGuide + practiceworkList.get(row - 1).getSnum() * Coe.thesisGuideL);//论文课时总数
            rows.createCell(5).setCellValue(practiceworkList.get(row - 1).getNum());//指导答辩人数
            rows.createCell(6).setCellValue(practiceworkList.get(row - 1).getNum() * Coe.thesisReply);//答辩课时
            DbSum dbSum = new DbSum();//给总表插入数据
            dbSum.setUid(practiceworkList.get(row - 1).getUid());
            dbSum.setPass(0);
            dbSum.setPclass(practiceworkList.get(row - 1).getClasshours());
            dbSum.setStartedDate(new Timestamp(new Date().getTime()));
            dbSum.setType(7);
            dbSumDao.insertDbSum(dbSum);
            rows.createCell(7).setCellValue(practiceworkList.get(row - 1).getClasshours());//标准课时
        }
      //  practiceworkDao.updatePassByPassAndType(1,3);
        return workbook;
    }

    @Override
    public List<User> PracticeUUser() {
        User user = new User();
        List<User> userList = userDao.getUser(user);
        List list = practiceworkDao.getUidbyPractice();
        for (int i = 0; i < userList.size(); i++) {
            for (int i1 = 0; i1 < list.size(); i1++) {
                if (userList.get(i).getId() == list.get(i1)) {
                    userList.remove(i);
                    continue;
                }
            }
        }
        return userList;
    }

}
