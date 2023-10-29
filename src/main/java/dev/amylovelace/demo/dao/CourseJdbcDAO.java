package dev.amylovelace.demo.dao;

import dev.amylovelace.demo.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class CourseJdbcDAO implements DAO<Course>{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Course> rowMapper =(rs, rowNum) -> {
        Course course = new Course();
        course.setCourseId(rs.getInt("courseId"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setLink(rs.getString("link"));
        return course;
    };



    @Override
    public List<Course> list() {
        String sql = "SELECT courseId, title, description,link FROM course";

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO course (title, description,link) VALUES (?,?,?)";
        int insert=  jdbcTemplate.update(sql,course.getTitle(),course.getDescription(),course.getLink());
        if(insert == 1 ){
            log.info("successfully created" + course.getTitle() );
        }

    }

    @Override
    public Optional<Course> get(int id) {
        String sql = "SELECT courseId, title, description,link FROM course WHERE courseId = ?";
        Course course = null;
        try{
            course = jdbcTemplate.queryForObject(sql, new Object[]{id},rowMapper);
        }catch (DataAccessException e) {
        log.error("not found course "+ id);
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void update(Course course, int id) {
        String sql = "UPDATE course SET title = ?, description = ?, link = ? WHERE courseId = ?";
        int update = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink(), id);
        if(update == 1){
            log.info("successfully updated course" + course.getTitle());
        }
    }

    @Override
    public void delete(int id) {
       jdbcTemplate.update("DELETE FROM course where courseId = ?", id);
    }
}
