package com.xjw.demo.Services.KQAdmin;

import com.xjw.demo.Dao.*;
import com.xjw.demo.Mapper.MouthDataRowMapper;
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
public class KQAdminService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    ////操作类
    //新增用户
    public String addNewUser(String username, String password){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE username = ? AND password=?",
                int.class,username,password);
        if(i == 0){
            jdbcTemplate.update("INSERT INTO `kq`.`myuser` (`userName`, `password`, `name`, `station`, `phone`, `mail`) VALUES (?, ?, ?, ?, ?, ?);\n",
                    username, password, "用户"+username, "未填写", "未填写", "未填写");
            return "success";
        }
        return "fail";//username exist
    }

    //删除用户 by name
    public String deleteUser(String username, String adminPas){
        System.out.println("check admin user");
        int index = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM password WHERE password=?",
                int.class,adminPas);
        if(index == 0){
            return "fail";//no an admin
        }
        System.out.println("check user exist");
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE username = ?",
                int.class,username);
        if(i == 0){
            return "fail";//user no exist
        }
        System.out.println("delete user");
        jdbcTemplate.update("DELETE FROM `kq`.`myuser` WHERE (`username` = ?);",
                username);
        return "success";
    }

    //删除用户 by id
    public String deleteUserById(Integer userId){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE userId = ?",
                int.class,userId);
        if(i == 0){
            return "fail";//user no exist
        }
        jdbcTemplate.update("DELETE FROM `kq`.`myuser` WHERE (`userId` = ?);",
                userId);
        return "success";
    }

    //新增优秀员工
    public String addExPerson(ExPerson exPerson){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE userId = ?",
                int.class,exPerson.getUserId());
        if(i == 0){
            return "user no exist";
        }
        int index = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  experson WHERE userId = ? And mouth = ?",
                int.class,exPerson.getUserId(), exPerson.getMouth());
        if(index != 0){
            return "fail";
        }
        jdbcTemplate.update("INSERT INTO `kq`.`experson` (`userId`, `signedTime`, `mouth`, `name`) VALUES (?, ?, ?, ?);",
                exPerson.getUserId(),exPerson.getSignedTime(),exPerson.getMouth(),exPerson.getName());
        return "success";
    }

    //更新优秀员工
    public String updateExPerson(String date){
        List<User> list = new ArrayList<>();
        try{
            System.out.println("test1111");
            System.out.println("date = "+date);
//            System.out.println();
//            MouthData user = jdbcTemplate.queryForObject("select * from monthdata where mouth='"+date+" ' ORDER BY signedTime DESC", new MouthDataRowMapper());
//
//
//            System.out.println(user);
            Integer MaxTime = jdbcTemplate.queryForObject("select max(signedTime) from monthdata where mouth='"+date+"'", Integer.class);
            System.out.println(MaxTime);
            List<MouthData> mouthDatas = jdbcTemplate.query("select  * from monthdata where signedTime = '"+MaxTime+"' and mouth = '"+date+"' ",new BeanPropertyRowMapper(MouthData.class));
            for(MouthData index : mouthDatas){
                list.add(this.getUserByUserId(index.getUserId()));
            }
            System.out.println("test1");
            System.out.println(list);
            //清空当月数据
            if(this.hasLoad(date)){
                System.out.println("delete");
                this.deleteExPerson(Integer.valueOf(date));
            }
            System.out.println("insert");
            for(User index : list){
                this.insertExPerson(index.getUserId(), MaxTime, Integer.valueOf(date), index.getName());
            }
        }catch(Exception e){
            System.out.println("sql error");
            return "fail";
        }
        return "success";
    }

    //同意申请
    public String agreeApply(Integer applyId, String msgTime, Integer userId){
        int i = jdbcTemplate.queryForObject("SELECT station FROM  apply WHERE applyId = ?",
                int.class,applyId);
        if(i != 0){
            return "fail";
        }
        jdbcTemplate.update("UPDATE apply SET station = 1 WHERE (applyId = ?)"
                ,applyId);
        jdbcTemplate.update("INSERT INTO `kq`.`message` (`msg`, `msgTime`, `msgTitle`, `memberId`, `hasRead`) VALUES (?, ?, ?, ?, '0');",
                "申请通过", msgTime, "通知", userId
        );
        return "success";
    }

    //拒绝申请
    public String disagreeApply(Integer applyId){
        int i = jdbcTemplate.queryForObject("SELECT station FROM  apply WHERE applyId = ?",
                int.class,applyId);
        if(i != 0){
            return "fail";
        }
        jdbcTemplate.update("UPDATE apply SET station = 2 WHERE (applyId = ?)"
                ,applyId);
        return "success";
    }

    //发送消息
    public String sendMsg(String msgTitle, String msg, String msgTime){
        List<User> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from myuser",new BeanPropertyRowMapper(User.class));
        System.out.println(list);
        System.out.println("count index = "+list.size());
        for(User user : list){
            jdbcTemplate.update("INSERT INTO `kq`.`message` (`msg`, `msgTime`, `msgTitle`, `memberId`, `hasRead`) VALUES (?, ?, ?, ?, '0');",
                    msg, msgTime, msgTitle, user.getUserId()
            );
           // System.out.println("msgTitle: "+msgTitle+"\n msg:"+msg+"\n msgTime:"+msgTime+"\n index:"+user.getUserId());
        }
        return "success";
    }

    //提交今日总结考勤
    public String subToday(String date, Integer unsigned, Integer signed){
        System.out.println(date);
        System.out.println(unsigned);
        System.out.println(signed);
        jdbcTemplate.update("INSERT INTO `kq`.`attdata` (`attTime`, `unsigned`, `signed`) VALUES (?, ?, ?);\n",
                date, unsigned, signed);
        return "success";
    }

    //更新考勤总结表
    public String updateToday(String date, Integer unsigned, Integer signed){
        System.out.println(date);
        System.out.println(unsigned);
        System.out.println(signed);
        jdbcTemplate.update("UPDATE `kq`.`attdata` SET `unsigned` = ?, `signed` = ? WHERE (`attTime` = ?);\n",
                unsigned, signed, date);
        return "success";
    }

//    //更新优秀员工
//    public String updateExPerson(Integer userId, Integer signedTime, Integer mouth, String name){
//        jdbcTemplate.update("UPDATE `kq`.`experson` SET `signedTime` = '?', `mouth` = '?', `name` = '?' WHERE (`userId` = '?');\n",
//                signedTime, mouth, name, userId);
//        return "success";
//    }

    //删除某月份优秀员工
    public String deleteExPerson(Integer mouth){
        jdbcTemplate.update("DELETE FROM `kq`.`experson` WHERE (mouth = ?);\n",
                mouth);
        return "success";
    }

    //插入优秀员工
    /*INSERT INTO `kq`.`experson` (`userId`, `signedTime`, `mouth`, `name`) VALUES ('1', '12', '4', '会长');*/
    public String insertExPerson(Integer userId, Integer signedTime, Integer mouth, String name){
        jdbcTemplate.update("INSERT INTO `kq`.`experson` (`userId`, `signedTime`, `mouth`, `name`) VALUES (?, ?, ?, ?);",
                userId, signedTime, mouth, name);
        return "success";
    }



    ////获取信息
    //获得考勤记录
    public List<Attendance> getAtt(){
        List<Attendance> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from att ORDER BY attId DESC",new BeanPropertyRowMapper(Attendance.class));
        return list;
    }

    //获得未审核申请
    public List<ExApply> getApply(){
        List<ExApply> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from apply where station = 0",new BeanPropertyRowMapper(ExApply.class));
        return list;
    }

    //获得考勤记录综合表
    public List<AttData> getAttSum() {
        List<AttData> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from attdata",new BeanPropertyRowMapper(AttData.class));
        return list;
    }

    //获得总人数
    public Integer sumMember(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser",
                int.class);
    }

    //获得已签到人数
    public Integer sumSignedMember(String date){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  att where attTime = '" + date+"'",
                int.class);
    }

    //获得优秀员工
    public List<ExPerson> getExPerson() {
        List<ExPerson> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from experson ORDER BY exPersonId DESC",new BeanPropertyRowMapper(ExPerson.class));
        System.out.println("service = "+list);
        return list;
    }

    //获取全体员工
    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list = jdbcTemplate.query("select  * from myuser",new BeanPropertyRowMapper(User.class));
        return list;
    }



    //获得员工信息
    public User getUserByUserId(Integer userId){
        User user = new User();
        user = jdbcTemplate.queryForObject("select * from myuser where userId="+userId, new UserRowMapper());
        return user;
    }

    //判断今日是否完成结算
    public boolean hasSubAtt(String date){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  attdata where attTime = '" + date+"'",
                int.class);
        //1 有记录  0 没记录
        //true 有结算  false 没结算
        return i != 0 ;
    }

    //判断今日是否完成结算
    public boolean hasLoad(String date){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  experson where mouth = '" + date+"'",
                int.class);
        //1 有记录  0 没记录
        //true 有结算  false 没结算
        return i != 0 ;
    }

}
