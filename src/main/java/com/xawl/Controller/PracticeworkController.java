package com.xawl.Controller;

import com.github.pagehelper.PageInfo;
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
import javax.servlet.http.HttpServletRequest;
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
        if ((Integer) session.getAttribute("type") == 1)
            practicework.setUid((Integer) session.getAttribute("uid"));
       // PageInfo page = new PageInfo(practiceworkService.getPracticework(practicework));
        return new ResultData(1, practiceworkService.getPracticework(practicework));
    }

    @RequestMapping("/user/insertPracticework.action")
    @ResponseBody
    ResultData insertPracticework(Practicework practicework, HttpSession session) {
        if (practicework == null)
            return new ResultData(23);
        System.out.println("practicework:" + practicework);
        practicework.setUid((Integer) session.getAttribute("uid"));
        if (practicework.getCid() == null || practicework.getCid() <= 0)
            return new ResultData(23, "Cid is null or worong");
        if (practicework.getSnum() == null || practicework.getSnum() < 0)
            return new ResultData(23, "Cid is null or worong");
        if (practicework.getType() == null || practicework.getType() <= 0)
            return new ResultData(23, "Type is null or worong");
        if (practicework.getTerm() == null || practicework.getTerm() <= 0)
            return new ResultData(23, "Term is null or worong");
        if (practicework.getNum() == null || practicework.getNum() <= 0)
            return new ResultData(23, "Num is null or worong");
        if (practicework.getType() != 3 && (practicework.getLname() == null || practicework.getLname() == "")) {
            return new ResultData(23, "Lname is null");
        }
        if (practicework.getType() == 3)
            practicework.setTerm(2);
        //查询是否已经插入过
        System.out.println("lessonwork.getType():" + practicework.getType());
        Practicework practicework1 = new Practicework();
        practicework1.setUid((Integer) session.getAttribute("uid"));
        practicework1.setType(practicework.getType());
        practicework1.setCid(practicework.getCid());
        practicework1.setTerm(practicework.getTerm());
        List<Practicework> practiceworksList = practiceworkService.getPracticework(practicework1);
        if (practiceworksList != null && practiceworksList.size() > 0 && practiceworksList.get(0).getPass() != 1)
            return new ResultData(24, "existed");

        practicework.setPass(0);
        practicework.setStarteddate(new Timestamp(new Date().getTime()));
        //班级的人数
        Integer pnum = practicework.getCid();//当type=3时，cid就是指导人数；
        Double pclass = 0.0;
        if (practicework.getType() == 3) {
            pclass = practicework.getSnum() * Coe.thesisGuideL;//type=3时，snum代表指导理工科不带实验的人数
        }/* else {*//*(practicework.getType()==1||practicework.getType()==2||practicework.getType()==4)*//*
            Dclass dclass = new Dclass();
            dclass.setId(practicework.getCid());
            System.out.println("dclass.getId():" + dclass.getId());
            pnum = dclassService.getDclass(dclass).get(0).getPnum();//改正，此处只需传实际人数，导出表时才需要班级真正人数
            System.out.println("cpnum:" + pnum);
        }*/
        practicework.setClasshours(calculateClasshours(practicework.getNum(), pnum, practicework.getType()) + pclass);
        practiceworkService.insertPracticework(practicework);
        return new ResultData(1);
    }

    /*
     * 计算标准课时
     * */
    Double calculateClasshours(Integer num, Integer pnum, Integer type) {
        Double classhours = 0.0;
        if (type == 1)
            classhours = num * pnum * Coe.practice;
        if (type == 2)
            classhours = num * pnum * Coe.noviciate;
        if (type == 3)
            classhours = num * Coe.thesisReply;//答辩课时
        if (type == 4)
            classhours = num * pnum * Coe.cthesisReply;
        return classhours;
    }

    @RequestMapping("/user/updatePracticeworkById.action")
    @ResponseBody
    ResultData updatePracticeworkById(Practicework practicework) {
        System.out.println("practicework.getLname():" + practicework.getLname());
        if (practicework == null || practicework.getId() == null)
            return new ResultData(23);
        if (practicework.getCid() != null && practicework.getNum() != null
                && practicework.getSnum() != null && practicework.getType() != null) {
           /* Dclass dclass = new Dclass();
            dclass.setId(practicework.getCid());
            Integer pnum = dclassService.getDclass(dclass).get(0).getPnum();//班级的人数
            Practicework practicework1 = new Practicework();
            practicework.setType(practiceworkService.getPracticework(practicework1).get(0).getType());*/
            Double pclass = 0.0;
            if (practicework.getType() == 3)
                pclass = practicework.getSnum() * Coe.thesisGuideL + practicework.getCid() * Coe.thesisGuide;//指导论文课时(已包含理工科)
            System.out.println("practicework.getNum():" + practicework.getNum());
            System.out.println("practicework.getSnum():" + practicework.getSnum());
            System.out.println("practicework.getType():" + practicework.getType());

            practicework.setClasshours(calculateClasshours(practicework.getNum(), practicework.getSnum(), practicework.getType()) + pclass);
        }
        practiceworkService.updatePracticeworkById(practicework);
        return new ResultData(1);
    }

    @RequestMapping("/admin/deletePracticeworkById.action")
    @ResponseBody
    ResultData deletePracticeworkById(Integer id) {
        if (id == null || id <= 0)
            return new ResultData(23);
        practiceworkService.deletePracticeworkById(id);
        return new ResultData(1);

    }

    @RequestMapping("/admin/exportPracticework.action")
    @ResponseBody
    ResultData exportPracticework(HttpServletRequest request,
                                  Practicework practicework) {
        if (practicework.getTerm() == null)
            return new ResultData(23);
        return new ResultData(1, practiceworkService.exportPracticework(request, practicework));

    }

    @RequestMapping("/admin/exportThesisework.action")
    @ResponseBody
    ResultData exportThesisework(HttpServletRequest request) {
        return new ResultData(1, practiceworkService.exportThesisework(request));
    }
    @RequestMapping("/admin/PracticeUUser.action")//考试工作量未未提交用户
    @ResponseBody
    ResultData PracticeUUser() {
        return new ResultData(1,  practiceworkService.PracticeUUser());
    }
}
