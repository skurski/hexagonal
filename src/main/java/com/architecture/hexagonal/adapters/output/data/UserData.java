package com.architecture.hexagonal.adapters.output.data;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class UserData {
    private final long id;
    private final String email;
    private final String password;
}
