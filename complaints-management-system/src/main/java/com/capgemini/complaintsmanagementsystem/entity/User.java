package com.capgemini.complaintsmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Email
    @Column(name = "user_email")
    private String userEmail;

    @NotBlank
    @Column(name = "user_password")
    private String userPassword;

    @NotBlank
    @Column(name = "user_type")
    private String userType;

    @NotBlank
    @Column(name = "user_phone")
    private String userPhone;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuditLog> auditLogs = new ArrayList<>();


	public User() {
	}


	

	public User( String userName,  String userEmail,  String userPassword,
			 String userType,  String userPhone) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userPhone = userPhone;
	}




	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String username) {
		this.userName = username;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}




	public void setUserType(String userType) {
		this.userType = userType;
	}




	public String getUserPhone() {
		return userPhone;
	}




	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userType=" + userType + ", userPhone=" + userPhone + "]";
	}




	

	


	





	

	
	

}
