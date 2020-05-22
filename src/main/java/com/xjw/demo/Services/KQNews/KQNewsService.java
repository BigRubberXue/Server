package com.xjw.demo.Services.KQNews;

import com.xjw.demo.Dao.Message;
import com.xjw.demo.Mapper.MessageRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class KQNewsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获得新消息列表
    public List<Message> getMsgList(Integer userId){
        List<Message> list = new ArrayList<>();
        list = jdbcTemplate.query("select * from kq.message where memberId = "+userId+" ORDER BY msgId DESC", new BeanPropertyRowMapper(Message.class));
        return list;
    }

    //获得新消息
    public Message getMsg(Integer msgId){
        String s = "select * from kq.message WHERE msgId = "+msgId;
        Message msg = jdbcTemplate.queryForObject(s, new MessageRowMapper());
        return msg;
    }

    //新消息已读
    public String hasReadMsg(Integer msgId){
        System.out.println(msgId);
        jdbcTemplate.update("UPDATE `kq`.`message` SET `hasRead` = '1' WHERE (`msgId` = ?);"
                ,msgId);
        return "success";
    }


}
