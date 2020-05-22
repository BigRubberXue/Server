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
public class AttData {

    //id
    private Integer attDataId;
    //时间
    private String attTime;
    //未签到人数
    private Integer unsigned;
    //签到人数
    private Integer signed;


}
