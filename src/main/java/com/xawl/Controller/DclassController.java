package com.xawl.Controller;

import com.xawl.Pojo.Dclass;
import com.xawl.Pojo.DclassList;
import com.xawl.Service.DclassService;
import com.xawl.Service.UserService;
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
public class DclassController {
    @Resource
    DclassService dclassService;

    @RequestMapping("/getDclass.action")
    @ResponseBody
    ResultData getDclass(Dclass dclass){


        List<Dclass> dclassList= dclassService.getDclass(dclass);

        return  new ResultData(1,dclassList);
    }
    @RequestMapping("/admin/updateDclass.action")
    @ResponseBody
    ResultData updateDclass(Dclass dclass){
        if(dclass==null)
            return new ResultData(23);
        if(dclass.getId()==null)
            return new ResultData(26);

        dclassService.updateDclassById(dclass);

        return  new ResultData(1);
    }
    @RequestMapping("/admin/delectDclass.action")
    @ResponseBody
    ResultData delectDclass(Dclass dclass){
        if(dclass==null)
            return new ResultData(23);
        if(dclass.getId()==null)
            return new ResultData(26);

        dclassService.delectDclassById(dclass);

        return  new ResultData(1);
    }
    @RequestMapping("/admin/batchDclass.action")
    @ResponseBody
    ResultData batchUsers(HttpSession session, DclassList dclassList){
        if(dclassList==null)
            return new ResultData(23);
        Dclass dclass =new Dclass();
        List<Dclass> list = dclassList.getList();
        for(int i=0;i<list.size();i++)
        {
            dclass=list.get(i);
            if(dclass.getCname()==null&&dclass.getCname()=="")
            {
                return new ResultData(i+1,"Techno");
            }
            if(dclass.getPnum()==null&&dclass.getPnum()<=0)
            {
                return new ResultData(i+1,"Pnum");
            }

            if(dclass.getSeries()==null&&dclass.getSeries()<=0)
            {
                return new ResultData(i+1,"Series");
            }
            if(dclass.getSdept()==null&&dclass.getSdept()=="")
            {
                return new ResultData(i+1,"Sdept");
            }
            dclass.setStarteddate(new Timestamp(new Date().getTime()));
            dclassService.insertDclass(dclass);
        }
        return new ResultData(1);
    }
}
