package com.xjw.demo.Mapper;

import com.xjw.demo.Dao.MouthData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MouthDataRowMapper implements RowMapper<MouthData> {
    @Override
    public MouthData mapRow(ResultSet resultSet, int i) throws SQLException {
        MouthData mouthData = new MouthData();
        mouthData.setMouthDataId(resultSet.getInt("mouthDataId"));
        mouthData.setMouth(resultSet.getInt("mouth"));
        mouthData.setUserId(resultSet.getInt("userId"));
        mouthData.setLateTime(resultSet.getInt("lateTime"));
        mouthData.setSignedTime(resultSet.getInt("signedTime"));
        return mouthData;
    }
}
