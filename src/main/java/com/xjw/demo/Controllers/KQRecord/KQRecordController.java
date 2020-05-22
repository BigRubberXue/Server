package com.xjw.demo.Controllers.KQRecord;

import com.xjw.demo.Dao.AttData;
import com.xjw.demo.Dao.Attendance;
import com.xjw.demo.Dao.MouthData;
import com.xjw.demo.Dao.User;
import com.xjw.demo.Services.KQRecord.KQRecordService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recordPage")
public class KQRecordController {

    @Autowired
    public User user;

    @Autowired
    public KQRecordService kqRecordService;


    //获得签到表
    @ResponseBody
    @RequestMapping(value = "/api/postAtt",method = RequestMethod.POST)
    public List<Attendance> postAtt(@RequestParam Integer userId){
        List<Attendance> list = kqRecordService.getAtt(userId);
        System.out.println(list);
        return list;
    }


    //获得考勤综合表
    @ResponseBody
    @RequestMapping(value = "/api/attDataSum", method = RequestMethod.POST)
    public List<MouthData> attDataSum(@RequestParam Integer userId){
        System.out.println("test");
        List<MouthData> list = kqRecordService.getAttDataSum(userId);
        return list;
    }


    @ResponseBody
//    @RequestMapping(value = "/api/signIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/api/signIn", produces  = "application/json;charset=UTF-8")
    public String signInData(@RequestBody Map<String, Map<String, Object>> map){
//
//        System.out.println(att.get("att"));
//        System.out.println(att.get("att").get("attMember"));
        if(map == null){
            return "fail: form error";
        }
//        System.out.println(map);
        Attendance att = new Attendance();

        att.setAttMemberId(Integer.valueOf(map.get("att").get("attMemberId").toString()));
        att.setAttMember(map.get("att").get("attMember").toString());
        att.setAttStatus(map.get("att").get("attStatus").toString());
        att.setAttTime(map.get("att").get("attTime").toString());
//        System.out.println(att);
//        produces = "application/json; charset=UTF-8"
//        System.out.println(list);
        return kqRecordService.signInData(att);
//        return "success";
    }

    @ResponseBody
//    @RequestMapping(value = "/api/signIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/api/checkSign", produces  = "application/json;charset=UTF-8")
    public String checkSigned(@RequestParam Integer userId, @RequestParam String data){
        if(userId == null || data == null){
            return "fail : map is no exist";
        }
        System.out.println("isSigned");
        return kqRecordService.checkSigned(userId, data);
    }






}
