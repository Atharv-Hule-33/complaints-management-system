package com.capgemini.complaintsmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaintsmanagementsystem.entity.User;


@Repository
public interface UserRepository extends JpaRepository< User,Long> {
	Optional<User> findByUserNameOrUserEmail(String username, String email);
	boolean existsByUserName(String username);

	boolean existsByUserEmail(String userEmail);
}
