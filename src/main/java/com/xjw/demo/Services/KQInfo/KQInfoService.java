package com.xjw.demo.Services.KQInfo;

import com.xjw.demo.Dao.User;
import com.xjw.demo.Mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class KQInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获得个人信息
    public User getUser(Integer userId){
        User user = new User();
        user = jdbcTemplate.queryForObject("select * from myuser where userId="+userId, new UserRowMapper());
        return user;
    }

    //修改个人信息
    public void updateInfo(User user){
        jdbcTemplate.update("UPDATE myuser SET userName = ?, password = ?, name = ?, station = ?, phone = ?, mail = ? WHERE (userId = ?)"
                , user.getUsername(), user.getPassword(), user.getName(), user.getStation(), user.getPhone(), user.getMail(), user.getUserId());
    }




}
