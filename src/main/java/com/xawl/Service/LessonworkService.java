package com.xawl.Service;

import com.xawl.Pojo.Lessonwork;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LessonworkService {
    List<Lessonwork> getLessonwork(Lessonwork lessonwork);
    void insertLessonwork(Lessonwork lessonwork);
    void updateLessonworkById(Lessonwork lessonwork);
    void deleteLessonworkById(Integer id);
    String exportTestwork(HttpServletRequest request, Lessonwork lessonwork);
}
