package com.architecture.hexagonal.application.usecases;

import com.architecture.hexagonal.domain.User;

public interface UserAccessUseCase {
    String createAccount(User user) throws Exception;
    String login(User user);
}
