package com.xawl.Service;

import com.xawl.Pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by doter on 2017/7/16.
 */
public interface UserService {
    List<User> getUser(User user);
    void updateUserById(User user);
    void insertUser(User user);
    String  batchUsers(List<User> userList);
    void deleteUser(Integer id);
    String batchUsersExcl(String path) throws IOException;
}
