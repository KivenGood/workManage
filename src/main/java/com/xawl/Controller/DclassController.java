package com.xawl.Controller;

import com.github.pagehelper.PageInfo;
import com.xawl.Pojo.Dclass;
import com.xawl.Pojo.DclassList;
import com.xawl.Service.DclassService;
import com.xawl.Service.UserService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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


    //    List<Dclass> dclassList= dclassService.getDclass(dclass);
      //  PageInfo page = new PageInfo(dclassList);
        return  new ResultData(1,dclassService.getDclass(dclass));
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
    @RequestMapping("/admin/deleteDclass.action")
    @ResponseBody
    ResultData delectDclass(Integer id){
        if(id==null)
            return new ResultData(23);


        dclassService.deleteDclassById(id);

        return  new ResultData(1);
    }
    @RequestMapping("/admin/batchDclass.action")
    @ResponseBody
    ResultData batchUsers(DclassList dclassList){
        if(dclassList==null)
            return new ResultData(23);
        return new ResultData(1,dclassService.batchDclass((List<Dclass>) dclassList.getList()));
    }
    @RequestMapping("/user/insertDclass.action")
    @ResponseBody
    ResultData insertDclass(Dclass dclass){
        if(dclass==null)
            return new ResultData(23);
        if(dclass.getSdept()==null||dclass.getSdept()=="")
            return new ResultData(26,"所在系为空");
        if(dclass.getSeries()==null||dclass.getSeries()<=0)
            return new ResultData(26,"年级为空");
        if(dclass.getPnum()==null||dclass.getPnum()<=0)
            return new ResultData(26,"人数为空");

        Dclass dclass1=new Dclass();//判重
        dclass1.setSeries(dclass.getSeries());
        dclass1.setCname(dclass.getCname());
        List<Dclass> dclassList=dclassService.getDclass(dclass1);
        System.out.println("dclassList.size():"+dclassList.size());
        if(dclassList!=null&&dclassList.size()>0)
            return new ResultData(26,"excisted");
        dclass.setType(1);
        dclass.setStarteddate(new Timestamp(new Date().getTime()));
        dclassService.insertDclass(dclass);
        return new ResultData(1);
    }
    @RequestMapping("/admin/batchDclassExcl.action")
    @ResponseBody
    ResultData batchDclassExcl(String  path) throws Exception {
        //dclassService.batchDclassExcl(path);

        return new ResultData(1, dclassService.batchDclassExcl(path));

    }

}
