package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY =
            """
                insert into course (id, name, author)
                values(?, ?, ?);
            """;

    private static String DELETE_QUERY =
            """
                delete from course where id = ?;
                    """;
    private static String SELECT_QUERY =
            """
                select * from course
                where id =?
                    """;
    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteBydId(long id) {
        springJdbcTemplate.update(DELETE_QUERY,id);
    }

    public Course findById(long id) {
        //ResultSet -> Bean => Row Mapper =>
        return springJdbcTemplate.queryForObject(SELECT_QUERY, // 특정 빈객체로 연결해주는 beanpropertyrowmapper로 course클래스를 연결해서 객체반환
                new BeanPropertyRowMapper<>(Course.class), id);
    }
}
