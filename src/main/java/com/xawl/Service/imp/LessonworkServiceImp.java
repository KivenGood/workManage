package com.xawl.Service.imp;

import com.xawl.Dao.DclassDao;
import com.xawl.Dao.LessonworkDao;
import com.xawl.Dao.UserDao;
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
import java.util.Calendar;
import java.util.List;

@Service
public class LessonworkServiceImp implements LessonworkService {

    @Resource
    LessonworkDao lessonworkDao;
    @Resource
    UserDao userDao;
    @Resource
    DclassDao dclassDao;

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

    @Transactional
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
        Integer uid = null;//用户id，控制excl何时进行下一行
        int i = 0;//控制lessonList的行
        for (int row = 1; row <= lessonworkList.size(); row++) {//控制行
            Double pclassSum = 0.0;//总课时
            rows = sheet.createRow(row);
            User user = new User();
            user.setId(lessonworkList.get(i).getUid());
            System.out.println("User.id:" + user.getId());
            rows.createCell(0).setCellValue(userDao.getUser(user).get(0).getName());//当前用户姓名
            rows.createCell(1).setCellValue(userDao.getUser(user).get(0).getLevel());//当前用户职称
          //  uid = lessonworkList.get(i).getUid();
           // do
                Dclass dclass = new Dclass();
                dclass.setId(lessonworkList.get(i).getCid());
                System.out.println("Dclass.id:" + dclass.getId());
                dclass = dclassDao.getDclass(dclass).get(0);
                String dclassName = dclass.getSeries() + dclass.getCname();//班级姓名
                Integer dclassPNum = dclass.getPnum();//班级人数
                System.out.println("dclassName:" + dclassName);
                System.out.println("dclassPNum:" + dclassPNum);
                if (lessonworkList.get(i).getType() == 1 || lessonworkList.get(i).getType() == 2) {//如果不是实验课
                    rows.createCell(2).setCellValue(lessonworkList.get(i).getLname());//课程名
                    rows.createCell(5).setCellValue("拆班");
                    System.out.println("lessonworkList.get(" + i + ").getPart():" + lessonworkList.get(i).getPart());
                    // System.out.println("lessonworkList.get("+i+").getPart().length():"+ lessonworkList.get(i).getPart().length());
                    if (lessonworkList.get(i).getPart() != null && lessonworkList.get(i).getPart() != "") {
                        //System.out.println("lessonworkList.get(" + i + ").getPart():" + lessonworkList.get(i).getPart());
                        System.out.println("lessonworkList.get(" + i + ").getId():" + lessonworkList.get(i).getId());
                        String[] pnums = lessonworkList.get(i).getPart().split(",");

                        for (int j = 0; j < pnums.length; j++) {
                            Dclass dclass1 = new Dclass();
                            System.out.println("pnums[" + j + "]:" + pnums[j]);
                            dclass1.setId(Integer.parseInt(pnums[j]));
                            System.out.println("dclass1.getId:"+dclass1.getId());
                            System.out.println("dclassDao.getDclass(dclass1).get(0):"+dclassDao.getDclass(dclass1).get(0));
                            dclass1 = dclassDao.getDclass(dclass1).get(0);
                            dclassPNum += dclass1.getPnum();
                            dclassName += "," + dclass1.getSeries() + dclass1.getCname();
                        }
                        System.out.println("dclassName和:" + dclassName);
                        System.out.println("dclassPNum和:" + dclassPNum);
                        rows.createCell(5).setCellValue("合班");//班级名
                    }
                    rows.createCell(3).setCellValue(dclassName);//班级名
                    rows.createCell(4).setCellValue(dclassPNum);//班级人数
                    rows.createCell(6).setCellValue(lessonworkList.get(i).getPclasshours());//计划学时
                    rows.createCell(7).setCellValue(lessonworkList.get(i).getCoe());//系数
                    rows.createCell(8).setCellValue(lessonworkList.get(i).getClasshours());//标准学时
                    pclassSum = lessonworkList.get(i).getClasshours();
                    i++;
                    if (i >= lessonworkList.size()) {
                       // row = i;
                        break;
                    }

                } if(lessonworkList.get(i).getType() == 3) {
                    rows.createCell(9).setCellValue(lessonworkList.get(i).getLname());//课程名
                    rows.createCell(10).setCellValue(dclassName);//任课班级
                    rows.createCell(11).setCellValue(dclassPNum);//班级人数
                    rows.createCell(12).setCellValue(lessonworkList.get(i).getPclasshours());//实验课时
                    rows.createCell(13).setCellValue(lessonworkList.get(i).getClasshours());//标准课时
                    pclassSum += lessonworkList.get(i).getClasshours();
                    i++;
                    if (i >= lessonworkList.size()) {
                      //  row = i;
                        break;
                    }
                    //break;
                }

           // while (uid == lessonworkList.get(i).getUid());
            rows.createCell(14).setCellValue(pclassSum);
            //pclassSum = 0.0;
        }
        String path = request.getSession().getServletContext().getRealPath("files");
        System.out.println("path：" + path);
        try {

            File xlsFile = new File(path, fileName);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path+fileName;
    }
}