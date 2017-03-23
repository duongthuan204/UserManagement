package com.csc.fresher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserDetail find(String username) {
		return userRepository.findOne(username);
	}

	public void save(UserDetail user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
