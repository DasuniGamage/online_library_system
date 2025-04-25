package com.rootcode.online_library_system.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {
    String generateToken(String email);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
