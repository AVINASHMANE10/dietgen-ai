package com.dietgen.ai.user.controller;

import com.dietgen.ai.user.dto.response.UserResponse;
import com.dietgen.ai.user.mapper.UserMapper;
import com.dietgen.ai.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/me")
public class MeController {

    private final UserService userService;

    public MeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponse me(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        var user = userService.getById(userId);
        return UserMapper.toUserResponse(user);
    }
}
