package com.architecture.hexagonal.application.ports.input;

import com.architecture.hexagonal.application.ports.output.UserAccessOutputPort;
import com.architecture.hexagonal.application.usecases.UserAccessUseCase;
import com.architecture.hexagonal.domain.User;

public class UserAccessInputPort implements UserAccessUseCase {
    private final UserAccessOutputPort userAccessOutputPort;

    public UserAccessInputPort(final UserAccessOutputPort userAccessOutputPort) {
        this.userAccessOutputPort = userAccessOutputPort;
    }

    @Override
    public String createAccount(final User user) throws Exception {
        user.isEmailAlreadyUsed(userAccessOutputPort.findByEmail(user.getEmail()));
        userAccessOutputPort.save(user);
        return "User successfully created";
    }

    @Override
    public String login(User user) {
        return user.login(userAccessOutputPort.findByEmail(user.getEmail()));
    }
}
