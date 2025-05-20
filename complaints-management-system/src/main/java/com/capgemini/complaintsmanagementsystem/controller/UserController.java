package com.capgemini.complaintsmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaintsmanagementsystem.Dto.UserPasswordUpdateDto;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		log.debug("Returning {} users", userService.getAllUsers().size());
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		log.debug("Employee fetched: {}", userId);
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
	}

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		User createdUser = userService.addUser(user);
		log.debug("Employee created with ID: {}", user.getUserId());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@Valid @PathVariable Long userId, @RequestBody User user,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		} else {
			log.debug("Employee created with ID: {}", user.getUserId());
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, user));
		}

	}

	@PatchMapping("/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> patchComplaint(@PathVariable Long userId, @RequestBody User user) {
		User updated = userService.patchUser(userId, user);
		log.debug("User updations with ID: {}", user.getUserId());
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		log.info("User with ID {} successfully deleted", userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{userId}/password")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> updatePassword(@PathVariable Long userId,
			@RequestBody UserPasswordUpdateDto passwordUpdateDTO) {
		User updated = userService.updateUserPassword(userId, passwordUpdateDTO);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

}
