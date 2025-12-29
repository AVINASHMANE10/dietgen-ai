package com.dietgen.ai.user.dto.request;


import com.dietgen.ai.user.entity.enums.*;
import jakarta.validation.constraints.*;

public record CreateOrUpdateProfileRequest(
        @NotNull(message = "age is required")
        @Min(value = 10, message = "age must be >= 10")
        @Max(value = 100, message = "age must be <= 100")
        Integer age,

        @NotNull(message = "gender is required")
        Gender gender,

        @NotNull(message = "heightCm is required")
        @Min(value = 80, message = "heightCm must be >= 80")
        @Max(value = 250, message = "heightCm must be <= 250")
        Double heightCm,

        @NotNull(message = "weightKg is required")
        @Min(value = 20, message = "weightKg must be >= 20")
        @Max(value = 300, message = "weightKg must be <= 300")
        Double weightKg,

        @NotNull(message = "goal is required")
        GoalType goal,

        @NotNull(message = "activityLevel is required")
        ActivityLevel activityLevel,

        @NotNull(message = "dietType is required")
        DietType dietType,

        @NotNull(message = "budgetLevel is required")
        BudgetLevel budgetLevel,

        @NotBlank(message = "country is required")
        @Size(max = 100, message = "country max length is 100")
        String country,


        @NotBlank(message = "state is required")
        @Size(max = 100, message = "state max length is 100")
        String state,

        @NotBlank(message = "city is required")
        @Size(max = 100, message = "city max length is 100")
        String city,

        @Size(max = 500, message = "allergies max length is 500")
        String allergies
) {}