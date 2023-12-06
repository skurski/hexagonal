package com.architecture.hexagonal.adapters.input;

import com.architecture.hexagonal.adapters.input.dto.UserDto;
import com.architecture.hexagonal.application.usecases.UserAccessUseCase;
import com.architecture.hexagonal.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
class UserAccessInputAdapter {
    private final UserAccessUseCase userAccessUseCase;

    public UserAccessInputAdapter(final UserAccessUseCase userAccessUseCase) {
        this.userAccessUseCase = userAccessUseCase;
    }

    @PostMapping("/register")
    String register(UserDto userDto) throws Exception {
        return userAccessUseCase.createAccount(
                User.builder()
                        .email(userDto.email())
                        .password(userDto.password())
                        .build()
        );
    }

    @PostMapping("/login")
    String login(UserDto userDto) {
        return userAccessUseCase.login(
                User.builder()
                        .email(userDto.email())
                        .password(userDto.password())
                        .build()
        );
    }
}

