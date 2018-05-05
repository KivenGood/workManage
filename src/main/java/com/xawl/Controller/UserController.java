package com.xawl.Controller;

import com.github.pagehelper.PageInfo;
import com.xawl.Pojo.UserList;
import com.xawl.Pojo.*;
import com.xawl.Service.UserService;
import com.xawl.Vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by doter on 2017/7/14.
 */
@Controller
public class UserController {
    @Resource
    UserService userService;

    /**
     * 登录接口
     */
    @RequestMapping("/login.action")
    @ResponseBody
    ResultData login(User user, HttpSession session) throws Exception {
        if (user == null)
            return new ResultData(23);
        if (user.getPass() == null || user.getTechno() == null)
            return new ResultData(26);
        User user1 = new User();
        user1.setTechno(user.getTechno());
        List<User> userList=userService.getUser(user1);
        if (userList == null||userList.size() <=0 )
            return new ResultData(-19, "user is not exist");
       User user2 =userList.get(0);
        if (user.getPass().equals(user2.getPass())) {
            session.setAttribute("uid", user2.getId());
          /*  System.out.println("techno:"+user2.getTechno());
            System.out.println("user2.getPass:"+user2.getPass());*/
            //session.setAttribute("level", user2.getLevel());
            session.setAttribute("uname", user2.getName());
            session.setAttribute("type", user2.getType());
           // System.out.println("level"+session.getAttribute("level"));
            System.out.println("uname"+session.getAttribute("uname"));
            System.out.println("type"+session.getAttribute("type"));
            String data = null;
            if (user.getTechno().equals(user.getPass())) {
                data = "pass is init";
                //       System.out.println("pass is init");
            }
            return new ResultData(user2.getType(), data,user2.getName());
        } else return new ResultData(-20, "pass is wrong");
    }

    @RequestMapping("/user/updatePass.action")
    @ResponseBody
    ResultData updatePass(HttpSession session, String pass) {
        Integer uid = (Integer) session.getAttribute("uid");
        if (pass == null || pass == "")
            return new ResultData(23);
        User user = new User();
        user.setId(uid);
        user.setPass(pass);
        userService.updateUserById(user);
        return new ResultData(1);
    }

    @RequestMapping("/user/exit.action")
    @ResponseBody
    ResultData exit(HttpSession session) {
        session.setAttribute("uid", null);
        session.setAttribute("type", null);
        return new ResultData(1);

    }
    @RequestMapping("/user/getUserInfo.action")
    @ResponseBody
    ResultData getUserInfo(HttpSession session){
        User user=new User();
        user.setName((String)session.getAttribute("uname"));
        user.setType((Integer) session.getAttribute("type"));
        return new ResultData(1,user);
    }
    @RequestMapping("/root/insertAdmin.action")
    @ResponseBody
    ResultData insertAdmin(User user) {
        if (user == null)
            return new ResultData(23);
        if (user.getTechno() == null && user.getTechno() == "")
            return new ResultData(23, "techno is null");
        if (user.getName() == null && user.getName() == "")
            return new ResultData(23, "name is null");
        user.setPass(user.getTechno());
        user.setType(2);
        user.setLevel("Admin");
        user.setSdept("Admin");
        user.setStarteddate(new Timestamp(new Date().getTime()));
        userService.insertUser(user);
        return new ResultData(1);


    }
    @RequestMapping("/admin/batchUsersExcl.action")
    @ResponseBody
    ResultData batchUsersExcl(String path) throws IOException {
        userService.batchUsersExcl(path);
        return new ResultData(1);

    }
    @RequestMapping("/admin/batchUsers.action")
    @ResponseBody
    ResultData batchUsers(UserList userList) {
        if (userList == null)
            return new ResultData(23);
       // User user = new User();
      //  List<User> list = userList.getList();
        List<User> userList1=(List<User>)userList.getList();
        return new ResultData(1, userService.batchUsers(userList1));
    }

    @RequestMapping("/admin/SelectUsers.action")
    @ResponseBody
    ResultData SelectUsers() {
        List<User> userList = new ArrayList();
        User user = new User();
        user.setType(1);
      //  userList = ;
     /*   for (int i = 0; i < userList.size(); i++)
            userList.get(i).setPass(null);*/
       // PageInfo page = new PageInfo(userList);
        return new ResultData(1, userService.getUser(user));
    }

    @RequestMapping("/admin/delectUserById.action")
    @ResponseBody
    ResultData delectUserById(Integer id) {
        if (id == null)
            return new ResultData(23);
        userService.deleteUser(id);

        return new ResultData(1);
    }


}
