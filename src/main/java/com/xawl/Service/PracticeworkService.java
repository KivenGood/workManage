package com.xawl.Service;

import com.xawl.Pojo.Practicework;

import java.util.List;

public interface PracticeworkService {
    List<Practicework> getPracticework(Practicework practicework);
    void insertPracticework(Practicework practicework);
    void updatePracticeworkById(Practicework practicework);
    void deletePracticeworkById(Integer id);
}
