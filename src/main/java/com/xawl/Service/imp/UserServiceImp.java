package com.xawl.Service.imp;

import com.xawl.Dao.UserDao;
import com.xawl.Pojo.User;
import com.xawl.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by doter on 2017/7/16.
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserDao userDao;

    public List<User> getUser(User user) {

        return userDao.getUser(user);
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUserById(id);
    }


}
