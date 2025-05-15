package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.exception.UserNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository= userRepository;
	}
	

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with id " + userId + " is not found"));
	}

	@Override
	public User addUser(User user) {
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
		return userRepository.save(existing);
	}

	@Override
	public void deleteUser(Long userId) {
		if(!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User with id " + userId + " is not found");
		}
		userRepository.deleteById(userId);
		
	}


	@Override
	public User patchUser(Long userId, User user) {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " is not found"));
		if (user.getUserId() != null) {
			existing.setUserId(user.getUserId());
		}
		if (user.getUserName() != null) {
			existing.setUserName(user.getUserName());
		}
		if (user.getUserEmail() != null) {
			existing.setUserEmail(user.getUserEmail());
		}

		if (user.getUserPhone() != null) {
			existing.setUserPhone(user.getUserPhone());
		}

		if (user.getUserType() != null) {
			existing.setUserType(user.getUserType());
		}
		return userRepository.save(existing);
	}

}
