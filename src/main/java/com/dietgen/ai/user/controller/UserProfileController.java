package com.dietgen.ai.user.controller;

import com.dietgen.ai.user.dto.request.CreateOrUpdateProfileRequest;
import com.dietgen.ai.user.dto.response.UserProfileResponse;
import com.dietgen.ai.user.mapper.UserMapper;
import com.dietgen.ai.user.service.UserProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/users/{userId}/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "profile controller alive";
    }

    @GetMapping
    public UserProfileResponse getProfile(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        Long authUserId = (Long) authentication.getPrincipal();
        if (!userId.equals(authUserId)) {
            throw new AccessDeniedException("You cannot view another user's profile");
        }

        var profile = userProfileService.getByUserId(userId);
        return UserMapper.toUserProfileResponse(profile);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileResponse upsertProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateOrUpdateProfileRequest request,
            Authentication authentication
    ) {
        Long authUserId = (Long) authentication.getPrincipal();
        if (!userId.equals(authUserId)) {
            throw new AccessDeniedException("You cannot modify another user's profile");
        }

        var profile = userProfileService.upsert(userId, request);
        return UserMapper.toUserProfileResponse(profile);
    }
}
