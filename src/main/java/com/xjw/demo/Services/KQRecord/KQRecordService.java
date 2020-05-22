package com.xjw.demo.Services.KQRecord;

import com.xjw.demo.Dao.AttData;
import com.xjw.demo.Dao.Attendance;
import com.xjw.demo.Dao.MouthData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class KQRecordService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获得考勤记录
    public List<Attendance> getAtt(Integer userId){
        List<Attendance> list = new ArrayList<>();
        list = jdbcTemplate.query("select * from att where attMemberId = "+userId+" ORDER BY attId DESC", new BeanPropertyRowMapper(Attendance.class));
        return list;
    }

    //获得综合考勤记录
    public List<MouthData> getAttDataSum(Integer userId){
        List<MouthData> list = new ArrayList<>();
        list = jdbcTemplate.query("select * from monthdata where userId = '"+userId+"' ORDER BY mouthDataId DESC", new BeanPropertyRowMapper(MouthData.class));
        System.out.println(list);
        return list;
    }


    public String signInData(Attendance att){
        /*int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE username = ?",
                int.class,username);
        */
//        System.out.println(att.getAttMemberId());
//        System.out.println(userExist(att.getAttMemberId()));
        if(!userExist(att.getAttMemberId())){
            return "fail: user no exist";
        }

        if(isSignedAtData(att.getAttMemberId(), att.getAttTime())){
            return "fail: has signed In";
        }
        String[] data = att.getAttTime().split("-");

        if(!hasMouthData(data[1],att.getAttMemberId())){
            jdbcTemplate.update("INSERT INTO `kq`.`monthdata` (`mouth`, `userId`, `lateTime`, `signedTime`) VALUES (?, ?, ?, ?);\n"
                    , data[1], att.getAttMemberId(), 0, 0);
        }
        //修改个人信息

        try{
            jdbcTemplate.update("INSERT INTO `kq`.`att` (`attMember`, `attTime`, `attStatus`, `attMemberId`) VALUES (?, ?, ?, ?);"
                    , att.getAttMember(), att.getAttTime(), att.getAttStatus(), att.getAttMemberId());
            if(Integer.valueOf(att.getAttStatus()) == 1){
                jdbcTemplate.update("UPDATE monthdata SET signedTime = signedTime + 1 WHERE (userId = ?)", att.getAttMemberId()
                );
            }else{
                jdbcTemplate.update("UPDATE monthdata SET lateTime = lateTime + 1 WHERE (userId = ?)", att.getAttMemberId()
                );
            }
            return "success:";
        }catch (Exception e){
            return "fail: sql error";
        }
    }

    public String checkSigned(Integer userId, String data){
        if(!userExist(userId)){
            //System.out.println("1");
            return "fail";
        }else{
            if(isSignedAtData(userId, data)){
//                System.out.println("2");
                return "fail";
            }else{
//                System.out.println("3");
                return "success";
            }
        }
    }

    public boolean userExist(Integer userId){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  myuser WHERE userId = ?",
                int.class, userId);
        return i>=1 ? true : false;
    }

    public boolean isSignedAtData(Integer userId, String data){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  att WHERE attMemberId = ? AND attTime = ?",
                int.class, userId, data);
        return i >= 1 ? true : false;
    }


    public boolean hasMouthData(String data, Integer userId){
        int i = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM  monthdata WHERE userId = ? AND mouth = ?",
                int.class, userId, data);
        return i != 0;
    }


}
