package com.example.authgate.campus.repository;

import com.example.authgate.campus.entity.RefreshToken;
import com.example.authgate.campus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
