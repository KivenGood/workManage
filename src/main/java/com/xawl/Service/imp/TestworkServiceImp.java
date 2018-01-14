package com.xawl.Service.imp;

import com.xawl.Dao.TestworkDao;
import com.xawl.Pojo.Testwork;
import com.xawl.Service.TestworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TestworkServiceImp implements TestworkService{
    @Resource
    TestworkDao testworkDao;
    @Override
    public List<Testwork> getTestwork(Testwork testwork) {

        return  testworkDao.getTestwork(testwork);
    }

    @Override
    public void insertTestwork(Testwork testwork) {
        testworkDao.insertTestwork(testwork);
    }

    @Override
    public void updateTestworkById(Testwork testwork) {
        testworkDao.updateTestworkById(testwork);
    }

    @Override
    public void delectTestworkById(Integer id) {
        testworkDao.delectTestworkById(id);
    }
}
