package com.xjw.demo.Mapper;

import com.xjw.demo.Dao.ExApply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ExApplyRowMapper implements RowMapper<ExApply>{
    @Override
    public ExApply mapRow(ResultSet resultSet, int i) throws SQLException {
        ExApply apply = new ExApply();
        apply.setApplyId(resultSet.getInt("applyId"));
        apply.setApplyTitle(resultSet.getString("applyTitle"));
        apply.setTips(resultSet.getString("tips"));
        apply.setReason(resultSet.getString("reason"));
        apply.setStartTime(resultSet.getString("startTime"));
        apply.setEndTime(resultSet.getString("endTime"));
        apply.setApplyType(resultSet.getInt("applyType"));
        apply.setUserId(resultSet.getInt("userId"));
        apply.setStation(resultSet.getInt("station"));
        return apply;
    }
}
