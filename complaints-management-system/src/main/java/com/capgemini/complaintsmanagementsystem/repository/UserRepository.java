package com.capgemini.complaintsmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaintsmanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository< User,Long> {
	public Long countByUserId();

}
