package com.cts.assignment.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.cts.assignment.exception.UserAlreadyExistsException;
import com.cts.assignment.exception.UserNotFoundException;
import com.cts.assignment.model.User;
import com.cts.assignment.repository.UserRepository;
import com.cts.assignment.service.UserServiceImpl;

public class UserControllerTest {

	@Mock
	private UserServiceImpl userService;
	@InjectMocks
	private UserController userController;

	private User user;
	private List<User> userList = null;
	private Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setDate(new Date());
		user.setUsername("John123");
		user.setPassword("johnpass");

		userList = new ArrayList<>();
		userList.add(user);
		options = Optional.of(user);
	}

	@Test
	public void testCreateUser() throws UserAlreadyExistsException {
		when(userService.registerUser((User) any())).thenReturn(user);
		ResponseEntity<User> userSaved = userController.createUser(user);
		assertNotNull(userSaved);
	}

	@Test
	public void testLogin() throws UserNotFoundException, ServletException {
		when(userService.findByIdAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		Object  fetchedUser = userController.login(user);
		assertNotNull(fetchedUser);
	}

	@Test
	public void testUpdateUser() throws UserNotFoundException {
		user.setName("Niraj001");
		when(userService.updateUser(user.getUsername(), user)).thenReturn(user);
		ResponseEntity<User> userSaved = userController.updateUser(user.getUsername(), user);
		assertNotNull(userSaved);
	}

	@Test
	public void testDeleteUser() throws UserNotFoundException {
		when(userService.deleteUser(user.getUsername())).thenReturn(true);
		userController.deleteUser(user.getUsername());
		assertTrue(true);
	}

	@Test
	public void testGetUserById() throws UserNotFoundException {
		when(userService.getUserById(user.getUsername())).thenReturn(user);
		ResponseEntity<User> userSaved = userController.getUserById(user.getUsername());
		assertNotNull(userSaved);
	}

//	@Test
//	public void testGetToken() {
//		fail("Not yet implemented");
//	}

}
