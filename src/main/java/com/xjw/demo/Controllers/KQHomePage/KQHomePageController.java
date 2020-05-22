package com.xjw.demo.Controllers.KQHomePage;

import com.xjw.demo.Dao.Attendance;
import com.xjw.demo.Dao.Message;
import com.xjw.demo.Dao.User;

import com.xjw.demo.Services.KQHomePage.KQHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homePage")
public class KQHomePageController {

    @Autowired
    public User user;

    @Autowired
    public KQHomePageService kqHomePageService;

//    @ResponseBody
//    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
//    public List<Message> getMsg(@RequestParam Integer userId){
//        List<Message> list = kqHomePageService.getMessages(userId);
//        //System.out.println(list);
//        return list;
//    }

    @ResponseBody
    @RequestMapping(value = "/getAtt", method = RequestMethod.GET)
    public List<Attendance> getAtt(@RequestParam Integer userId){
        List<Attendance> list = kqHomePageService.getAtt(userId);
//        System.out.println(list);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/api/postInfo", method = RequestMethod.POST)
    public User post(@RequestParam Integer userId){
        //produces = "application/json; charset=UTF-8"
        User user = kqHomePageService.getUser(userId);
        //System.out.println(list);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/api/hasNewMsg", method = RequestMethod.POST)
    public Integer hasNewMsg(@RequestParam Integer userId){
        //produces = "application/json; charset=UTF-8"
        int i = kqHomePageService.hasNewMsg(userId);
        return i;
    }





}
