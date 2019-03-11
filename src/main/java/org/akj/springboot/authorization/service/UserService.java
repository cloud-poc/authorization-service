package org.akj.springboot.authorization.service;

import org.akj.springboot.authorization.entity.Users;
import org.akj.springboot.authorization.exception.GenericException;
import org.akj.springboot.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public Users findByUsername(String userName) {
		Users user = userRepository.findByUsername(userName);
		if (user == null) {
			throw new GenericException("E-001","User does not exist");
		}
		return user;
	}
}
