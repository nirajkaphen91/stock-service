package com.cts.assignment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.assignment.exception.UserAlreadyExistsException;
import com.cts.assignment.exception.UserNotFoundException;
import com.cts.assignment.model.User;
import com.cts.assignment.repository.UserRepository;

public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userService;

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
	public void registerUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(user);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void registerUserFailure() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(null);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);
	}

	@Test
	public void updateUser() throws UserNotFoundException {
		when(userRepository.findById(user.getUsername())).thenReturn(options);
		user.setName("Niraj001");
		User fetchuser = userService.updateUser(user.getUsername(), user);
		assertEquals(user, fetchuser);
	}

	@Test
	public void deleteUserSuccess() throws UserNotFoundException {
		when(userRepository.findById(user.getUsername())).thenReturn(options);
		boolean flag = userService.deleteUser(user.getUsername());
		assertEquals(true, flag);
	}

	@Test
	public void getUserById() throws UserNotFoundException {
		when(userRepository.findById(user.getUsername())).thenReturn(options);
		User fetchedUser = userService.getUserById(user.getUsername());
		assertEquals(user, fetchedUser);
	}
	
	@Test
	public void findByIdAndPassword() throws UserNotFoundException {
		when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		User fetchedUser = userService.findByIdAndPassword(user.getUsername(), user.getPassword());
		assertEquals(user, fetchedUser);
	}
}