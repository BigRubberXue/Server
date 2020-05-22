package com.xjw.demo.Mapper;

import com.xjw.demo.Dao.AttData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttDataRowMapper implements RowMapper<AttData> {
    @Override
    public AttData mapRow(ResultSet resultSet, int i) throws SQLException {
        AttData attData = new AttData();
        attData.setAttDataId(resultSet.getInt("attDataId"));
        attData.setAttTime(resultSet.getString("attTime"));
        attData.setSigned(resultSet.getInt("signed"));
        attData.setUnsigned(resultSet.getInt("unsigned"));
        return attData;
    }
}
