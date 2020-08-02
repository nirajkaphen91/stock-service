package com.cts.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.assignment.exception.UserAlreadyExistsException;
import com.cts.assignment.exception.UserNotFoundException;
import com.cts.assignment.model.User;
import com.cts.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByIdAndPassword(String userId, String password) throws UserNotFoundException {
		return userRepository.findByUsernameAndPassword(userId, password);
	}
	
	public User registerUser(User user) throws UserAlreadyExistsException {
		user = userRepository.save(user);
		if(user == null) {
			throw new UserAlreadyExistsException("User already exist");
		} else {
			return user;
		}
	}

	public User updateUser(String userId,User user) throws UserNotFoundException {
		userRepository.save(user);
		return userRepository.findById(userId).get();
	}

	public boolean deleteUser(String userId) throws UserNotFoundException {
		try {
			userRepository.deleteById(userId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	public User getUserById(String userId) throws UserNotFoundException {
		return userRepository.findById(userId).get();
	}
}
