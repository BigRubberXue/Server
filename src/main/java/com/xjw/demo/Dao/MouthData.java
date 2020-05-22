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
public class MouthData {
    //id
    private Integer mouthDataId;
    //月份
    private Integer mouth;
    //用户id
    private Integer userId;
    //迟到次数
    private Integer lateTime;
    //签到次数
    private Integer signedTime;

}
