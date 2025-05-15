package com.project;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.capgemini.complaintsmanagementsystem.controller.UserController;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.service.UserService;



class UserControllerTest {
	


    @Mock
    private UserService userService;


    private User sampleUser;
    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private List<User> userList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleUser = new User("John Doe", "john@example.com", "password", "1234567890", "ADMIN");
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(sampleUser);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(userService).getAllUsers();
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getUserName());
        verify(userService).getUserById(1L);
    }

    @Test
    void testAddUser() {
    	BindingResult bindingResult = mock(BindingResult.class);
    	when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.addUser(any(User.class))).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.addUser(sampleUser,bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("john@example.com", response.getBody().getUserEmail());
        verify(userService).addUser(any(User.class));
    }

    @Test
    void testUpdateUser() {
    	BindingResult bindingResult = mock(BindingResult.class);
    	when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.updateUser(1L, sampleUser,bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getUserName());
        verify(userService).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testPatchUser() {
        when(userService.patchUser(eq(1L), any(User.class))).thenReturn(sampleUser);
        ResponseEntity<User> response = userController.patchComplaint(1L, sampleUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).patchUser(eq(1L), any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(1L);
    }


    }

  



