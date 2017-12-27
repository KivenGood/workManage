package com.xawl.Service.imp;

import com.xawl.Dao.UserDao;
import com.xawl.Pojo.User;
import com.xawl.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by doter on 2017/7/16.
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserDao userDao;

    public User getUser(User user) {

        User user1=userDao.getUser(user);
        return user1;
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }


}
