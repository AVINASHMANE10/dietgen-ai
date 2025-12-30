package com.dietgen.ai.auth.controller;

import com.dietgen.ai.auth.dto.request.LoginRequest;
import com.dietgen.ai.auth.dto.response.AuthResponse;
import com.dietgen.ai.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        String token = authService.login(req.email(), req.password());
        return AuthResponse.bearer(token);
    }
}
