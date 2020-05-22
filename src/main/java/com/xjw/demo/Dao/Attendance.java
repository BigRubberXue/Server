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
public class Attendance {

    private Integer attId;
    //签到成员
    private String attMember;
    //签到时间
    private String attTime;
    //签到状态
    private String attStatus;
    //签到成员id
    private Integer attMemberId;
}
