package com.architecture.hexagonal.adapters.output.mapper;

import com.architecture.hexagonal.adapters.output.data.UserData;
import com.architecture.hexagonal.domain.User;

import java.util.Optional;

public class UserMapper {

    public static Optional<User> userDataToDomain(Optional<UserData> userDataOptional) {
        if (userDataOptional.isPresent()) {
            var userData = userDataOptional.get();
            return Optional.of(
                    User.builder()
                    .id(userData.getId())
                    .email(userData.getEmail())
                    .password(userData.getPassword())
                    .build());
        }

        return Optional.empty();
    }

    public static UserData userDomainToData(User user) {
        return UserData.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
