/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    public RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setFirstName(rs.getString("firstName"));
        s.setLastName(rs.getString("lastName"));
        s.setEmail(rs.getString("email"));
        s.setDateOfBirth(rs.getDate("dateOfBirth"));
        s.setEnrollmentDate(rs.getDate("enrollmentDate"));

        return s;
    };
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveStudent(Student s) {
        String sql = """
                INSERT INTO Student
                (firstName, lastName, email, dateOfBirth, enrollmentDate)
                VALUES (?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(sql,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDateOfBirth(),
                s.getEnrollmentDate()
        );
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Student ORDER BY Id DESC";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM Student WHERE Id = ?";

        return jdbcTemplate.queryForObject(sql, studentRowMapper, id);
    }

    public int update(Student s) {
        String sql = """
                UPDATE Student
                SET firstName=?,
                    lastName=?,
                    email=?,
                    dateOfBirth=?,
                    enrollmentDate=?
                WHERE Id=?
                """;

        int rows = jdbcTemplate.update(sql,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDateOfBirth(),
                s.getEnrollmentDate(),
                s.getId()
        );
        return rows;
    }

    public int delete(int id) {
        String sql = "DELETE FROM Student WHERE Id=?";

        return jdbcTemplate.update(sql, id);
    }
}
