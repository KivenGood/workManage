package com.xawl.Service;

import com.xawl.Pojo.Testwork;

import java.util.List;

public interface TestworkService {
    List<Testwork> getTestwork(Testwork testwork);
    void insertTestwork(Testwork testwork);
    void updateTestworkById(Testwork testwork);
    void delectTestworkById(Testwork testwork);
}
