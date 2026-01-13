package com.example.authgate.campus.controller;

import com.example.authgate.campus.dto.LoginRequest;
import com.example.authgate.campus.dto.LoginResponse;
import com.example.authgate.campus.entity.RefreshToken;
import com.example.authgate.campus.entity.User;
import com.example.authgate.campus.repository.RefreshTokenRepository;
import com.example.authgate.campus.repository.UserRepository;
import com.example.authgate.campus.service.RefreshTokenService;
import com.example.authgate.campus.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken =
                jwtUtil.generateAccessToken(user.getUsername());

        String refreshToken =
                refreshTokenService.createRefreshToken(user).getToken();

        return ResponseEntity.ok(
                Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken
                )
        );
    }



}
