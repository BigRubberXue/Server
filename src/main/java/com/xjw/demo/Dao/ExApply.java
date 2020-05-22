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
public class ExApply{

    //请求id
    private Integer applyId;

    //请求标题
    private String applyTitle;

    //请求说明
    private String tips;

    //原因
    private String reason;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //请求类型
    private Integer applyType;

    //申请人id
    private Integer userId;

    /*申请状态
    0. 未受理
    1, 已同意
    2, 未同意
    */

    private Integer station;

}
