package com.cts.assignment.service;

import com.cts.assignment.exception.UserAlreadyExistsException;
import com.cts.assignment.exception.UserNotFoundException;
import com.cts.assignment.model.User;

public interface UserService {
	public User findByIdAndPassword(String userId, String password) throws UserNotFoundException;

	User registerUser(User user) throws UserAlreadyExistsException;

	User updateUser(String userId, User user) throws UserNotFoundException;

	boolean deleteUser(String userId) throws UserNotFoundException;

	User getUserById(String userId) throws UserNotFoundException;
}
