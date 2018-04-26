package com.xawl.Dao;

import com.xawl.Pojo.DbSum;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface DbSumDao {
    List<DbSum> getDbSum(DbSum dbSum);

    void insertDbSum(DbSum dbSum);

    void updateDbSumByPass(Integer id);

    void deleteDbSumById(Integer id);
}
