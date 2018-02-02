package com.xawl.Dao;

import com.xawl.Pojo.Dclass;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;
@MapperScan
public interface DclassDao {
    List<Dclass> getDclass(Dclass dclass);
    void insertDclass(Dclass dclass);
    void updateDclassById(Dclass dclass);
    void deleteDclassById(Integer id);
}
