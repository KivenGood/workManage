package com.xawl.Controller;

import com.github.pagehelper.PageInfo;
import com.xawl.Pojo.Coe;
import com.xawl.Pojo.Testwork;
import com.xawl.Service.TestworkService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class TestworkController {
    @Resource
    TestworkService testworkService;

    @RequestMapping("/user/insertTestworkJnum.action")
    @ResponseBody
    ResultData insertTestworkJnum(HttpSession session, Testwork testwork) {
        if (testwork == null || testwork.getJnum() == null||testwork.getTerm()==null)
            return new ResultData(23);
        Testwork testwork1 = new Testwork();
        testwork1.setUid((Integer) session.getAttribute("uid"));
        //System.out.println("uid:"+testwork1.getUid());
        testwork1.setTerm(testwork.getTerm());
        testwork.setUid(testwork1.getUid());
        testwork.setJpclass(testwork.getJnum() * Coe.invigilate);
        System.out.println();
        testwork.setPclassNum(testwork.getJpclass());
        testwork.setPass(0);
        testwork.setStatedDate(new Timestamp(new Date().getTime()));
        List<Testwork> testworkList = testworkService.getTestwork(testwork1);//查询此用户之前是否插入过考试工作量的数据,在同一学期
        if (testworkList != null && testworkList.size() > 0 && testworkList.get(0).getPass() != 1) {
            if (testworkList.get(0).getJnum() != null && testworkList.get(0).getJnum() > 0)
                return new ResultData(24, "existed");
            else {//如果已存在课程名插入就得更新总学时了
                testwork.setId(testworkList.get(0).getId());
                testwork.setPclassNum(testwork.getJpclass() + testworkList.get(0).getPclassNum());
                testworkService.updateTestworkById(testwork);
                System.out.println("uid1:" + testwork.getUid());
                System.out.println("getJnum1:" + testwork.getJnum());
                System.out.println("getJpclass1:" + testwork.getJpclass());
                System.out.println("getPclassNum1:" + testwork.getPclassNum());
                testworkService.updateTestworkById(testwork);
                return new ResultData(1);
            }
        }
        System.out.println("uid:" + testwork.getUid());
        System.out.println("getJnum:" + testwork.getJnum());
        System.out.println("getJpclass:" + testwork.getJpclass());
        System.out.println("getPclassNum:" + testwork.getPclassNum());
        testworkService.insertTestwork(testwork);
        return new ResultData(1);

    }

    @RequestMapping("/user/insertTestwork.action")
    @ResponseBody
    ResultData insertTestwork(HttpSession session, Testwork testwork) {
        if (testwork == null)
            return new ResultData(23);
        if (testwork.getLname() == null || testwork.getLname() == "")
            return new ResultData(26, "课程名字为空");
        if (testwork.getTerm() == null || testwork.getTerm() <= 0)
            return new ResultData(23, "Term is null or worong");
        testwork.setPass(0);//pass设置为未审核
        testwork.setUid((Integer) session.getAttribute("uid"));
        System.out.println("uid:" + testwork.getUid());
        testwork.setStatedDate(new Timestamp(new Date().getTime()));
        //计算标准课时
        if (testwork.getMpclass() != null && testwork.getMpclass() > 0) {
            testwork.setMpclass(Coe.testPaper);
        }
        if (testwork.getQpclass() != null && testwork.getQpclass() > 0) {
            testwork.setQpclass(Coe.testPaper);
        }
        System.out.println("testWork.getMpclass:" + testwork.getMpclass());
        System.out.println("testwork.getQpclass():" + testwork.getQpclass());
        testwork.setPaperSum(testwork.getBpaperNum() + testwork.getCpaperNum() + testwork.getQpaperNum() + testwork.getPaperNum());
        testwork.setPaperPclass(testwork.getPaperSum() * Coe.inspectTest);
        testwork.setPclassNum(testwork.getMpclass() + testwork.getQpclass() + testwork.getPaperPclass());

        Testwork testwork1 = new Testwork();
        testwork1.setUid(testwork.getUid());
        testwork1.setTerm(testwork.getTerm());
        List<Testwork> testworksList = testworkService.getTestwork(testwork1);
        if (testworksList != null && testworksList.size() > 0) {//详情见数据库设计中的附1
            System.out.println("0000:" + testworksList);
            if (testworksList.get(0).getLname() == null || testworksList.get(0).getLname() == "") {
                testwork.setPclassNum(testwork.getPclassNum() + testworksList.get(0).getJpclass());
                testwork.setId(testworksList.get(0).getId());
                testworkService.updateTestworkById(testwork);
                return new ResultData(1);
            } else {
                System.out.println("1.5 1.5 1.5");
                System.out.println("testwork.getLname():" + testwork.getLname());
                System.out.println("testworksList.get(0).getPass():" + testworksList.get(0).getPass());
                if (testwork.getLname() == testworksList.get(0).getLname() || testwork.getLname().equals(testworksList.get(0).getLname())) {
                    System.out.println("1111");
                    if (testworksList.get(0).getPass() != 1) {
                        System.out.println("222");
                        return new ResultData(24, "existed");
                    }
                    System.out.println("333");
                }
            }
        }
        System.out.println("阅卷总份数:" + testwork.getPaperSum());
        System.out.println("阅卷总课时:" + testwork.getPaperPclass());
        System.out.println("总课时：" + testwork.getPclassNum());
        testworkService.insertTestwork(testwork);
        return new ResultData(1);
    }


    @RequestMapping("/user/updateTestworkById.action")
    @ResponseBody
    ResultData updateTestworkById(Testwork testwork) {
        if (testwork == null || testwork.getId() == null)
            return new ResultData(23);
        Double pclassNum = 0.0;
        Integer paperSum = 0;
    /*    if ((testwork.getJnum() != null && testwork.getJnum() > 0)
                || (testwork.getQpaperNum() != null && testwork.getQpaperNum() > 0)
                || (testwork.getCpaperNum() != null && testwork.getCpaperNum() > 0)
                || (testwork.getBpaperNum() != null && testwork.getBpaperNum() > 0)
                || (testwork.getPaperNum() != null && testwork.getPaperNum() > 0)
                || (testwork.getMpclass() != null && testwork.getMpclass() > 0)
                || (testwork.getQpclass() != null && testwork.getQpclass() > 0))*/
        //不加这句判断语句，只改名字总课时汇总时会报空指针异常
        if (testwork.getJnum() != null && testwork.getJnum() >= 0) {
            testwork.setJpclass(testwork.getJnum() * Coe.invigilate);
            pclassNum += testwork.getJpclass();
            System.out.println("Jpclass：" + testwork.getJpclass());
            System.out.println("pclassNum：" + pclassNum);
        }
        if (testwork.getMpclass() != null && testwork.getMpclass() > 0) {
            testwork.setMpclass(Coe.testPaper);
            pclassNum += testwork.getMpclass();
            System.out.println("pclassNum：" + pclassNum);

        }
        if (testwork.getQpclass() != null && testwork.getQpclass() > 0) {
            testwork.setQpclass(Coe.testPaper);
            pclassNum += testwork.getQpclass();
            System.out.println("pclassNum：" + pclassNum);

        }
        if (testwork.getQpaperNum() != null && testwork.getQpaperNum() > 0) {
            paperSum += testwork.getQpaperNum();
        }
        if (testwork.getCpaperNum() != null && testwork.getCpaperNum() > 0) {
            paperSum += testwork.getCpaperNum();
        }
        if (testwork.getBpaperNum() != null && testwork.getBpaperNum() > 0) {
            paperSum += testwork.getBpaperNum();
        }
        if (testwork.getPaperNum() != null && testwork.getPaperNum() > 0) {
            paperSum += testwork.getPaperNum();
        }
        if (testwork.getJnum() != null && testwork.getJnum() > 0){//因为传的数必须都传，所以任取一个即可
            testwork.setPaperSum(paperSum);
            testwork.setPaperPclass(testwork.getPaperSum() * Coe.inspectTest);
            testwork.setPclassNum(pclassNum + testwork.getPaperPclass());
            System.out.println("总阅卷数：" + testwork.getPaperSum());
            System.out.println("总阅卷课时：" + testwork.getPaperPclass());
            System.out.println("总课时：" + testwork.getPclassNum());
        }


        testworkService.updateTestworkById(testwork);
        return new

                ResultData(1);

    }

    @RequestMapping("/user/getTestwork.action")
    @ResponseBody
    ResultData getTestwork(Testwork testwork, HttpSession session) {
        if((Integer) session.getAttribute("type")==1)
            testwork.setUid((Integer) session.getAttribute("uid"));
        //PageInfo page = new PageInfo(testworkService.getTestwork(testwork));
        return new ResultData(1,testworkService.getTestwork(testwork));
    }

    @RequestMapping("/admin/deleteTestworkById.action")
    @ResponseBody
    ResultData delectTestwork(Integer id) {
        if (id == null || id <= 0) {
            return new ResultData(23);
        }
        testworkService.deleteTestworkById(id);
        return new ResultData(1);

    }

    @RequestMapping("/admin/exportTestwork.action")
    @ResponseBody
    ResultData exportTestwork(HttpServletRequest request,
                              Testwork testwork) {
        if(testwork.getTerm()==null||testwork.getTerm()<=0){
            return new ResultData(23);
        }

        return new ResultData(1,  testworkService.exportTestwork(request,testwork));
    }
    @RequestMapping("/admin/TestworkUUser.action")//考试工作量未未提交用户
    @ResponseBody
    ResultData TestworkUUser() {
       return new ResultData(1,  testworkService.TestworkUUser());
    }
}

