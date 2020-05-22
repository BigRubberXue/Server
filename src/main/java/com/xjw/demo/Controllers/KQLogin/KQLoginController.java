package com.xjw.demo.Controllers.KQLogin;

import com.xjw.demo.Dao.User;
import com.xjw.demo.Services.KQLogin.KQLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sign")
public class KQLoginController {

    @Autowired
    public KQLoginService kqLoginService;


    @ResponseBody
    @RequestMapping(value = "/api/checkToSign", method = RequestMethod.POST)
    public User signin_post(@RequestParam String username, @RequestParam String password){
        //登陆
        /*if((user = kqLoginService.checkUserExistAndGet(username, password)) != null){
            System.out.println("success");
            System.out.println("user :"+user);
            System.out.println("kqLoginService :"+kqLoginService);
            return user;
        }else{
            //账号不存在或密码不正确
            System.out.println("fail");
            System.out.println("user :"+user);
            System.out.println("kqLoginService :"+kqLoginService);
            return null;
        }*/
        User user = kqLoginService.checkUserExistAndGet(username, password);
        System.out.println("test");
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/api/adminLogin", method = RequestMethod.POST)
    public String adminLogin(@RequestParam String password){
        System.out.println(password);
        return kqLoginService.checkAdminPassword(password);
    }



}
