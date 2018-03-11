package com.xawl.Controller;

import com.xawl.Pojo.Message;
import com.xawl.Service.MessageService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MessageController {
    @Resource
    MessageService messageService;

    @RequestMapping("/getMessage.action")
    @ResponseBody
    ResultData getMessage(Message message)
    {
        if (message==null)
            message.setType(1);
        return new ResultData(1, messageService.getMessage(message));
    }
    @RequestMapping("/admin/insertMessage.action")
    @ResponseBody
    ResultData insertMessage(Message message)
            {
        if (message.getData()==null)
                return new ResultData(-23,"date is null");
                messageService.insertMessage(message);
        return new ResultData(1);
    }
    @RequestMapping("/admin/updateMessage.action")
    @ResponseBody
    ResultData updateMessage(Message message)
    {
        if (message==null)
            return new ResultData(-23);
        if (message.getId()==null)
            return new ResultData(-22,"id is null");
        messageService.updateMessageById(message);
        return new ResultData(1);
    }
    @RequestMapping("/admin/deleteMessage.action")
    @ResponseBody
    ResultData deleteMessage(Integer id)
    {
        if (id==null)
            return new ResultData(-23,"id is null");
        messageService.deleteMessageById(id);
        return new ResultData(1);
    }
}
