package com.zti.pantry_portal;

import com.zti.pantry_portal.model.User;
import com.zti.pantry_portal.repository.PersonRepository;
import com.zti.pantry_portal.repository.UserRepository;
import com.zti.pantry_portal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testAddUser() {
//        User user = new User();
//        user.setName("testuser");
//        user.setPassword("testpassword");
//
//        // Mock the behavior of UserRepository and PersonRepository
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedpassword");
//        when(userRepository.insert(user)).thenReturn(user);
//        doNothing().when(personRepository).save(any());
//
//        // Call the addUser method
//        userService.addUser(user);
//
//        // Verify that the password was encoded and user was saved
//        verify(passwordEncoder, times(1)).encode(user.getPassword());
//        verify(userRepository, times(1)).insert(user);
//        verify(personRepository, times(1)).save(any());
//    }

    @Test
    public void testGetUserByName() {
        String username = "testuser";
        User user = new User();
        user.setName(username);

        // Mock the behavior of UserRepository
        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Call the getUserByName method
        User retrievedUser = userService.getUserByName(username);

        // Verify that the correct user was retrieved
        assertEquals(username, retrievedUser.getName());
    }

    @Test
    public void testGetUserByName_UserNotFound() {
        String username = "nonexistentuser";

        // Mock the behavior of UserRepository to return an empty Optional
        when(userRepository.findByName(username)).thenReturn(Optional.empty());

        // Call the getUserByName method and expect an exception
        assertThrows(RuntimeException.class, () -> userService.getUserByName(username));
    }

    @Test
    public void testDeleteUser() {
        String userId = "123";

        // Call the deleteUser method
        userService.deleteUser(userId);

        // Verify that the UserRepository's deleteById method was called with the correct ID
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testLoadUserByUsername() {
        String username = "testuser";
        User user = new User();
        user.setName(username);

        // Mock the behavior of UserRepository
        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Call the loadUserByUsername method
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Verify that the correct UserDetails object was returned
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistentuser";

        // Mock the behavior of UserRepository to return an empty Optional
        when(userRepository.findByName(username)).thenReturn(Optional.empty());

        // Call the loadUserByUsername method and expect an exception
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }
}
