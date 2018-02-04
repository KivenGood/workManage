package com.xawl.Service;

import com.xawl.Pojo.Lessonwork;

import java.util.List;

public interface LessonworkService {


    List<Lessonwork> getLessonwork(Lessonwork lessonwork);
    void insertLessonwork(Lessonwork lessonwork);
    void updateLessonworkById(Lessonwork lessonwork);
    void deleteLessonworkById(Integer id);
}
