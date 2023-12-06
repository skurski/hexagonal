package com.architecture.hexagonal.application.ports.output;

import com.architecture.hexagonal.domain.User;

import java.util.Optional;

public interface UserAccessOutputPort {
    Optional<User> findByEmail(String email);
    void save(User user);
}
