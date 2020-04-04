package com.zyj.wechartexam.webCtr.controller;

import com.zyj.wechartexam.webCtr.bean.Users;
import com.zyj.wechartexam.webCtr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @PostMapping(value = "/user/login")
    public String login(Users user, Map<String,Object> map, HttpSession session){
        logger.info(user.toString());
        List<Users> reuser=userService.selectUser(user);
        logger.info("登录结果"+reuser);
        if(user.getUserName()==""||user.getUserPassword()==""||user.getUserNumber()==""){
            map.put("msg","用户信息不能为空，请重新填写");
            return "login";
        }
        Users resultUser=reuser.get(0);
        if(resultUser!=null && user.getUserPassword()!=null && user.getUserPassword().equals(resultUser.getUserPassword()) && user.getUserName().equals(resultUser.getUserName())){
           //防止表单重复提交可以重定向到主页
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logOut(){
        return "redirect:/";
    }
}
