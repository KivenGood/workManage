package com.xawl.Controller;

import com.xawl.Pojo.Dclass;
import com.xawl.Service.DclassService;
import com.xawl.Service.UserService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
}
