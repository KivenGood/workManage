package com.xawl.Controller;

import com.xawl.Pojo.Testwork;
import com.xawl.Service.TestworkService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class TestworkController {
    @Resource
    TestworkService testworkService;

    @RequestMapping("/user/insertTestwork.action")
    @ResponseBody
    ResultData insertTestwork(HttpSession session, Testwork testwork){
        if(testwork==null)
            return new ResultData(23);
        if(testwork.getType()==null||testwork.getNum()==null)
            return new ResultData(26);
        testwork.setPass(0);
        testwork.setUid((Integer) session.getAttribute("uid"));
        testwork.setStarteddate(new Timestamp(new Date().getTime()));
        if(testwork.getType()==1||testwork.getType()==2)
            testwork.setClasshours((double)testwork.getNum()*6);
        if(testwork.getType()==3)
            testwork.setClasshours((double)testwork.getNum()*2);
        if (testwork.getType()==4||testwork.getType()==5||testwork.getType()==6||testwork.getType()==7)
            testwork.setClasshours((double)testwork.getNum()/5);
        //查询是否已经插入过
        Testwork testwork1=null;
        testwork1.setUid(testwork.getUid());
        testwork1.setType(testwork.getType());
        List<Testwork> testworksList= testworkService.getTestwork(testwork1);
        for(int i=0;i<testworksList.size();i++)
        {
            if(testworksList.get(i).getPass()==4);
            else return new ResultData(24,"existed");
        }

        testworkService.insertTestwork(testwork);
        return new ResultData(1);
    }
    @RequestMapping("/user/updateTestworkById.action")
    @ResponseBody
    ResultData updateTestworkById(Testwork testwork){
        if(testwork==null)
            return new ResultData(23);
        testworkService.updateTestworkById(testwork);
        return new ResultData(1);
    }

}

