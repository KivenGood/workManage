package com.xawl.Service;

import com.xawl.Pojo.DbSum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DbSumService {
  /*  List<DbSum> getDbSum(DbSum dbSum);
    void insertDbSum(DbSum dbSum);
    void updateDbDbSumById(DbSum dbSum);
    void deleteDbDbSumById(Integer id);*/
    String exportDbSum(HttpServletRequest request);
}
