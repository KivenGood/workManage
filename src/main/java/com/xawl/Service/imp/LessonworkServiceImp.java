package com.xawl.Service.imp;

import com.xawl.Dao.DbSumDao;
import com.xawl.Dao.DclassDao;
import com.xawl.Dao.LessonworkDao;
import com.xawl.Dao.UserDao;
import com.xawl.Pojo.DbSum;
import com.xawl.Pojo.Dclass;
import com.xawl.Pojo.Lessonwork;
import com.xawl.Pojo.User;
import com.xawl.Service.LessonworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class LessonworkServiceImp implements LessonworkService {

    @Resource
    LessonworkDao lessonworkDao;
    @Resource
    DclassDao dclassDao;
    @Resource
    DbSumDao dbSumDao;
    @Resource
    UserDao userDao;

    @Override
    public List<Lessonwork> getLessonwork(Lessonwork lessonwork) {
        //if(lessonwork.getPageNum()!=null&&lessonwork.getPageSize()!=null)
        //PageHelper.startPage(lessonwork.getPageNum(),lessonwork.getPageSize());
        System.out.println("11:"+lessonwork);
        List<Lessonwork> lessonworksList = lessonworkDao.getLessonwork(lessonwork);
        System.out.println("lessonworksList.size()" + lessonworksList.size());
        //if(lessonworksList.size()==0) return null;
        for (int i = 0; i < lessonworksList.size(); i++) {
            Dclass dclass = new Dclass();
            dclass.setId(lessonworksList.get(i).getCid());
            System.out.println("Dclass.id:" + dclass.getId());
            dclass = dclassDao.getDclass(dclass).get(0);
            lessonworksList.get(i).setCname(dclass.getSeries() + dclass.getCname());//班级姓名
            lessonworksList.get(i).setCnum(dclass.getPnum());//班级人数
            if (lessonworksList.get(i).getPart() != null && lessonworksList.get(i).getPart() != "") {
                System.out.println("lessonworkList.get(" + i + ").getId():" + lessonworksList.get(i).getId());
                String[] pnums = lessonworksList.get(i).getPart().split(",");
                System.out.println("pnums.length:" + pnums.length);
                for (int j = 0; j < pnums.length; j++) {
                    if (pnums[j] != null && pnums[j] != "") {
                        Dclass dclass1 = new Dclass();
                        System.out.println("pnums[" + j + "]:" + pnums[j]);
                        dclass1.setId(Integer.parseInt(pnums[j]));
                        System.out.println("dclass1.getId:" + dclass1.getId());
                        System.out.println("dclassDao.getDclass(dclass1).get(0):" + dclassDao.getDclass(dclass1).get(0));
                        dclass1 = dclassDao.getDclass(dclass1).get(0);
                        lessonworksList.get(i).setCname(lessonworksList.get(i).getCname() + "," + dclass.getSeries() + dclass1.getCname());//班级姓名
                        lessonworksList.get(i).setCnum(lessonworksList.get(i).getCid() + dclass1.getPnum());//班级人数
                    }
                }
            }
        }
        return lessonworksList;
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

    @Transactional//这里加事物注解的原因是，同一个类中，无事物注解的方法中调用有事物注解的方法，事物不执行
    @Override
    public String exportTestwork(HttpServletRequest request, Lessonwork lessonwork) {
        lessonwork.setPass(0);
        Calendar a = Calendar.getInstance();
        System.out.println(a.get(Calendar.YEAR));
        String fileName = a.get(Calendar.YEAR) + "第" + lessonwork.getTerm() + "学期课程工作量统计.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook = makeTestworkExcl(workbook, lessonwork);
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {
            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            xlsStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "files/" + fileName;
    }

    @Transactional
    public HSSFWorkbook makeTestworkExcl(HSSFWorkbook workbook, Lessonwork lessonwork) {//excl的具体创建，分开是因为方便做总表时的创建；
        HSSFSheet sheet = workbook.createSheet("课堂" + lessonwork.getTerm());
        List<Lessonwork> lessonworkList = getLessonwork(lessonwork);
        if(lessonworkList==null)
            return null;
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
        System.out.println("lessonworkList.size():" + lessonworkList.size());
        int i = 0;//控制lessonList的行
        int uid = 0;//控制表格的换行
        for (int row = 1; row <= lessonworkList.size(); row++) {//控制行
            Double pclassSum = 0.0;//总课时

            DbSum dbSum = new DbSum();//给总表插入数据
            dbSum.setUid(lessonworkList.get(i).getUid());
            System.out.println("1111111111111111111111111");
            dbSum.setPass(0);
            dbSum.setStartedDate(new Timestamp(new Date().getTime()));
            dbSum.setType(0 + lessonwork.getTerm());

            uid = lessonworkList.get(i).getUid();
            rows = sheet.createRow(row);
            System.out.println("lessonworkList.get(i):" + i);
            System.out.println("lessonworkList.get(i).getUser()" + lessonworkList.get(i).getUser());
            System.out.println("User.name:" + lessonworkList.get(i).getUid());
            rows.createCell(0).setCellValue(lessonworkList.get(i).getUser().getName());//当前用户姓名
            rows.createCell(1).setCellValue(lessonworkList.get(i).getUser().getLevel());//当前用户职称
            System.out.println("dclassName:" + lessonworkList.get(i).getCname());
            System.out.println("dclassPNum:" + lessonworkList.get(i).getCnum());
            if (lessonworkList.get(i).getType() == 1 || lessonworkList.get(i).getType() == 2) {//如果不是实验课
                rows.createCell(2).setCellValue(lessonworkList.get(i).getLname());//课程名
                rows.createCell(5).setCellValue("拆班");
                System.out.println("lessonworkList.get(" + i + ").getPart():" + lessonworkList.get(i).getPart());
                // System.out.println("lessonworkList.get("+i+").getPart().length():"+ lessonworkList.get(i).getPart().length());
                if (lessonworkList.get(i).getPart() != null && lessonworkList.get(i).getPart() != "") {
                    rows.createCell(5).setCellValue("合班");//班级名
                }
                rows.createCell(3).setCellValue(lessonworkList.get(i).getCname());//班级名
                rows.createCell(4).setCellValue(lessonworkList.get(i).getCnum());//班级人数
                rows.createCell(6).setCellValue(lessonworkList.get(i).getPclasshours());//计划学时
                rows.createCell(7).setCellValue(lessonworkList.get(i).getCoe());//系数
                rows.createCell(8).setCellValue(lessonworkList.get(i).getClasshours());//标准学时
                pclassSum += lessonworkList.get(i).getClasshours();
                i++;
                if (i >= lessonworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    rows.createCell(14).setCellValue(pclassSum);
//                    System.out.println("lessonworkList.get(i).getUser().getName():"+lessonworkList.get(i).getUser().getName());
                    System.out.println("break pclassSum:" + pclassSum);
                    // row = i;
                    break;
                }
                if (uid != lessonworkList.get(i).getUid()) {
                    // row = i;
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    System.out.println("break pclassSum:" + pclassSum);
                    rows.createCell(14).setCellValue(pclassSum);
                    continue;
                }
            }
            if (lessonworkList.get(i).getType() == 3) {
                rows.createCell(9).setCellValue(lessonworkList.get(i).getLname());//课程名
                rows.createCell(10).setCellValue(lessonworkList.get(i).getCname());//任课班级
                rows.createCell(11).setCellValue(lessonworkList.get(i).getCnum());//班级人数
                rows.createCell(12).setCellValue(lessonworkList.get(i).getPclasshours());//实验课时
                rows.createCell(13).setCellValue(lessonworkList.get(i).getClasshours());//标准课时
                pclassSum += lessonworkList.get(i).getClasshours();
                i++;
                if (i >= lessonworkList.size()) {
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    //    System.out.println("lessonworkList.get(i).getUser().getName():"+lessonworkList.get(i).getUser().getName());
                    System.out.println("break pclassSum:" + pclassSum);
                    rows.createCell(14).setCellValue(pclassSum);
                    break;
                }
                if (uid != lessonworkList.get(i).getUid()) {
                    // row = i;
                    dbSum.setPclass(pclassSum);
                    dbSumDao.insertDbSum(dbSum);
                    System.out.println("break pclassSum:" + pclassSum);
                    rows.createCell(14).setCellValue(pclassSum);
                    continue;
                }
            }
            System.out.println("lessonworkList.get(i).getUser().getName():" + lessonworkList.get(i).getUser().getName());
            System.out.println("pclassSum:" + pclassSum);
            dbSum.setPclass(pclassSum);//这里执行的误区是break和continue后不执行
            dbSumDao.insertDbSum(dbSum);
            rows.createCell(14).setCellValue(pclassSum);

        }
        //lessonworkDao.updateLessonworkByPass(1);
        return workbook;
    }

    @Override
    public List<User> LessonworkUUser() {
        User user = new User();
        List<User> userList = userDao.getUser(user);
        List list = lessonworkDao.getUidbyLesson();
        for (int i = 0; i <userList.size(); i++) {
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