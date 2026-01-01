package com.dietgen.ai.food.mapper;

import com.dietgen.ai.food.dto.response.FoodResponse;
import com.dietgen.ai.food.entity.Food;

public class FoodMapper {

    private FoodMapper() {}

    public static FoodResponse toResponse(Food food) {
        return new FoodResponse(
                food.getId(),
                food.getName(),
                food.getDietType(),
                food.getState(),
                food.getCity(),
                food.getCaloriesPer100g(),
                food.getProtein(),
                food.getCarbs(),
                food.getFats(),
                food.getBudgetLevel()
        );
    }
}
