package com.xjw.demo.Controllers.KQNews;

import com.xjw.demo.Dao.Message;
import com.xjw.demo.Dao.User;
import com.xjw.demo.Services.KQNews.KQNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/newsPage")
public class KQNewsController {

    @Autowired
    public User user;

    @Autowired
    public KQNewsService kqNewsService;


    @ResponseBody
    @RequestMapping(value = "/api/postMsgList", method = RequestMethod.POST)
    public List<Message> postMsgList(@RequestParam Integer userId){
        List<Message> list = kqNewsService.getMsgList(userId);
        //System.out.println(list);
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/api/postMsg", method = RequestMethod.POST)
    public Message postMsg(@RequestParam Integer msgId){
        //System.out.println(list);
        return kqNewsService.getMsg(msgId);
    }


    @ResponseBody
    @RequestMapping(value = "/api/hasRead", method = RequestMethod.POST)
    public String hasRead(@RequestParam Integer msgId){
        //System.out.println(list);
        return kqNewsService.hasReadMsg(msgId);
    }


}
