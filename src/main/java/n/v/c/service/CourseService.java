package n.v.c.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import n.v.c.dto.CourseDto;
import n.v.c.entity.Course;
import n.v.c.entity.CourseList;
import n.v.c.enums.StatusCourse;
import n.v.c.form.RegisterForm;
import n.v.c.repository.CourseListRepository;
import n.v.c.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseListRepository courseListRepository;

	public Optional<CourseList> getCourseName(long id) {
		return courseListRepository.findById(id);
	}

	public List<CourseDto> getAllCourseByUsername(String name) {
		List<CourseDto> rtn = new ArrayList<>();
		courseRepository.findAllByUsernameRegister(name).forEach(e -> {
			rtn.add(CourseDto.builder().nameCourse(e.getNameCourse()).startDate(e.getStartDate()).otp(e.getOtp())
					.status(StatusCourse.getDisplayFromCode(e.getStatus())).price(e.getPrice()).build());
		});

		return rtn;

	}

	public void registerCourse(RegisterForm form, String usernameRegister) {

		Course tmp = courseRepository.findAllByUsernameRegisterAndNameCourse(usernameRegister, form.getCourseName());
		Course entity = null;
		if (tmp == null) {
			List<CourseList> courses = courseListRepository.findAllById(form.getCourseCode());
			if (courses.size() != 1) {
				return;
			}
			entity = Course.builder().nameCourse(courses.get(0).getCourseName()).price(courses.get(0).getPrice())
					.status(StatusCourse.VERIFYING.getCode()).usernameRegister(usernameRegister)
					.startDate(LocalDate.now()).discountCode(form.getDiscountCode()).usernameCourse(form.getUsername())
					.passwordCourse(form.getPassword()).otp(form.getOtp()).build();
		} else {
			entity = tmp;
			entity.setPasswordCourse(form.getPassword());
			entity.setUsernameCourse(form.getUsername());
			entity.setDiscountCode(form.getDiscountCode());
		}
		courseRepository.save(entity);
	}

	public List<CourseDto> getCustomerRegisterInfo() {
		List<CourseDto> rtn = new ArrayList<>();
		courseRepository.findAll().forEach(e -> {
			rtn.add(CourseDto.builder().customerName(e.getUsernameRegister()).nameCourse(e.getNameCourse())
					.usernameCourse(e.getUsernameCourse()).passwordCourse(e.getPasswordCourse()).paymentCode(e.getOtp())
					.startDate(e.getStartDate()).status(StatusCourse.getDisplayFromCode(e.getStatus())).build());
		});
		return rtn;
	}

	public Course getCourseByUserAndId(String username, String courseName) {
		return courseRepository.findAllByUsernameRegisterAndNameCourse(username, courseName);
	}

}
