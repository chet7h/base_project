package n.v.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import n.v.c.service.CourseListService;
import n.v.c.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseListService courseListService;

	@Autowired
	private CourseService courseService;

	@GetMapping({ "course", "/", "" })
	public String course(Authentication authentication, Model model) {
		model.addAttribute("courseList", courseListService.getCourseList(authentication.getName()));
		return "course";
	}

//	@GetMapping("register-course/{courseId}")
//	public String registerCourse(@PathVariable(name = "courseId") long courseId, Model model,
//			Authentication authentication) {
//		Random rand = new Random();
//		int otp = rand.nextInt(999999);
//		model.addAttribute("course", CourseForm.builder().courseId(courseId).otp(String.valueOf(otp)).build());
//		return "register-course";
//	}
//
//	@PostMapping("register-course")
//	public String registerCourse(Authentication authentication, CourseForm CourseForm) {
//
//		courseService.registerCourse(CourseForm, authentication.getName());
//		return "redirect:course-info";
//	}

	@GetMapping("course-info")
	public String infoCourse(Authentication authentication, Model model) {
		model.addAttribute("courseInfos", courseService.getAllCourseByUsername(authentication.getName()));
		return "info-course";
	}

}
