package com.xawl.Dao;

import com.xawl.Pojo.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by doter on 2017/7/16.
 */
@MapperScan
public interface UserDao {
    User getUser(User user);
   void  updateUserById(User user);
}
