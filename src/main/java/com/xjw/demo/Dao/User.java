package com.xjw.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Integer userId;
    //用户名，登陆
    private String username;
    //密码，登陆
    private String password;
    //员工姓名
    private String name;
    //工位
    private String station;
    //联系方式--电话
    private String phone;
    //联系方式--邮箱
    private String mail;

}
