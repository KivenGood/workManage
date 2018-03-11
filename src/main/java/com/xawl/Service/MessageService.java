package com.xawl.Service;

import com.xawl.Pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessage(Message message);
    void insertMessage(Message message);
    void updateMessageById(Message message);
    void deleteMessageById(Integer id);
}
