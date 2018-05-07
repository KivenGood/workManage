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
    @RequestMapping("/admin/batchDclassExcl.action")
    @ResponseBody
    ResultData batchDclassExcl(MultipartFile file) throws Exception {
        //dclassService.batchDclassExcl(path);

        return new ResultData(1, dclassService.batchDclassExcl("C:\\Users\\Kiven\\Desktop\\批量导入班级样表.xlsx"));

    }

}
