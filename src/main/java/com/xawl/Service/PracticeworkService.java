package com.xawl.Service;

import com.xawl.Pojo.Practicework;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PracticeworkService {
    List<Practicework> getPracticework(Practicework practicework);
    void insertPracticework(Practicework practicework);
    void updatePracticeworkById(Practicework practicework);
    void deletePracticeworkById(Integer id);
    String exportPracticework(HttpServletRequest request,Practicework practicework);
    String exportThesisework(HttpServletRequest request);
}
