package com.xjw.demo.Services.KQApply;

import com.xjw.demo.Dao.Attendance;
import com.xjw.demo.Dao.ExApply;
import com.xjw.demo.Dao.Message;
import com.xjw.demo.Dao.User;
import com.xjw.demo.Mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class KQApplyService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    //申请提交
    public void updateApply(ExApply exApply){
        jdbcTemplate.update("INSERT INTO  apply  ( applyTitle, tips, reason, startTime, endTime, applyType, userId, station)  VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                , exApply.getApplyTitle(), exApply.getTips(), exApply.getReason(), exApply.getStartTime(), exApply.getEndTime(), exApply.getApplyType(), exApply.getUserId(), exApply.getStation());
    }
    //UPDATE apply SET applyId = ?, applyTitle = ?, tips = ?, reason = ?, startTime = ?, endTime = ?, applyType =  WHERE (applyId = ?)
    //INSERT INTO  apply  ( applyTitle, tips, reason, startTime, endTime, applyType)  VALUES (?, ?, ?, ?, ?, ?);


    public List<ExApply> getApplyList(Integer userId) {
        List<ExApply> list = new ArrayList<>();
        list = jdbcTemplate.query("SELECT * FROM apply where userId = "+userId+" ORDER BY applyId DESC",new BeanPropertyRowMapper(ExApply.class));
        System.out.println("service = "+list);
        return list;
    }
}
