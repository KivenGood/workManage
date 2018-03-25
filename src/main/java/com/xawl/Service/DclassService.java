package com.xawl.Service;

import com.xawl.Pojo.Dclass;

import java.util.List;

public interface DclassService {
    List<Dclass> getDclass(Dclass dclass);
    void insertDclass(Dclass dclass);
    void updateDclassById(Dclass dclass);
    void deleteDclassById(Integer id);

}
