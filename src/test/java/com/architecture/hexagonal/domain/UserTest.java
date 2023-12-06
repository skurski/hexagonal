package com.architecture.hexagonal.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private static final String PASSWORD = "password";
    private static final String MAIL = "Peter@gmail.com";
    private static final String WRONG_PASSWORD = "wrongPassword";

    @Test
    void createAccount_EmailAlreadyExists_ExceptionIsThrown() {
        // given
        var testUser = new User(MAIL, PASSWORD);
        // when
        var optionalUser = Optional.of(testUser);
        // then
        Assertions.assertThrows(Exception.class, () -> testUser.isEmailAlreadyUsed(optionalUser));
    }

    @Test
    void login_passwordIsCorrect_authenticationPass() {
        // given
        var user = new User(MAIL, PASSWORD);
        var optionalUser = Optional.of(user);
        // when
        var loginResult = user.login(optionalUser);
        // then
        Assertions.assertEquals(loginResult, "Authenticated with success");
    }

    @Test
    void login_passwordIsNotCorrect_authenticationFail() {
        // given
        var user = new User(MAIL, PASSWORD);
        var optionalUser = Optional.of(new User(MAIL, WRONG_PASSWORD));
        // when
        var loginResult = user.login(optionalUser);
        // then
        Assertions.assertEquals(loginResult, "Invalid credentials");
    }
}