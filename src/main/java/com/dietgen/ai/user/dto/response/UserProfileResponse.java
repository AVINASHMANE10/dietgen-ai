package com.dietgen.ai.user.dto.response;

import com.dietgen.ai.user.entity.enums.*;

import java.time.Instant;

public record UserProfileResponse(
        Long userId,
        Integer age,
        Gender gender,
        Double heightCm,
        Double weightKg,
        GoalType goal,
        ActivityLevel activityLevel,
        DietType dietType,
        BudgetLevel budgetLevel,
        String country,
        String state,
        String city,
        String allergies,
        Instant createdAt
) {}
