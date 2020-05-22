package com.xjw.demo.Controllers.KQAdmin;

import com.xjw.demo.Dao.*;
import com.xjw.demo.Services.KQAdmin.KQAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class KQAdminController {

    @Autowired
    public KQAdminService kqAdminService;


/*    //显示页面
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String indexPage(Model model){
        //admin页面
        return "#";
    }*/

    //添加用户
    @ResponseBody
    @RequestMapping(value = "/api/addMember", method = RequestMethod.POST)
    public String addMember(@RequestParam String username, @RequestParam String password){
        return kqAdminService.addNewUser(username, password);
    }

    //删除用户
    @ResponseBody
    @RequestMapping(value = "/api/deleteMember", method = RequestMethod.POST)
    public String deleteMember(@RequestParam String username, @RequestParam String adminPas){

        return kqAdminService.deleteUser(username, adminPas);
    }

    //同意申请
    @ResponseBody
    @RequestMapping(value = "/api/agreeApply", method = RequestMethod.POST)
    public String agreeApply(@RequestParam Integer applyId, @RequestParam String msgTime, @RequestParam Integer userId){
        return kqAdminService.agreeApply(applyId, msgTime, userId);
    }

    //拒绝申请
    @ResponseBody
    @RequestMapping(value = "/api/disagreeApply", method = RequestMethod.POST)
    public String disagreeApply(@RequestParam Integer applyId){
        return kqAdminService.disagreeApply(applyId);
    }

    //发送消息
    @ResponseBody
    @RequestMapping(value = "/api/sendMsg", method = RequestMethod.POST)
    public String sendMsg(@RequestParam String msg, @RequestParam String msgTitle, @RequestParam String msgTime){
        return kqAdminService.sendMsg(msgTitle, msg, msgTime);
    }

    //提交今日考勤
    @ResponseBody
    @RequestMapping(value = "/api/subToday", method = RequestMethod.POST)
    public String subToday(@RequestParam String date){
        Integer sumMember = kqAdminService.sumMember();//总人数
        Integer signedMember = kqAdminService.sumSignedMember(date); //已签到人数
        if(sumMember < signedMember){
            return "fail";
        }
        if(kqAdminService.hasSubAtt(date)){
            System.out.println("update");
            return kqAdminService.updateToday(date, sumMember-signedMember, signedMember);
        }
        System.out.println("insert");
        return kqAdminService.subToday(date, sumMember-signedMember, signedMember);
    }

    //更新优秀员工
    @ResponseBody
    @RequestMapping(value = "/api/updateExPerson", method = RequestMethod.POST)
    public String updateExPerson(@RequestParam String date){
        return kqAdminService.updateExPerson(date);
    }



    ////获取数据
    //获取记录表的
    @ResponseBody
    @RequestMapping(value = "/api/postAtt", method = RequestMethod.POST)
    public List<Attendance> postAtt(){
        List<Attendance> list = kqAdminService.getAtt();
        //System.out.println(list);
        return list;
    }

    //获取综合记录表的
    @ResponseBody
    @RequestMapping(value = "/api/getAttSum", method = RequestMethod.POST)
    public List<AttData> postAttSum(){
        List<AttData> list = kqAdminService.getAttSum();
        //System.out.println(list);
        return list;
    }

    //获取员工表
    @ResponseBody
    @RequestMapping(value = "/api/getUserList", method = RequestMethod.POST)
    public List<User> postUserList(){
        List<User> list = kqAdminService.getUserList();
        //System.out.println(list);
        return list;
    }

    //优秀员工表
    @ResponseBody
    @RequestMapping(value = "/api/postExPerson", method = RequestMethod.POST)
    public List<ExPerson> postExPerson(){
        List<ExPerson> list = kqAdminService.getExPerson();
        System.out.println("controller="+list);
        return list;
    }

    //获取申请表
    @ResponseBody
    @RequestMapping(value = "/api/postApply", method = RequestMethod.POST)
    public List<ExApply> postApply(){
        List<ExApply> list = kqAdminService.getApply();
        //System.out.println(list);
        return list;
    }

    //获取签到人数
    @ResponseBody
    @RequestMapping(value = "/api/getSumDataMap", method = RequestMethod.POST)
    public Map<String, Object> getSum(@RequestParam String date){
        Map<String, Object> map = new HashMap<>();
        map.put("sumMember",kqAdminService.sumMember());
        map.put("signedMember", kqAdminService.sumSignedMember(date));
        //System.out.println(list);
        return map;
    }




    public boolean isNullString(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }
}
