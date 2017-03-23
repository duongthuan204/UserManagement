package com.csc.fresher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new UserDetail());
		return "login";
	}
	
	@GetMapping("/error")
	public String error(){
		return "error";
	}

	@PostMapping("/welcome")
	public String login(UserDetail user, Model model, RedirectAttributes redirect) {
		String username = user.getUsername().trim();
		String password = user.getPassword().trim();
		UserDetail data = userService.find(username);
		if (data != null) {
			if (userService.passwordEncoder.matches(password, data.getPassword())) {
				model.addAttribute("user", data);
				return "welcome";
			}
		}
		redirect.addFlashAttribute("message", "Invalid username or password");
		return "redirect:/";

	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserDetail());
		return "register";
	}

	@PostMapping("/success")
	public String save(UserDetail user, Model model, RedirectAttributes redirect) {
		String username = user.getUsername().trim();
		if (userService.find(username) == null) {
			userService.save(user);
			model.addAttribute("user", user);
			return "welcome";
		}
		redirect.addFlashAttribute("message", "This username is already taken");
		return "redirect:/register";
	}
	
	

}
