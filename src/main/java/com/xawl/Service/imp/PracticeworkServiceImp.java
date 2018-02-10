package com.xawl.Service.imp;

import com.xawl.Dao.PracticeworkDao;
import com.xawl.Pojo.Practicework;
import com.xawl.Service.PracticeworkService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PracticeworkServiceImp implements PracticeworkService {
    @Resource
    PracticeworkDao practiceworkDao;

    @Override
    public List<Practicework> getPracticework(Practicework practicework) {
        return practiceworkDao.getPracticework(practicework);
    }

    @Override
    public void insertPracticework(Practicework practicework) {
        practiceworkDao.insertPracticework(practicework);
    }

    @Override
    public void updatePracticeworkById(Practicework practicework) {
        practiceworkDao.updatePracticeworkById(practicework);
    }

    @Override
    public void deletePracticeworkById(Integer id) {
        practiceworkDao.deletePracticeworkById(id);
    }
}
