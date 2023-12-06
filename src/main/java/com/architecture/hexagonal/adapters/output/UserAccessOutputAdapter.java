package com.architecture.hexagonal.adapters.output;

import com.architecture.hexagonal.adapters.output.mapper.UserMapper;
import com.architecture.hexagonal.adapters.output.repo.UserRepository;
import com.architecture.hexagonal.application.ports.output.UserAccessOutputPort;
import com.architecture.hexagonal.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccessOutputAdapter implements UserAccessOutputPort {
    private final UserRepository userRepository;

    public UserAccessOutputAdapter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return UserMapper.userDataToDomain(userRepository.findByEmail(email));
    }

    @Override
    public void save(final User user) {
        userRepository.save(UserMapper.userDomainToData(user));

    }
}
