package com.xawl.Controller;

import com.xawl.Pojo.Coe;
import com.xawl.Pojo.Dclass;
import com.xawl.Pojo.Lessonwork;
import com.xawl.Service.DclassService;
import com.xawl.Service.LessonworkService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LessonworkController {
    @Resource
    LessonworkService lessonworkService;
    DclassService dclassService;

    @RequestMapping("/user/getLessonwork.action")
    @ResponseBody
    ResultData getLessonwork(Lessonwork lessonwork, HttpSession session) {
        if (lessonwork.getUid() != 0)
            lessonwork.setUid((Integer) session.getAttribute("uid"));
        return new ResultData(1, lessonworkService.getLessonwork(lessonwork));

    }

    @RequestMapping("/user/insertLesswork.action")
    @ResponseBody
    ResultData insertLesswork(Lessonwork lessonwork, HttpSession session) {
        if (lessonwork == null)
            return new ResultData(23);
        if (lessonwork.getCid() == null || lessonwork.getCid() <= 0)
            return new ResultData(23, "Cid is null or worong");
        if (lessonwork.getLname() == null || lessonwork.getLname() == "")
            return new ResultData(23, "Lname is null");
        if (lessonwork.getPclasshours() == null || lessonwork.getPclasshours() <= 0)
            return new ResultData(23, "Pclasshours is null or worong");
        if (lessonwork.getClasshours() == null || lessonwork.getClasshours() <= 0)
            return new ResultData(23, "Classhours is null or worong");
        lessonwork.setUid((Integer) session.getAttribute("uid"));
        lessonwork.setPass(0);
        Dclass dclass = new Dclass();
        dclass.setId(lessonwork.getId());
        Integer pnum = dclassService.getDclass(dclass).get(1).getPnum();//班级的人数
        //实验课,实验课没有合班课
        if (lessonwork.getType() == 3)
            lessonwork.setClasshours(lessonwork.getPclasshours() * (double) pnum / 15);
        if (lessonwork.getType() == 2 || lessonwork.getType() == 1) {
            //若不是合班课
            if (lessonwork.getPart() == null || lessonwork.getPart() == "") {
                if (pnum <= 50)
                    lessonwork.setClasshours((double) lessonwork.getPclasshours());
                else {
                    lessonwork.setClasshours(calculateClasshours(pnum, lessonwork.getPclasshours()));
                }
            } else {//合班课
                String[] pnums = lessonwork.getPart().split("/");
                for (int i = 0; i < pnums.length; i++) {
                    System.out.println("pnums[" + i + "]:" + pnums[i]);
                    pnum += Integer.parseInt(pnums[i]);
                    System.out.println("pnum:" + pnum);
                }
                lessonwork.setClasshours(calculateClasshours(pnum, lessonwork.getPclasshours()));
            }
        }

        lessonworkService.insertLessonwork(lessonwork);
        return new ResultData(1);


    }
    @RequestMapping("/admin/deleteLessonworkById.action")
    @ResponseBody
    ResultData deleteLessonworkById(Integer id) {

        if(id==null||id<=0)
            return new ResultData(23);
        lessonworkService.deleteLessonworkById(id);
        return new ResultData(1);

    }


    /*
    * 计算计划学时
    * */
    Double calculateClasshours(Integer pnum, Integer pclasshours) {
        Double classhours = 0.0;
        if (pnum <= 50)
            classhours = pclasshours * Coe.up40_50;
        else if (pnum <= 60)
            classhours = pclasshours * Coe.up51_60;
        else if (pnum <= 70)
            classhours = pclasshours * Coe.up61_70;
        else if (pnum <= 80)
            classhours = pclasshours * Coe.up71_80;
        else if (pnum <= 90)
            classhours = pclasshours * Coe.up81_90;
        else if (pnum <= 100)
            classhours = pclasshours * Coe.up91_100;
        else if (pnum <= 110)
            classhours = pclasshours * Coe.up101_110;
        else
            classhours = pclasshours * Coe.up111;
        return classhours;
    }
}
