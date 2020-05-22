package com.xjw.demo.Controllers.KQInfo;


import com.xjw.demo.Dao.User;
import com.xjw.demo.Services.KQInfo.KQInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/infoPage")
public class KQInfoController {


    @Autowired
    public KQInfoService kqInfoService;

    /*
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String signup_get(Model model){
        //个人信息
        return "#";
    }*/

    @ResponseBody
    @RequestMapping(value = "/api/postInfo", method = RequestMethod.POST)
    public User loadUser(@RequestParam Integer userId){
        //produces = "application/json; charset=UTF-8"
        User user = kqInfoService.getUser(userId);
        //System.out.println(list);
//        System.out.println(user);
        return user;
    }

    @ResponseBody
    @PostMapping(value = "/api/changeInfo", produces  = "application/json;charset=UTF-8")
    public String updateInfo(@RequestBody Map<String, Map<String, Object>> map){
//        System.out.println(map);
        if(map == null){
            return "fail";
        }
        /*User user = kqInfoService.getUser(Integer.valueOf(data.getUserId()));
        user.setPassword(isNullString(req.getParameter("password")) ? user.getName() : req.getParameter("password"));
        user.setName(isNullString(req.getParameter("name")) ? user.getName() : req.getParameter("name"));
        user.setStation(isNullString(req.getParameter("station")) ? user.getStation() : req.getParameter("station"));
        user.setPhone(isNullString(req.getParameter("phone")) ? user.getPhone() : req.getParameter("phone"));
        user.setMail(isNullString(req.getParameter("mail")) ? user.getMail() : req.getParameter("mail"));*/
        User user = new User();
        user.setUserId(Integer.valueOf(map.get("user").get("userId").toString()));
        user.setUsername(map.get("user").get("username").toString());
        user.setPassword(map.get("user").get("password").toString());
        user.setName(map.get("user").get("name").toString());
        System.out.println(user.getName());
        user.setStation(map.get("user").get("station").toString());
        user.setPhone(map.get("user").get("phone").toString());
        user.setMail(map.get("user").get("mail").toString());

        kqInfoService.updateInfo(user);
        return "success";
    }

    public boolean isNullString(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }

}
