package com.xawl.Service.imp;

import com.xawl.Dao.DclassDao;
import com.xawl.Pojo.Dclass;
import com.xawl.Service.DclassService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class DclassServiceImp implements DclassService {
    @Resource
    DclassDao dclassDao;

    @Override
    public List<Dclass> getDclass(Dclass dclass) {
       // if(dclass.getPageNum()!=null&&dclass.getPageSize()!=null)
           // PageHelper.startPage(dclass.getPageNum(),dclass.getPageSize());
        List<Dclass> DclassList = dclassDao.getDclass(dclass);
        return DclassList;
    }


    @Override
    public void insertDclass(Dclass dclass) {
        dclassDao.insertDclass(dclass);
    }

    @Override
    public void updateDclassById(Dclass dclass) {
        dclassDao.updateDclassById(dclass);
    }

    @Override
    public void deleteDclassById(Integer id) {
        dclassDao.deleteDclassById(id);
    }

    @Override
    public String batchDclassExcl(String path) throws Exception {
        List<Dclass> dclassList = new ArrayList();
        FileInputStream fileIn = new FileInputStream(path);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new XSSFWorkbook(fileIn);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() <1) {
                continue;
            }
            //创建实体类
            Dclass dclass = new Dclass();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            dclass.setCname(r.getCell(0).getStringCellValue());
            dclass.setPnum((int) r.getCell(1).getNumericCellValue());
            dclass.setSdept(r.getCell(2).getStringCellValue());
            dclass.setSeries((int)r.getCell(3).getNumericCellValue());
            dclass.setStarteddate(new Timestamp(new Date().getTime()));
            dclassList.add(dclass);
        }
        fileIn.close();
        return batchDclass(dclassList);
        // userDao.insertUser(userList);

    }


    @Transactional
    @Override
    public String batchDclass(List<Dclass> dclassList) {
        for (int i = 0; i < dclassList.size(); i++) {
            if (dclassList.get(i).getCname() == null || dclassList.get(i).getCname() == "")
                return i + 1 + "Cname is null";
            if (dclassList.get(i).getPnum() == null || dclassList.get(i).getPnum() <= 0)
                return i + 1 + "Pnum is null or Pnum<=0";
            if (dclassList.get(i).getSeries() == null || dclassList.get(i).getSeries()<= 0)
                return i + 1 + "Series is null or wrong";
            if (dclassList.get(i).getSdept() == null || dclassList.get(i).getSdept() == "")
                return i + 1 + "Sdept is null";
            dclassList.get(i).setStarteddate(new Timestamp(new Date().getTime()));
            System.out.println("userList.get(i):" + dclassList.get(i));
            dclassDao.insertDclass(dclassList.get(i));
        }
        return "";
    }
}
