package com.dietgen.ai.food.dto.response;

import com.dietgen.ai.user.entity.enums.BudgetLevel;
import com.dietgen.ai.user.entity.enums.DietType;

public record FoodResponse(
        Long id,
        String name,
        DietType dietType,
        String state,
        String city,
        Integer caloriesPer100g,
        Double protein,
        Double carbs,
        Double fats,
        BudgetLevel budgetLevel
) {}
