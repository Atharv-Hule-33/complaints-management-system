package com.project;

import com.capgemini.complaintsmanagementsystem.controller.UserController;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserServiceTest {
	
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User sampleUser;

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

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(userService).getAllUsers();
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getUserName());
        verify(userService).getUserById(1L);
    }

//    @Test
//    void testAddUser() {
//        when(userService.addUser(any(User.class))).thenReturn(sampleUser);
//
//        ResponseEntity<User> response = userController.addUser(sampleUser);
//
//        assertEquals(201, response.getStatusCodeValue());
//        assertEquals("john@example.com", response.getBody().getUserEmail());
//        verify(userService).addUser(any(User.class));
//    }

//    @Test
//    void testUpdateUser() {
//        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(sampleUser);
//
//        ResponseEntity<User> response = userController.updateUser(1L, sampleUser);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("John Doe", response.getBody().getUserName());
//        verify(userService).updateUser(eq(1L), any(User.class));
//    }

    @Test
    void testPatchUser() {
        when(userService.patchUser(eq(1L), any(User.class))).thenReturn(sampleUser);

        ResponseEntity<User> response = userController.patchComplaint(1L, sampleUser);

        assertEquals(200, response.getStatusCodeValue());
        verify(userService).patchUser(eq(1L), any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService).deleteUser(1L);
    }

}
