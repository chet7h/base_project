package n.v.c.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import n.v.c.form.LoginForm;
import n.v.c.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@GetMapping("register")
	public String register(LoginForm form, Model model) {
		model.addAttribute("user", form);
		return "register";
	}

	@PostMapping("register")
	public String register(@Valid LoginForm form, BindingResult result, Model model) {
		form.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
		model.addAttribute("user", form);
		if (result.hasErrors() || (!form.getPassword().equals(form.getPasswordConfirm()))) {
			return "register";
		}
		return userService.registerNewAcc(form);
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("logout")
	public String logout() {
		return "login";
	}

	@GetMapping("forgot-password")
	public String forgot() {
		return "forgot-password";
	}

	@PostMapping("forgot-password")
	public String updateForgot(String username) {
		userService.updateForgot(username);
		return "redirect:login";
	}

}
