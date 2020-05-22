package com.xjw.demo.Services.KQHomePage;

import com.xjw.demo.Dao.Attendance;
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
public class KQHomePageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    //获得新消息
//    public List<Message> getMessages(Integer userId){
//        List<Message> list = new ArrayList<>();
//        list = jdbcTemplate.query("select top 10 * from msg where userId = "+userId, new BeanPropertyRowMapper(Message.class));
//        return list;
//    }

    //获得考勤记录
    public List<Attendance> getAtt(Integer userId){
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM kq.att where attMemberId = "+userId+" ORDER BY attId DESC LIMIT 10;";
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Attendance.class));
        return list;
    }

    //获得个人信息
    public User getUser(Integer userId){
        User user = new User();
        user = jdbcTemplate.queryForObject("select * from myuser where userId="+userId, new UserRowMapper());
        return user;
    }

    //是否有新信息
    public Integer hasNewMsg(Integer userId){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM kq.message where memberId = ? and hasRead = 0",
                int.class, userId);
//        System.out.println(i);
        return i;
    }




}
