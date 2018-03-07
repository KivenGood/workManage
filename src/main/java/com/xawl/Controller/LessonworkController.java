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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class LessonworkController {
    @Resource
    LessonworkService lessonworkService;
    @Resource
    DclassService dclassService;

    @RequestMapping("/user/getLessonwork.action")
    @ResponseBody
    ResultData getLessonwork(Lessonwork lessonwork, HttpSession session) {
        if (lessonwork.getUid()==null)
            lessonwork.setUid((Integer) session.getAttribute("uid"));
        return new ResultData(1, lessonworkService.getLessonwork(lessonwork));

    }

    @RequestMapping("/user/insertLesswork.action")
    @ResponseBody
    ResultData insertLesswork(Lessonwork lessonwork, HttpSession session) {
        if (lessonwork == null)
            return new ResultData(23);
        lessonwork.setUid((Integer) session.getAttribute("uid"));
        //查询是否已经插入过
        System.out.println("lessonwork1.getType():"+lessonwork.getType());
        Lessonwork lessonwork1=new Lessonwork();
        lessonwork1.setUid(lessonwork.getUid());
        lessonwork1.setType(lessonwork.getType());
        lessonwork1.setCid(lessonwork.getCid());
        lessonwork1.setPart(lessonwork.getPart());

        List<Lessonwork> lessonworksList= lessonworkService.getLessonwork(lessonwork1);
        if(lessonworksList!=null&&lessonworksList.size()>0)
            return new ResultData(24,"existed");

        if (lessonwork.getCid() == null || lessonwork.getCid() <= 0)
            return new ResultData(23, "Cid is null or worong");
        if (lessonwork.getLname() == null || lessonwork.getLname() == "")
            return new ResultData(23, "Lname is null");
        if (lessonwork.getPclasshours() == null || lessonwork.getPclasshours() <= 0)
            return new ResultData(23, "Pclasshours is null or worong");
        if (lessonwork.getType() == null || lessonwork.getType() <= 0)
            return new ResultData(23, "Type is null or worong");
        if (lessonwork.getClasshours() == null || lessonwork.getClasshours() <= 0)
            return new ResultData(23, "Classhours is null or worong");

        lessonwork.setPass(0);
        lessonwork.setStarteddate(new Timestamp(new Date().getTime()));
        Dclass dclass = new Dclass();
        dclass.setId(lessonwork.getCid());
        Integer pnum = dclassService.getDclass(dclass).get(0).getPnum();//班级的人数
        System.out.println("pnum3"+pnum);
        //实验课,实验课没有合班课
        if (lessonwork.getType() == 3) {
            lessonwork.setClasshours(lessonwork.getPclasshours() * (double) pnum * Coe.testClass);
            lessonwork.setCoe(Coe.testClass);
        }
        else
        {

    //    lessonwork.setClasshours(calculateClasshours(pnum,lessonwork.getPart()));
        lessonwork.setCoe(calculateClasshours(pnum, lessonwork.getPart()));
        lessonwork.setClasshours(lessonwork.getCoe()*lessonwork.getPclasshours());}
  /*      if (lessonwork.getType() == 2 || lessonwork.getType() == 1) {
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
                lessonwork.setClasshours(calculateClasshours(pnum, lessonwork.getPclasshours(),lessonwork.getPart()));
            }
        }*/

        lessonworkService.insertLessonwork(lessonwork);
        return new ResultData(1);


    }
    @RequestMapping("/user/updateLessworkById.action")
    @ResponseBody
    ResultData updateTestworkById(Lessonwork lessonwork){
        if(lessonwork==null||lessonwork.getId()==null)
            return new ResultData(23);
        if(lessonwork.getCid()!=null||lessonwork.getPart()!=null||lessonwork.getPclasshours()!=null)
        {
            Dclass dclass = new Dclass();
            dclass.setId(lessonwork.getCid());

            Integer pnum = dclassService.getDclass(dclass).get(0).getPnum();//班级的人数
            if (lessonwork.getType() == 3) {
                lessonwork.setClasshours(lessonwork.getPclasshours() * (double) pnum * Coe.testClass);
                lessonwork.setCoe(Coe.testClass);
            }
            else
            {
            lessonwork.setCoe(calculateClasshours(pnum, lessonwork.getPart()));
            lessonwork.setClasshours(lessonwork.getCoe()*lessonwork.getPclasshours());}

        }
        lessonworkService.updateLessonworkById(lessonwork);
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
    * 计算计划学时,先进行合班和拆班的区分
    * */
    Double calculateClasshours(Integer pnum, String part) {
        //若不是合班课
        Double coe = 0.0;
        if (part == null || part == "") {
            if (pnum <= 50)
                coe = 1.0;
            else {
                coe = compute(pnum);
            }
        } else {//合班课
            String[] pnums = part.split("/");
            for (int i = 0; i < pnums.length; i++) {
                System.out.println("pnums[" + i + "]:" + pnums[i]);
                pnum += Integer.parseInt(pnums[i]);
                System.out.println("pnum:" + pnum);
            }
            coe = compute(pnum);
        }
        return coe;
    }
    /*
     * 具体计算计划学时
     * */
    Double compute(Integer pnum
            /*, Integer pclasshours*/
            ) {
        /*Double classhours = 0.0;*/
        Double coe=0.0;
            if (pnum <= 50)
                coe=Coe.up40_50;
                //classhours = pclasshours *coe ;
            else if (pnum <= 60)
                coe= Coe.up51_60;
            else if (pnum <= 70)
                coe= Coe.up61_70;
            else if (pnum <= 80)
                coe= Coe.up71_80;
            else if (pnum <= 90)
                coe= Coe.up81_90;
            else if (pnum <= 100)
                coe= Coe.up91_100;
            else if (pnum <= 110)
                coe= Coe.up101_110;
            else
                coe= Coe.up111;
            return coe;
        }

}