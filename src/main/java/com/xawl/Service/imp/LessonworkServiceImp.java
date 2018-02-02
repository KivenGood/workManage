package com.xawl.Service.imp;

import com.xawl.Dao.LessonworkDao;
import com.xawl.Pojo.Lessonwork;
import com.xawl.Service.LessonworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class LessonworkServiceImp implements LessonworkService{

    @Resource
    LessonworkDao lessonworkDao;
    @Override
    public List<Lessonwork> getLessonwork(Lessonwork lessonwork) {
        return lessonworkDao.getLessonwork(lessonwork);
    }

    @Override
    public void insertLessonwork(Lessonwork lessonwork) {
        lessonworkDao.insertLessonwork(lessonwork);

    }

    @Override
    public void updateLessonworkById(Lessonwork lessonwork) {

        lessonworkDao.updateLessonworkById(lessonwork);
    }

    @Override
    public void deleteLessonworkById(Integer id) {
        lessonworkDao.deleteLessonworkById(id);

    }
}
