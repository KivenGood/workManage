package com.xawl.Service.imp;

import com.xawl.Dao.UserDao;
import com.xawl.Pojo.User;
import com.xawl.Service.UserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by doter on 2017/7/16.
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserDao userDao;

    public List<User> getUser(User user) {
       // if(user.getPageNum()!=null&&user.getPageSize()!=null)
         //   PageHelper.startPage(user.getPageNum(),user.getPageSize());
        return userDao.getUser(user);
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    @Transactional
    public String batchUsers(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            // user = list.get(i);
            if (userList.get(i).getTechno() == null && userList.get(i).getTechno() == "") {
                return i + 1 + "Techno is null";
            }

            if (userList.get(i).getSdept() == null && userList.get(i).getSdept() == "") {
                return i + 1 + "Sdept is null";
            }
            if (userList.get(i).getLevel() == null && userList.get(i).getLevel() == "") {
                return i + 1 + "Level is null";
            }
            if (userList.get(i).getName() == null && userList.get(i).getName() == "") {
                    return i + 1 + "Name is null";
            }
            userList.get(i).setPass(userList.get(i).getTechno());
            userList.get(i).setStarteddate(new Timestamp(new Date().getTime()));
            userList.get(i).setType(1);
            System.out.println("userList.get(i):" + userList.get(i));
            userDao.insertUser(userList.get(i));
        }
        return "1";
    }


    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDao.deleteUserById(id);
    }

    public String batchUsersExcl(String xlsPath) throws IOException {
        List<User> userList = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new XSSFWorkbook(fileIn);
//获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
//创建实体类
            User user = new User();
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
            user.setTechno(r.getCell(0).getStringCellValue());
            user.setName(r.getCell(1).getStringCellValue());
            user.setLevel(r.getCell(2).getStringCellValue());
            user.setSdept(r.getCell(3).getStringCellValue());
            user.setPass(user.getTechno());
            user.setType(1);
            user.setStarteddate(new Timestamp(new Date().getTime()));
            userList.add(user);
        }
        fileIn.close();
        return batchUsers(userList);
        // userDao.insertUser(userList);

    }

}
