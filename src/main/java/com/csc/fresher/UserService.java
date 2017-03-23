package com.csc.fresher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDetail find(String username) {
		return userRepository.findOne(username);
	}

	public void save(UserDetail user) {
		userRepository.save(user);
	}

}
