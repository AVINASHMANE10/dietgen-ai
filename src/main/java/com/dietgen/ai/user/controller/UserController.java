package com.dietgen.ai.user.controller;

import com.dietgen.ai.user.dto.request.CreateUserRequest;
import com.dietgen.ai.user.dto.response.UserResponse;
import com.dietgen.ai.user.mapper.UserMapper;
import com.dietgen.ai.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        var user = userService.createUser(request);
        return UserMapper.toUserResponse(user);
    }
}
