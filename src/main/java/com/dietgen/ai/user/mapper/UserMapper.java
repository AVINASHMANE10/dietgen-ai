package com.dietgen.ai.user.mapper;

import com.dietgen.ai.user.dto.response.UserProfileResponse;
import com.dietgen.ai.user.dto.response.UserResponse;
import com.dietgen.ai.user.entity.User;
import com.dietgen.ai.user.entity.UserProfile;

public final class UserMapper {

    private UserMapper() {}

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.isActive(),
                user.getCreatedAt()
        );
    }

    public static UserProfileResponse toUserProfileResponse(UserProfile profile) {
        return new UserProfileResponse(
                profile.getUser().getId(),
                profile.getAge(),
                profile.getGender(),
                profile.getHeightCm(),
                profile.getWeightKg(),
                profile.getGoal(),
                profile.getActivityLevel(),
                profile.getDietType(),
                profile.getBudgetLevel(),
                profile.getCountry(),
                profile.getState(),
                profile.getCity(),
                profile.getAllergies(),
                profile.getCreatedAt()
        );
    }
}
