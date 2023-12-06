package com.architecture.layered.service;

import com.architecture.layered.api.dto.UserDto;
import com.architecture.layered.model.User;
import com.architecture.layered.model.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService systemUnderTest;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccount_givenEmailAlreadyExists_ExceptionIsThrown() {
        // given
        var userDto  = new UserDto("peter@gmail.com", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> systemUnderTest.createAccount(userDto));
    }

    @Test
    void login_passwordIsCorrect_authenticationPass() throws Exception {
        // given
        var userDto  = new UserDto("peter@gmail.com", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User("peter@gmail.com", "password")));
        // when
        var result = systemUnderTest.login(userDto);
        // then
        Assertions.assertEquals("Authenticated", result);
    }

    @Test
    void login_passwordIsNotCorrect_AuthenticationFail() {
        // given
        var userDto = new UserDto("peter@g.com", "pass");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User("peter@g.com", "pass")));
        // when
        var result = systemUnderTest.login(userDto);
        // then
        Assertions.assertNotEquals("Invalid authentication", result);
    }
}