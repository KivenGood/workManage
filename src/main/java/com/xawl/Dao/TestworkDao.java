package com.xawl.Dao;

import com.xawl.Pojo.Testwork;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

@MapperScan
public interface TestworkDao {
    List<Testwork> getTestwork(Testwork testwork);
    void insertTestwork(Testwork testwork);
    void updateTestworkById(Testwork testwork);
    void deleteTestworkById(Integer id);
    void updateTestworkByPass(Integer pass);
    List getUidbyTest();
}
