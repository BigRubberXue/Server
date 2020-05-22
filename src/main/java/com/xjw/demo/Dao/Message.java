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
public class Message {

    private Integer msgId;
    //消息
    private String msg;
    //消息发送时间
    private String msgTime;
    //标题
    private String msgTitle;
    //发送的目标成员
    private Integer memberId;
    /*是否已读
    * 0 . 未读
    * 1 . 已读
    * */
    private Integer hasRead;

}
