package com.xawl.Service.imp;

import com.xawl.Dao.DclassDao;
import com.xawl.Dao.UserDao;
import com.xawl.Pojo.Dclass;
import com.xawl.Service.DclassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DclassServiceImp implements DclassService {
    @Resource
    DclassDao dclassDao;
    @Override
    public List<Dclass> getDclass(Dclass dclass) {

        List<Dclass> DclassList=dclassDao.getDclass(dclass);
        return DclassList;
    }



    @Override
    public void insertDclass(Dclass dclass) {
        dclassDao.insertDclass(dclass);
    }

    @Override
    public void updateDclassById(Dclass dclass) {
        dclassDao.updateDclassById(dclass);
    }

    @Override
    public void deleteDclassById(Integer id) {
        dclassDao.deleteDclassById(id);
    }
}
