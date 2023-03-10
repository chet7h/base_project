package n.v.c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import n.v.c.entity.CourseList;

@Repository
public interface CourseListRepository extends JpaRepository<CourseList, Long>{

	List<CourseList> findAllByCourseCode(String courseId);

	List<CourseList> findAllById(long courseId);

}
