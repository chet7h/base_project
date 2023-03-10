package n.v.c.controller;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import n.v.c.entity.Course;
import n.v.c.entity.CourseList;
import n.v.c.form.RegisterForm;
import n.v.c.service.BankService;
import n.v.c.service.CourseService;
import n.v.c.service.DiscountCodeService;

@Controller
public class RegisterCourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private DiscountCodeService discountCodeService;

	@Autowired
	private BankService bankService;

	@GetMapping("register-course/{courseId}")
	public String registerCourse(Model model, @PathVariable(name = "courseId") String courseId,
			Authentication authentication) {
		RegisterForm course = new RegisterForm();
		// mode new/update
		String CourseName = "";
		long CourseId = 0;
		Course tmp = null;
		CourseList courseList = null;
		try {
			CourseId = Long.parseLong(courseId);
			courseList = courseService.getCourseName(CourseId).get();
			tmp = courseService.getCourseByUserAndId(authentication.getName(), courseList.getCourseName());
		} catch (Exception e) {
			CourseName = courseId;
			tmp = courseService.getCourseByUserAndId(authentication.getName(), CourseName);
		}

		if (tmp != null) {
//update
			course.setOtp(tmp.getOtp());
			course.setCourseName(tmp.getNameCourse());
			course.setPrice(tmp.getPrice());
			course.setDiscountCode(tmp.getDiscountCode());
			course.setUsername(tmp.getUsernameCourse());
			course.setPassword(tmp.getPasswordCourse());

		} else {
			Random rand = new Random();
			int otp = rand.nextInt(999999);
			String otpStr = "easymooc_" + otp;
			course.setOtp(otpStr);
			
			course.setCourseName(courseList.getCourseName());
			course.setPrice(courseList.getPrice());
		}

		course.setCourseCode(CourseId);
		course.setCssItem1("active");
		course.setActivePage("1");

		model.addAttribute("course", course);
		return "register-course";
	}

	@PostMapping("register-step")
	public String registerStep(Authentication authentication, @RequestBody RegisterForm form, Model model) {
		String activePage = "";

		this.resetCss(form);
		if ("1n".equals(form.getBtnClick())) {
			form.setCssItem2("active");
			// validate
			if (StringUtils.isAnyBlank(form.getUsername(), form.getPassword())) {
				form.setCssItem1("danger");
				form.setMessageItem1("Thông tin đăng nhập không đúng");
			}
			activePage = "2";
		} else if ("2n".equals(form.getBtnClick())) {
			form.setCssItem3("active");
			// check mã giảm giá
			if (StringUtils.isNotBlank(form.getDiscountCode())) {
				boolean isCodeIncorrect = !discountCodeService.checkDiscountCode(form.getDiscountCode());
				if (isCodeIncorrect) {
					form.setCssItem2("warning");
					form.setDiscountCode("");
					form.setMessageItem2("Mã không tồn tại");
				}
			}
			activePage = "3";
		} else if ("3n".equals(form.getBtnClick())) {
			// check pay
			// TODO tam thoi skip chuc nang nay
			if (bankService.checkPayWhenRegister(form.getOtp()) < 1) {
				form.setCssItem3("warning");
			}
			form.setCssItem4("active");
			activePage = "4";
		} else if ("2p".equals(form.getBtnClick())) {
			form.setCssItem1("active");
			activePage = "1";
		} else if ("3p".equals(form.getBtnClick())) {
			form.setCssItem2("active");
			activePage = "2";
		} else if ("4p".equals(form.getBtnClick())) {
			form.setCssItem3("active");
			activePage = "3";
		}
		form.setActivePage(activePage);
		model.addAttribute("course", form);
		return "register-course :: register-step";
	}

	@PostMapping("register-course")
	public String registerCourse(Authentication authentication, RegisterForm form) {
		courseService.registerCourse(form, authentication.getName());
		return "redirect:course-info";
	}

	private void resetCss(RegisterForm form) {
		if ("active".equals(form.getCssItem1())) {
			form.setCssItem1("");
			form.setMessageItem1("");
		}
		if ("active".equals(form.getCssItem2())) {
			form.setCssItem2("");
			form.setMessageItem2("");
		}
		if ("active".equals(form.getCssItem3())) {
			form.setCssItem3("");
		}
		if ("active".equals(form.getCssItem4())) {
			form.setCssItem4("");
		}

	}
}
