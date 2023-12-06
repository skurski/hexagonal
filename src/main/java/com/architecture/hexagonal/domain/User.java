package com.architecture.hexagonal.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
// Instead of setters better to use
// specific methods for use cases
@Builder
@NoArgsConstructor
public class User {
    private long id;
    private String email;
    private String password;

    public User(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public User(final long id, final String email, final String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public void isEmailAlreadyUsed(Optional<User> optionalUser) throws Exception {
        if (optionalUser.isPresent()) {
            throw new Exception("Email address already exist");
        }
    }

    public String login(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var isThePasswordValid = isThePasswordValid(user);
            if (isThePasswordValid) {
                return "Authenticated with success";
            } else {
                return "Invalid credentials";
            }
        } else {
            return "Invalid credentials";
        }
    }

    private boolean isThePasswordValid(User user) {
        return user.getPassword().equals(this.password);
    }
}
