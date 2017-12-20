package com.xawl.Service;

import com.xawl.Pojo.User;

/**
 * Created by doter on 2017/7/16.
 */
public interface UserService {
    User getUser(User user);
    void updateUserById(User user);

}
