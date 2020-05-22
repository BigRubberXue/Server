package com.xjw.demo.Mapper;

import com.xjw.demo.Dao.ExPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExPersonRowMapper implements RowMapper<ExPerson> {
    @Override
    public ExPerson mapRow(ResultSet resultSet, int i) throws SQLException {
        ExPerson exPerson = new ExPerson();
        exPerson.setExPersonId(resultSet.getInt("exPersonId"));
        exPerson.setUserId(resultSet.getInt("userId"));
        exPerson.setSignedTime(resultSet.getInt("signedTime"));
        exPerson.setMouth(resultSet.getInt("mouth"));
        exPerson.setName(resultSet.getString("name"));
        return null;
    }
}
