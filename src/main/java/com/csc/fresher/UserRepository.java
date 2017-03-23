package com.csc.fresher;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.csc.fresher.UserDetail;

public interface UserRepository extends CrudRepository<UserDetail, String>{
	
	List<UserDetail> findByUsernameContaining(String username);
	
}
