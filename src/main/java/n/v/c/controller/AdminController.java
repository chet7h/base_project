package n.v.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import n.v.c.service.CourseService;

@Controller
public class AdminController {

	@Autowired
	private CourseService courseService;

	@GetMapping("cuongnv20")
	public String index(Model model) {
		model.addAttribute("courseList", courseService.getCustomerRegisterInfo());
		return "admin";
	}
}
