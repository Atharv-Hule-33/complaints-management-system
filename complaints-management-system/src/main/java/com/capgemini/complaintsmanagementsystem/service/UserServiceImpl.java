package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.exception.UserNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository= userRepository;
	}
	

	@Override
	public List<User> getAllUsers() {
		log.debug("Fetching all users from the repository");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		log.debug("Fetching user by ID: {}", userId);
		User user = userRepository.findById(userId).orElseThrow(()-> {
			log.warn("User not found with ID: {}", userId);
			return new UserNotFoundException("User with id " + userId + " is not found");
		});
		return user;
	}


	@Override
	public User addUser(User user) {
		log.debug("Saving new user to the repository");
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long userId, User user) {
		User existing = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with id " + userId + " is not found"));
		existing.setUserName(user.getUserName());
		existing.setUserEmail(user.getUserEmail());
		existing.setUserPassword(user.getUserPassword());
		existing.setUserPhone(user.getUserPhone());
		existing.setUserType(user.getUserType());
		log.info("Successfully updated user with ID: {}", userId);
	    log.debug("Updated user details: {}", existing);
		return userRepository.save(existing);
	}

	@Override
	public void deleteUser(Long userId) {
		if(!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User with id " + userId + " is not found");
		}
		log.debug("Deleting user by ID: {}", userId);
		userRepository.deleteById(userId);
		
	}


	@Override
	public User patchUser(Long userId, User user) {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " is not found"));
		if (user.getUserId() != null) {
			log.debug("Patching userId: {}", user.getUserId());
			existing.setUserId(user.getUserId());
		}
		if (user.getUserName() != null) {
			log.debug("Patching userName: {}", user.getUserName());
			existing.setUserName(user.getUserName());
		}
		if (user.getUserEmail() != null) {
			log.debug("Patching userEmail: {}", user.getUserEmail());
			existing.setUserEmail(user.getUserEmail());
		}

		if (user.getUserPhone() != null) {
			log.debug("Patching userPhone: {}", user.getUserPhone());
			existing.setUserPhone(user.getUserPhone());
		}

		if (user.getUserType() != null) {
			log.debug("Patching userType: {}", user.getUserType());
			existing.setUserType(user.getUserType());
		}
		log.info("Successfully patched user with ID: {}", userId);
	    log.debug("Updated user details after patch: {}", existing);
		return userRepository.save(existing);
	}

}
