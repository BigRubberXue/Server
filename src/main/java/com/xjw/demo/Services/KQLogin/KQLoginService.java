package com.xjw.demo.Services.KQLogin;


import com.xjw.demo.Dao.User;
import com.xjw.demo.Mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class KQLoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public User checkUserExistAndGet(String username, String password){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE username = ? AND password=?",
                int.class,username,password);
        //log.info("成功查询,结果为:"+i);
        if(i == 1){
            String s = "select * from myuser WHERE username = '"+username+"' AND password = '" + password + "' ";
            return jdbcTemplate.queryForObject(s,new UserRowMapper());
        }else{
            return new User();
        }
    }


    public String checkAdminPassword(String password){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM password WHERE password=?",
                int.class,password);
        System.out.println(i);
        return i >= 1 ? "success":"fail";
    }
}
