package com.ex.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.domain.User;

@Service
@Transactional
public class KinderServiceImpl implements KinderService {

	@Override
	public User authenticate(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
