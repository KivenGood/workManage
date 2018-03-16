package com.xawl.Service.imp;

import com.xawl.Dao.TestworkDao;
import com.xawl.Dao.UserDao;
import com.xawl.Pojo.Coe;
import com.xawl.Pojo.Testwork;
import com.xawl.Pojo.User;
import com.xawl.Service.TestworkService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class TestworkServiceImp implements TestworkService {
    @Resource
    TestworkDao testworkDao;
    @Resource
    UserDao userDao;
    @Override
    public List<Testwork> getTestwork(Testwork testwork) {

        return testworkDao.getTestwork(testwork);
    }

    @Override
    public void insertTestwork(Testwork testwork) {
        testworkDao.insertTestwork(testwork);
    }

    @Override
    public void updateTestworkById(Testwork testwork) {
        testworkDao.updateTestworkById(testwork);
    }

    @Override
    public void deleteTestworkById(Integer id) {
        testworkDao.deleteTestworkById(id);
    }

    @Override
    public void exportTestwork(HttpServletRequest request) {
  /*      Testwork testwork = new Testwork();
        testwork.setPass(2);
        List<Testwork> testworkList = testworkDao.getTestwork(testwork);

        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("命题门数");
        rows.createCell(2).setCellValue("课时（命题）");
        rows.createCell(3).setCellValue("命题门数（清考）");
        rows.createCell(4).setCellValue("清考命题课时");
        rows.createCell(5).setCellValue("监考场次");
        rows.createCell(6).setCellValue("课时（监考）");
        rows.createCell(7).setCellValue("阅卷份数（正考）");
        rows.createCell(8).setCellValue("阅卷份数（补考）");
        rows.createCell(9).setCellValue("阅卷份数（重考）");
        rows.createCell(10).setCellValue("阅卷份数（清考）");
        rows.createCell(11).setCellValue("阅卷总份数");
        rows.createCell(12).setCellValue("课时（阅卷）");
        rows.createCell(13).setCellValue("总计课时");
        int id;//用户id
        int i=0;//控制testworkList的索引
        System.out.println("testworkList.size():"+testworkList.size());
        for (int row = 1; row <= testworkList.size(); row++) {//控制行
            id = testworkList.get(i).getUid();
           rows = sheet.createRow(row);
            User user=new User();
            user.setId(testworkList.get(i).getUid());
            rows.createCell(0).setCellValue(userDao.getUser(user).get(0).getName());//当前用户姓名
            double classhoursNum=0;//总计课时
            int paperNum=0;//阅卷总份数
            while(id==testworkList.get(i).getUid()) {//控制每个用户的一行
                System.out.println("第一次"+i+"......");
                if (testworkList.get(i).getType() == 1 && id == testworkList.get(i ).getUid()) {//普考命题
                    classhoursNum += testworkList.get(i).getClasshours();
                    rows.createCell(1).setCellValue(testworkList.get(i).getNum());
                    rows.createCell(2).setCellValue(testworkList.get(i).getClasshours());
                    i++;
                    if(i==testworkList.size())
                        break;
                }

                if (testworkList.get(i).getType() == 2 && id == testworkList.get(i).getUid()) {//清考命题
                    classhoursNum += testworkList.get(i).getClasshours();
                    rows.createCell(3).setCellValue(testworkList.get(i).getNum());
                    rows.createCell(4).setCellValue(testworkList.get(i).getClasshours());
                    i++;
                    if(i==testworkList.size())
                        break;
                }

                if (testworkList.get(i).getType() == 3 && id == testworkList.get(i).getUid()) {//监考
                    classhoursNum += testworkList.get(i).getClasshours();
                    rows.createCell(5).setCellValue(testworkList.get(i).getNum());
                    rows.createCell(6).setCellValue(testworkList.get(i).getClasshours());
                    i++;
                    if(i==testworkList.size())
                        break;
                }
                if (testworkList.get(i).getType() == 4 && id == testworkList.get(i).getUid()) {//正考阅卷
                    classhoursNum += testworkList.get(i).getClasshours();
                    paperNum += testworkList.get(i).getNum();
                    rows.createCell(7).setCellValue(testworkList.get(i).getNum());
                    i++;
                    if(i==testworkList.size())
                        break;
                    // rows.createCell(2).setCellValue(testworkList.get(i).getClasshours());
                }
                if (testworkList.get(i).getType() == 5 && id == testworkList.get(i).getUid()) {//补考阅卷
                    classhoursNum += testworkList.get(i).getClasshours();
                    paperNum += testworkList.get(i).getNum();
                    rows.createCell(8).setCellValue(testworkList.get(i).getNum());
                    i++;
                    if(i==testworkList.size())
                        break;
                    // rows.createCell(2).setCellValue(testworkList.get(i).getClasshours());
                }
                if (testworkList.get(i).getType() == 6 && id == testworkList.get(i).getUid()) {//重考阅卷
                    classhoursNum += testworkList.get(i).getClasshours();
                    paperNum += testworkList.get(i).getNum();
                    rows.createCell(9).setCellValue(testworkList.get(i).getNum());
                    i++;
                    if(i==testworkList.size())
                        break;
                    // rows.createCell(2).setCellValue(testworkList.get(i).getClasshours());
                }
                if (testworkList.get(i).getType() == 7 && id == testworkList.get(i).getUid())//清考阅卷
                {
                    classhoursNum += testworkList.get(i).getClasshours();
                    paperNum += testworkList.get(i).getNum();
                    rows.createCell(10).setCellValue(testworkList.get(i).getNum());
                    i++;
                    if(i==testworkList.size())
                        break;
                    // rows.createCell(2).setCellValue(testworkList.get(i).getClasshours());
                }
            }
            rows.createCell(11).setCellValue(paperNum);//阅卷总份数
            rows.createCell(12).setCellValue(paperNum* Coe.inspectTest);//课时（阅卷）
            rows.createCell(13).setCellValue(classhoursNum);//总计课时
            System.out.println("第"+i+"完。。。。。。。。。。");
            if(i==testworkList.size())
                break;
        }
       *//* for (int row = 0; row < 10; row++)
        {
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++)
            {
                // 向工作表中添加数据
                rows.createCell(col).setCellValue("data" + row + col);
            }
        }
        try {*//*
            String path =request.getSession().getServletContext().getRealPath("files");
            System.out.println("path："+path);
            File xlsFile = new File(path,"考试工作量统计.xls");
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


      testworkDao.updateTestworkByPass(4);*/
    }
}
