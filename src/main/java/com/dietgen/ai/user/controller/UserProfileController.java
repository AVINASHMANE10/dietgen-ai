package com.dietgen.ai.user.controller;

import com.dietgen.ai.user.dto.request.CreateOrUpdateProfileRequest;
import com.dietgen.ai.user.dto.response.UserProfileResponse;
import com.dietgen.ai.user.mapper.UserMapper;
import com.dietgen.ai.user.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileResponse upsertProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateOrUpdateProfileRequest request
    ) {
        var profile = userProfileService.upsert(userId, request);
        return UserMapper.toUserProfileResponse(profile);
    }
}
