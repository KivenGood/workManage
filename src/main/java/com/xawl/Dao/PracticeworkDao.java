package com.xawl.Dao;

import com.xawl.Pojo.Practicework;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

@MapperScan
public interface PracticeworkDao {
    List<Practicework> getPracticework(Practicework practicework);
    void insertPracticework(Practicework practicework);
    void updatePracticeworkById(Practicework practicework);
    void deletePracticeworkById(Integer id);
   // void updatePracticeworkByPass(Integer pass);
    void updatePassByPassAndType(Integer pass,Integer type);

    List getUidbyPractice();
}
