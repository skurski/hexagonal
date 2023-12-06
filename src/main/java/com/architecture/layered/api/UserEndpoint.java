package com.architecture.layered.api;

import com.architecture.layered.api.dto.UserDto;
import com.architecture.layered.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    String register(UserDto userDto) throws Exception {
        return userService.createAccount(userDto);
    }

    @PostMapping("/login")
    String login(UserDto userDto) {
        return userService.login(userDto);
    }
}
