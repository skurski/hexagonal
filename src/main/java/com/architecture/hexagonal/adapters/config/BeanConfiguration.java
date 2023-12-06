package com.architecture.hexagonal.adapters.config;

import com.architecture.hexagonal.application.ports.input.UserAccessInputPort;
import com.architecture.hexagonal.application.ports.output.UserAccessOutputPort;
import com.architecture.hexagonal.application.usecases.UserAccessUseCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BeanConfiguration {

    private final ApplicationContext context;

    BeanConfiguration(final ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public UserAccessUseCase createUserAccessUseCase() {
        return new UserAccessInputPort(context.getBean(UserAccessOutputPort.class));
    }
}
