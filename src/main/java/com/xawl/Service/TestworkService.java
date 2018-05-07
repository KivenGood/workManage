package com.xawl.Service;

import com.xawl.Pojo.Testwork;
import com.xawl.Pojo.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TestworkService {
    List<Testwork> getTestwork(Testwork testwork);
    void insertTestwork(Testwork testwork);
    void updateTestworkById(Testwork testwork);
    void deleteTestworkById(Integer id);
    String  exportTestwork(HttpServletRequest request,Testwork testwork);
    List<User> TestworkUUser();
    HSSFWorkbook makeTestworkExcl(HSSFWorkbook workbook, Testwork testwork);
}
