package com.example.authgate.campus.dto;

import lombok.*;

import java.util.Set;

@Getter
@Builder
public class LoginResponse {

    private String username;
    private String email;
    private Set<String> roles;
}
