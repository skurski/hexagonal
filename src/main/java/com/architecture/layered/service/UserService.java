package com.architecture.layered.service;

import com.architecture.layered.api.dto.UserDto;
import com.architecture.layered.model.User;
import com.architecture.layered.model.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String createAccount(UserDto userDto) throws Exception {
        isEmailAlreadyUsed(userDto.email());

        var user = new User(userDto.email(), userDto.password());
        userRepository.save(user);

        return "User successfully created";
    }

    private void isEmailAlreadyUsed(final String email) {
        userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email already used"));
    }

    public String login(UserDto userDto) {
        final var user = userRepository.findByEmail(userDto.email())
                .orElseThrow(() -> new IllegalArgumentException("No such email exists"));

        if (isThePasswordValid(user, userDto)) {
            return "Authenticated";
        }

        return "Invalid authentication";
    }

    private boolean isThePasswordValid(final User user, final UserDto userDto) {
        return user.getPassword().equals(userDto.password());
    }
}
