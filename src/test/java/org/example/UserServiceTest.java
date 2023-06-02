package org.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.out.println("Starting tests for method with @BeforeEach");
    }

    @AfterEach
    public void tearDown() {
        userService = null;
        System.out.println("Executing clean up operations after each test with @AfterEach");
    }

    @Test
    public void testRegisterUser_Positive() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.registerUser(user)).thenReturn(true);

        boolean result = userService.registerUser(user);

        assertTrue(result);
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testRegisterUser_Negative() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.registerUser(user)).thenReturn(false);

        boolean result = userService.registerUser(user);

        assertFalse(result);
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testRegisterUser_EdgeCase() {
        User existingUser = new User("ExistingUser", "password", "existinguser@example.com");
        User newUser = new User("NewUser", "password", "newuser@example.com");

        List<User> existingUsers = new ArrayList<>();
        existingUsers.add(existingUser);

        when(userService.registerUser(newUser)).thenReturn(true);
        when(userService.registerUser(existingUser)).thenReturn(false);

        boolean resultNewUser = userService.registerUser(newUser);
        boolean resultExistingUser = userService.registerUser(existingUser);

        assertTrue(resultNewUser);
        assertFalse(resultExistingUser);

        verify(userService, times(1)).registerUser(newUser);
        verify(userService, times(1)).registerUser(existingUser);
    }


    @Test
    public void testLoginUser_Positive() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.loginUser("JohnDoe", "password")).thenReturn(user);

        User loggedInUser = userService.loginUser("JohnDoe", "password");

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
        verify(userService, times(1)).loginUser("JohnDoe", "password");
    }

    @Test
    public void testLoginUser_Negative() {
        when(userService.loginUser("JohnDoe", "password")).thenReturn(null);

        User loggedInUser = userService.loginUser("JohnDoe", "password");

        assertNull(loggedInUser);
        verify(userService, times(1)).loginUser("JohnDoe", "password");
    }

    @Test
    public void testLoginUser_EdgeCase() {
        User user = new User("JohnDoe", "wrongpassword", "johndoe@example.com");
        when(userService.loginUser("JohnDoe", "password")).thenReturn(user);
        User loggedInUser = userService.loginUser("JohnDoe", "wrongpassword"); //logging user in
        assertNull(loggedInUser);   //verify logged in user is null
        verify(userService, times(1)).loginUser("JohnDoe", "wrongpassword"); // verify login attempt
    }

    @Test
    public void testUpdateUserProfile_Positive() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.updateUserProfile(any(User.class), anyString(), anyString(), anyString())).thenAnswer(invocation -> {
            User updatedUser = invocation.getArgument(0);
            updatedUser.setUsername(invocation.getArgument(1));
            updatedUser.setPassword(invocation.getArgument(2));
            updatedUser.setEmail(invocation.getArgument(3));
            return true;
        });

        boolean result = userService.updateUserProfile(user, "NewUsername", "NewPassword", "newemail@example.com");

        assertTrue(result);
        assertEquals("NewUsername", user.getUsername());
        assertEquals("NewPassword", user.getPassword());
        assertEquals("newemail@example.com", user.getEmail());
        verify(userService, times(1)).updateUserProfile(user, "NewUsername", "NewPassword", "newemail@example.com");
    }



    @Test
    public void testUpdateUserProfile_Negative() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.updateUserProfile(any(User.class), anyString(), anyString(), anyString())).thenAnswer(invocation -> {
            User updatedUser = invocation.getArgument(0);
            updatedUser.setUsername(invocation.getArgument(1));
            updatedUser.setPassword(invocation.getArgument(2));
            updatedUser.setEmail(invocation.getArgument(3));
            return false;
        });

        boolean result = userService.updateUserProfile(user, "ExistingUsername", "NewPassword", "newemail@example.com");

        assertFalse(result);
        assertEquals("ExistingUsername", user.getUsername());
        assertEquals("NewPassword", user.getPassword());
        assertEquals("newemail@example.com", user.getEmail());
        verify(userService, times(1)).updateUserProfile(user, "ExistingUsername", "NewPassword", "newemail@example.com");
    }


    @Test
    public void testUpdateUserProfile_EdgeCase() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        when(userService.updateUserProfile(eq(user), eq("ExistingUsername"), eq("NewPassword"), eq("newemail@example.com"))).thenReturn(false);

        boolean result = userService.updateUserProfile(user, "ExistingUsername", "NewPassword", "newemail@example.com");

        assertFalse(result);
        assertEquals("JohnDoe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("johndoe@example.com", user.getEmail());
        verify(userService, times(1)).updateUserProfile(user, "ExistingUsername", "NewPassword", "newemail@example.com");
    }



}
