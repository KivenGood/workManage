package com.xawl.Dao;

import com.xawl.Pojo.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by doter on 2017/7/16.
 */
@MapperScan
public interface UserDao {
   List<User> getUser(User user);
   void  updateUserById(User user);
   void insertUser(User user);
   void deleteUserById(int id);
}
