/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getInt("Id"));
        u.setName(rs.getString("Name"));
        u.setPassword(rs.getString("Password"));
        return u;
    };
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "select * from User where Name = ?";

        return jdbcTemplate.queryForObject(sql, userRowMapper, username);
    }

    public int saveUser(User user) {
        String sql = "INSERT INTO User (Name, Password) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getPassword());
    }

}
