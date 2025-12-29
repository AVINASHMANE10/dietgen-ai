package com.dietgen.ai.user.service;

import com.dietgen.ai.user.dto.request.CreateUserRequest;
import com.dietgen.ai.user.entity.User;
import com.dietgen.ai.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // For now: local encoder. Later we’ll make it a @Bean.
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(CreateUserRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .email(req.email().toLowerCase())
                .passwordHash(passwordEncoder.encode(req.password()))
                .active(true)
                .build();

        return userRepository.save(user);
    }
}
