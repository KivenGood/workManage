package com.xawl.Service;

import com.xawl.Pojo.User;

import java.util.List;

/**
 * Created by doter on 2017/7/16.
 */
public interface UserService {
    List<User> getUser(User user);
    void updateUserById(User user);
    void insertUser(User user);
    void deleteUser(Integer id);

}
