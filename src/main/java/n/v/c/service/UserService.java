package n.v.c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import n.v.c.entity.User;
import n.v.c.form.LoginForm;
import n.v.c.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		// Kiểm tra xem user có tồn tại trong database không?
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}

	public String registerNewAcc(LoginForm form) {
		User user = userRepository.findByUsername(form.getUsername());
		if (user == null) {
			User entity = User.builder().username(form.getUsername()).password(form.getPassword()).role("ROLE_USER")
					.passwordConfirm(form.getPasswordConfirm()).status("1").build();
			userRepository.save(entity);
			return "login";
		}
		return "forgot-password";
	}

	public void updateForgot(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			user.setStatus("2");
			userRepository.save(user);
		}

	}

}
