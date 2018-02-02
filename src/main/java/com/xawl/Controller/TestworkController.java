package com.xawl.Controller;

import com.xawl.Pojo.Coe;
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
        System.out.println("uid:"+testwork.getUid());
        testwork.setStarteddate(new Timestamp(new Date().getTime()));
    /*    if(testwork.getType()==1||testwork.getType()==2)
            testwork.setClasshours((double)testwork.getNum()* Coe.testPaper);
        if(testwork.getType()==3)
            testwork.setClasshours((double)testwork.getNum()*Coe.invigilate);
        if (testwork.getType()==4||testwork.getType()==5||testwork.getType()==6||testwork.getType()==7)
            testwork.setClasshours((double)testwork.getNum()*Coe.inspectTest);*/
    testwork.setClasshours(calculateClasshours(testwork.getType(),testwork.getNum()));
        //查询是否已经插入过
        Testwork testwork1=new Testwork();
        testwork1.setUid(testwork.getUid());
        testwork1.setType(testwork.getType());
        List<Testwork> testworksList= testworkService.getTestwork(testwork1);
        for(int i=0;i<testworksList.size();i++)
        {
            if(testworksList.get(i).getPass()==4);
            else return new ResultData(24,"existed");
        }
        System.out.println("testwork.getLnames()"+testwork.getLnames());
        testworkService.insertTestwork(testwork);
        return new ResultData(1);
    }
    /*
    * 计算标准课时
    * */
    Double calculateClasshours(Integer type,Integer num){
        Double Classhours=0.0;
         if(type==1||type==2)
             Classhours= (double)num* Coe.testPaper;
        if(type==3)
                Classhours=(double)num*Coe.invigilate;
        if (type==4||type==5||type==6||type==7)
                Classhours=(double)num*Coe.inspectTest;
            return Classhours;
    }
    @RequestMapping("/user/updateTestworkById.action")
    @ResponseBody
    ResultData updateTestworkById(Testwork testwork){
        if(testwork==null&&testwork.getId()==null)
            return new ResultData(23);
        if(testwork.getNum()!=null&&testwork.getNum()>0)
        {
            if(testwork.getType()==null||testwork.getType()<=0)
                return new ResultData(23,"Type is null");
            testwork.setClasshours(calculateClasshours(testwork.getType(),testwork.getNum()));
        }
        testworkService.updateTestworkById(testwork);
        return new ResultData(1);
    }
    @RequestMapping("/user/getTestwork.action")
    @ResponseBody
    ResultData getTestwork(Testwork testwork){
        if(testwork==null)
            return new ResultData(23);
        if(testwork.getId()==null&&testwork.getType()==null&&testwork.getUid()==null&&testwork.getPass()==null)
            return new ResultData(26);

        return new ResultData(1, testworkService.getTestwork(testwork));
    }
    @RequestMapping("/admin/deleteTestwork.action")
    @ResponseBody
    ResultData delectTestwork(Integer id){
        if(id==null||id<=0){
            return new ResultData(23);
        }
        testworkService.deleteTestworkById(id);
        return new ResultData(1);

    }
}

