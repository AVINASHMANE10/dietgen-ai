package com.dietgen.ai.food.controller;

import com.dietgen.ai.food.dto.response.FoodResponse;
import com.dietgen.ai.food.service.FoodService;
import com.dietgen.ai.user.entity.enums.BudgetLevel;
import com.dietgen.ai.user.entity.enums.DietType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public Page<FoodResponse> getFoods(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) DietType dietType,
            @RequestParam(required = false) BudgetLevel budgetLevel,
            @RequestParam(required = false) Integer minCalories,
            @RequestParam(required = false) Integer maxCalories,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        if (minCalories != null && maxCalories != null && minCalories > maxCalories) {
            throw new IllegalArgumentException("minCalories cannot be greater than maxCalories");
        }

        int safePage = Math.max(0, page);
        int safeSize = Math.min(Math.max(1, size), 50);
        var pageable = PageRequest.of(safePage, safeSize, Sort.by(Sort.Direction.ASC, "name"));

        return foodService.getFoods(state, city, dietType, budgetLevel, minCalories, maxCalories, q, pageable);
    }
}
