package com.xjw.demo.Mapper;

import com.xjw.demo.Dao.Attendance;
import org.springframework.jdbc.core.RowMapper;
import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AttendanceRowMapper implements RowMapper<Attendance> {
    @Override
    public Attendance mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        Attendance att = new Attendance();
        att.setAttId(resultSet.getInt("attId"));
        att.setAttMember(resultSet.getString("attMember"));
        att.setAttTime(resultSet.getString("attTime"));
        att.setAttStatus(resultSet.getString("attStatus"));
        att.setAttMemberId(resultSet.getInt("attMemberId"));

        return att;
    }
}
