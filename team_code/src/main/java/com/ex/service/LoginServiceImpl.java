package com.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.domain.User;
import com.ex.repo.UserRepo;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserRepo repo;

	@Override
	public User authenticate(User user) {
		return repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

}
