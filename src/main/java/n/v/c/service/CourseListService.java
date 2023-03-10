package n.v.c.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import n.v.c.dto.CourseListDto;
import n.v.c.repository.CourseListRepository;
import n.v.c.repository.CourseRepository;

@Service
public class CourseListService {

	@Autowired
	private CourseListRepository courseListRepository;

	@Autowired
	private CourseRepository courseRepository;

	public List<CourseListDto> getCourseList(String username) {
		List<CourseListDto> rtn = new ArrayList<>();
		Set<String> courseInfos = courseRepository.findAllByUsernameRegister(username).stream()
				.map(e -> e.getNameCourse()).collect(Collectors.toSet());
		courseListRepository.findAll().forEach(e -> {
			String action = "1";// register
			if (courseInfos.contains(e.getCourseName())) {
				action = "0";// view
			}
			rtn.add(CourseListDto.builder().id(e.getId()).courseCode(e.getCourseCode()).courseName(e.getCourseName())
					.link(e.getLink()).duration(e.getDuration()).jobTitle(e.getJobTitle()).price(e.getPrice())
					.action(action).build());
		});

		return rtn;
	}

}
