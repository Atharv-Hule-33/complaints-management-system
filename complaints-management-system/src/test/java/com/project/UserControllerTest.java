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


        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user1 = new User("John Doe", "john@example.com", "John@123", "9847563223", "user");
        user2 = new User("Jane Smith", "jane@example.com", "Jane@123", "9868574632", "admin");
        userList = Arrays.asList(user1, user2);
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(user1.getUserId()))
                .andExpect(jsonPath("$[0].userName").value(user1.getUserName()))
                .andExpect(jsonPath("$[1].userId").value(user2.getUserId()));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_ShouldReturnUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user1);

        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user1.getUserId()))
                .andExpect(jsonPath("$.userEmail").value(user1.getUserEmail()))
                .andExpect(jsonPath("$.userName").value(user1.getUserName()));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void getUserById_WhenUserNotFound_ShouldReturnOkWithNull() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/users/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).getUserById(99L);
    }

    @Test
    void addUser_ShouldCreateNewUser() throws Exception {
        User newUser = new User(null, "New User", "new@example.com", "New@123", "9876543210", "user");
        User savedUser = new User(1L, "New User", "new@example.com", "New@123", "9876543210", "user");

        when(userService.addUser(newUser)).thenReturn(savedUser);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(3L))
                .andExpect(jsonPath("$.userName").value("New User"))
                .andExpect(jsonPath("$.userEmail").value("new@example.com"));

        verify(userService, times(1)).addUser(newUser);
    }
//    
    @Test
    void updateUser_ShouldUpdateExistingUser() throws Exception {
        User updatedUser = new User("Updated Name", "updated@example.com", "Updated@123", "8735297454", "user");

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Updated Name"))
                .andExpect(jsonPath("$.userEmail").value("updated@example.com"));

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void patchUser_ShouldPartiallyUpdateUser() throws Exception {
        User patchData = new User();
        patchData.setUserEmail("patched@example.com");
        patchData.setUserPhone("9999999999");
        
        User patchedUser = new User("John Doe", "patched@example.com", "John@123", "9999999999", "user");

        when(userService.patchUser(eq(1L), any(User.class))).thenReturn(patchedUser);

        mockMvc.perform(patch("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(patchData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").value("patched@example.com"))
                .andExpect(jsonPath("$.userPhone").value("9999999999"))
                .andExpect(jsonPath("$.userName").value("John Doe")); 

        verify(userService, times(1)).patchUser(eq(1L), any(User.class));
    }

    @Test
    void deleteUser_ShouldDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

