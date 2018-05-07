package com.xawl.Service;

import com.xawl.Pojo.Lessonwork;
import com.xawl.Pojo.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LessonworkService {
    List<Lessonwork> getLessonwork(Lessonwork lessonwork);
    void insertLessonwork(Lessonwork lessonwork);
    void updateLessonworkById(Lessonwork lessonwork);
    void deleteLessonworkById(Integer id);
    String exportTestwork(HttpServletRequest request, Lessonwork lessonwork);

    HSSFWorkbook makeTestworkExcl(HSSFWorkbook workbook, Lessonwork lessonwork);

    List<User> LessonworkUUser();
}
