package com.xjw.demo.Controllers.KQApply;

import com.xjw.demo.Dao.ExApply;
import com.xjw.demo.Services.KQApply.KQApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/applyPage")
public class KQApplyController {

    @Autowired
    public KQApplyService kqApplyService;

    @ResponseBody
    @PostMapping(value = "/api/postApply", produces  = "application/json;charset=UTF-8")
    public String postApply(@RequestBody Map<String, Map<String, Object>> map){
        ExApply apply = new ExApply();

        apply.setApplyTitle((map.get("apply").get("applyTitle").toString()));
        apply.setReason((map.get("apply").get("reason").toString()));
        apply.setStartTime((map.get("apply").get("startTime").toString()));
        apply.setEndTime((map.get("apply").get("endTime").toString()));
        apply.setTips((map.get("apply").get("tips").toString()));
        apply.setApplyType(Integer.valueOf((map.get("apply").get("applyType").toString())));
        apply.setUserId(Integer.valueOf(map.get("apply").get("userId").toString()));
        apply.setStation(0);

        System.out.println(apply);

        try{
            kqApplyService.updateApply(apply);
            /*if(apply.getApplyType()==1){

            }*/
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/getApplyList", method = RequestMethod.POST)
    public List<ExApply> getApplyList(@RequestParam Integer userId){
        return kqApplyService.getApplyList(userId);
    }

}
