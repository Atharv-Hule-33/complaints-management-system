package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.controller.UserController;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new User(1L, "John Doe", "john@example.com", "password123", 
                         "1234567890", "customer");
        user2 = new User(2L, "Jane Smith", "jane@example.com", "password456", 
                         "0987654321", "admin");
        userList = Arrays.asList(user1, user2);
    }

    @Test
    public void getAllUsers_ShouldReturnAllUsers() {
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(2, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getUserName());
        assertEquals("Jane Smith", response.getBody().get(1).getUserName());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void getUserById_ShouldReturnUser() {
        when(userService.getUserById(1L)).thenReturn(user1);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals("John Doe", response.getBody().getUserName());
        assertEquals("john@example.com", response.getBody().getUserEmail());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void getUserById_WhenUserNotFound_ShouldReturnNull() {
        when(userService.getUserById(99L)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(99L);

        assertNull(response.getBody());
        verify(userService, times(1)).getUserById(99L);
    }

    @Test
    public void addUser_ShouldCreateNewUser() {
        User newUser = new User(null, "New User", "new@example.com", "newpass", 
                               "1112223333", "customer");
        User savedUser = new User(3L, "New User", "new@example.com", "newpass", 
                                "1112223333", "customer");

        when(userService.addUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = userController.addUser(newUser);

        assertEquals(3L, response.getBody().getUserId());
        assertEquals("New User", response.getBody().getUserName());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void updateUser_ShouldUpdateExistingUser() {
        User updatedDetails = new User();
        updatedDetails.setUserName("Updated Name");
        updatedDetails.setUserEmail("updated@example.com");
        
        User expectedUser = new User(1L, "Updated Name", "updated@example.com", 
                                   "password123", "1234567890", "customer");

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.updateUser(1L, updatedDetails);

        assertEquals("Updated Name", response.getBody().getUserName());
        assertEquals("updated@example.com", response.getBody().getUserEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    public void patchUser_ShouldPartiallyUpdateUser() {
        User patchData = new User();
        patchData.setUserEmail("patched@example.com");
        
        User patchedUser = new User(1L, "John Doe", "patched@example.com", 
                                  "password123", "1234567890", "customer");

        when(userService.patchUser(eq(1L), any(User.class))).thenReturn(patchedUser);

        ResponseEntity<User> response = userController.patchComplaint(1L, patchData);

        assertEquals("patched@example.com", response.getBody().getUserEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).patchUser(eq(1L), any(User.class));
    }

    @Test
    public void deleteUser_ShouldReturnNoContent() {
    	ResponseEntity<Void> response = userController.deleteUser(1L);

    	assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }
}