package com.xjw.demo.Mapper;


import com.xjw.demo.Dao.Message;
import org.springframework.jdbc.core.RowMapper;
import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message>  {
    @Override
    public Message mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setMsgId(resultSet.getInt("msgId"));
        message.setMsg(resultSet.getString("msg"));
        message.setMsgTime(resultSet.getString("msgTime"));
        message.setMsgTitle(resultSet.getString("msgTitle"));
        message.setMemberId(resultSet.getInt("memberId"));
        return message;
    }
}
