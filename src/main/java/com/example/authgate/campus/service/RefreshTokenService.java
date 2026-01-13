package com.example.authgate.campus.service;

import com.example.authgate.campus.entity.RefreshToken;
import com.example.authgate.campus.entity.User;
import com.example.authgate.campus.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user) {
        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(
                        Instant.now().plusMillis(refreshTokenExpiration)
                )
                .user(user)
                .build();

        return refreshTokenRepository.save(token);
    }

}
