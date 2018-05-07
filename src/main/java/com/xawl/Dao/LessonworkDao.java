package com.xawl.Dao;

import com.xawl.Pojo.Lessonwork;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface LessonworkDao {
    List<Lessonwork> getLessonwork(Lessonwork lessonwork);
    void insertLessonwork(Lessonwork lessonwork);
    void updateLessonworkById(Lessonwork lessonwork);
    void deleteLessonworkById(Integer id);
    void updateLessonworkByPass(Integer pass);

    List getUidbyLesson();
}
