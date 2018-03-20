package com.xawl.Controller;

import com.xawl.Pojo.Coe;
import com.xawl.Pojo.Dclass;
import com.xawl.Pojo.Practicework;
import com.xawl.Service.DclassService;
import com.xawl.Service.PracticeworkService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PracticeworkController {
    @Resource
    PracticeworkService practiceworkService;
    @Resource
    DclassService dclassService;

    @RequestMapping("/user/getPracticework.action")
    @ResponseBody
    ResultData getLessonwork(Practicework practicework, HttpSession session) {
        if (practicework.getUid()==null)
            practicework.setUid((Integer) session.getAttribute("uid"));
        return new ResultData(1, practiceworkService.getPracticework(practicework));
    }
    @RequestMapping("/user/insertPracticework.action")
    @ResponseBody
    ResultData insertLesswork(Practicework practicework, HttpSession session) {
        if (practicework == null)
            return new ResultData(23);
        System.out.println("practicework:"+practicework);
        practicework.setUid((Integer) session.getAttribute("uid"));
        if (practicework.getCid() == null || practicework.getCid() <= 0)
            return new ResultData(23, "Cid is null or worong");
        //查询是否已经插入过
        System.out.println("lessonwork.getType():"+practicework.getType());
        Practicework practicework1=new Practicework();
        practicework1.setUid(practicework.getUid());
        practicework1.setType(practicework.getType());
        practicework1.setCid(practicework.getCid());
        practicework1.setTerm(practicework.getTerm());
        List<Practicework> practiceworksList= practiceworkService.getPracticework(practicework1);
        if(practiceworksList!=null&&practiceworksList.size()>0&&practiceworksList.get(0).getPass()!=4)
            return new ResultData(24,"existed");

        if (practicework.getType() == null || practicework.getType() <= 0)
            return new ResultData(23, "Type is null or worong");
        if (practicework.getNum() == null || practicework.getNum() <= 0)
            return new ResultData(23, "Num is null or worong");
        if(practicework.getType()==4){
            if (practicework.getLname() == null || practicework.getLname() == "")
                return new ResultData(23, "Lname is null");
            if (practicework.getCid() >=8)
                return new ResultData(23, "Cid must <=8 When type=4");
        }
        practicework.setPass(0);
        practicework.setStarteddate(new Timestamp(new Date().getTime()));
        //班级的人数
        Integer pnum=practicework.getCid();//当type=3时，cid就是指导人数；
        Double pclass=0.0;//type=3时，lanme代表指导理工科不带实验的人数；
        if(practicework.getType()==3){
            pclass=Integer.valueOf(practicework.getLname())*Coe.thesisGuideL;
        }
        else{/*(practicework.getType()==1||practicework.getType()==2||practicework.getType()==4)*/
            Dclass dclass = new Dclass();
            dclass.setId(practicework.getCid());
            System.out.println("dclass.getId():"+dclass.getId());
            pnum = dclassService.getDclass(dclass).get(0).getPnum();
             System.out.println("cpnum:"+pnum);
        }

        practicework.setClasshours(calculateClasshours(practicework.getNum(),pnum,practicework.getType())+pclass);
        practiceworkService.insertPracticework(practicework);
        return new ResultData(1);
    }
    /*
    * 计算标准课时
    * */
    Double calculateClasshours(Integer num, Integer pnum,Integer type){
        Double classhours=0.0;
        if(type==1)
            classhours=num*pnum*Coe.practice;
        if(type==2)
            classhours=num*pnum*Coe.noviciate;
        if(type==3)
            classhours=pnum*Coe.thesisGuide+num*Coe.thesisReply;
        if(type==4)
            classhours=num*pnum*Coe.cthesisReply;
        return classhours;
    }
    @RequestMapping("/user/updatePracticeworkById.action")
    @ResponseBody
    ResultData updatePracticeworkById(Practicework practicework){
        System.out.println("practicework.getLname():"+practicework.getLname());
        if(practicework==null||practicework.getId()==null)
            return new ResultData(23);
        if(practicework.getCid()!=null||practicework.getNum()!=null||
                (practicework.getType()==3&&practicework.getLname()!=null&&practicework.getLname()==""))
        {
            Dclass dclass = new Dclass();
            dclass.setId(practicework.getCid());
            Integer pnum = dclassService.getDclass(dclass).get(0).getPnum();//班级的人数
            Practicework practicework1=new Practicework();
            practicework.setType(practiceworkService.getPracticework(practicework1).get(0).getType());
            Double pclass=0.0;//type=3时，lanme代表指导理工科不带实验的人数；
            if(practicework.getType()==3&&practicework.getLname()!=null&&practicework.getLname()=="")
                pclass=Integer.valueOf(practicework.getLname())*Coe.thesisGuideL;
            practicework.setClasshours(calculateClasshours(practicework.getNum(),pnum,practicework.getType())+pclass);
        }
        practiceworkService.updatePracticeworkById(practicework);
        return new ResultData(1);
    }

    @RequestMapping("/admin/deletePracticeworkById.action")
    @ResponseBody
    ResultData deletePracticeworkById(Integer id) {
        if(id==null||id<=0)
            return new ResultData(23);
        practiceworkService.deletePracticeworkById(id);
        return new ResultData(1);

    }
}
