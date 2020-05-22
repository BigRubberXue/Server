package com.xjw.demo.Mapper;


import com.xjw.demo.Dao.User;
import org.springframework.jdbc.core.RowMapper;
import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        /*Integer user_id=resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String password=resultSet.getString("password");
        String name=resultSet.getString("name");
        Integer attention=resultSet.getInt("attention");
        Integer gift=resultSet.getInt("gift");*/
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setStation(resultSet.getString("station"));
        user.setPhone(resultSet.getString("phone"));
        user.setMail(resultSet.getString("mail"));
        return user;
    }
}