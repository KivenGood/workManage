package com.xawl.Dao;


import com.xawl.Pojo.Message;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface MessageDao {
    List<Message> getMessage(Message message);
    void insertMessage(Message message);
    void updateMessageById(Message message);
    void deleteMessageById(Integer id);

}
