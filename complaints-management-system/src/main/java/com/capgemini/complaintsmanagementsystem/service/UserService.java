package com.capgemini.complaintsmanagementsystem.service;

import java.util.*;

import com.capgemini.complaintsmanagementsystem.entity.User;


public interface UserService {
	boolean existsByUserName(String username);

	boolean existsByEmail(String email);
	List<User> getAllUsers();

	User getUserById(Long userId);

	User addUser(User user);

	User updateUser(Long userId, User user);

	User patchUser(Long userId, User user);

	void deleteUser(Long userId);
	User findByUserNameOrEmail(String username, String email);

}
