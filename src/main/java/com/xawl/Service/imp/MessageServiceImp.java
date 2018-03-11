package com.xawl.Service.imp;

import com.xawl.Dao.MessageDao;
import com.xawl.Pojo.Message;
import com.xawl.Service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MessageServiceImp implements MessageService {
    @Resource
    MessageDao messageDao;
    @Override
    public List<Message> getMessage(Message message) {
        return messageDao.getMessage(message);
    }

    @Override
    public void insertMessage(Message message) {
        messageDao.insertMessage(message);
    }

    @Override
    public void updateMessageById(Message message) {
        messageDao.updateMessageById(message);
    }

    @Override
    public void deleteMessageById(Integer id) {
        messageDao.deleteMessageById(id);
    }
}
