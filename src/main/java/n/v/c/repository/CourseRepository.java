package n.v.c.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import n.v.c.entity.Course;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findAllByUsernameRegister(String name);

	Course findAllByUsernameRegisterAndNameCourse(String username, String courseName);

}
