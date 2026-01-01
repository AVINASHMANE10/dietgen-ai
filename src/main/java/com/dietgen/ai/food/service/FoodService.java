package com.dietgen.ai.food.service;

import com.dietgen.ai.food.dto.response.FoodResponse;
import com.dietgen.ai.food.entity.Food;
import com.dietgen.ai.food.mapper.FoodMapper;
import com.dietgen.ai.food.repository.FoodRepository;
import com.dietgen.ai.food.repository.FoodSpecifications;
import com.dietgen.ai.user.entity.enums.BudgetLevel;
import com.dietgen.ai.user.entity.enums.DietType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

//    public Page<FoodResponse> getFoods(String state, DietType dietType, Pageable pageable) {
//
//        boolean hasState = state != null && !state.trim().isBlank();
//        boolean hasDietType = dietType != null;
//
//        if (hasState && hasDietType) {
//            return foodRepository
//                    .findByStateIgnoreCaseAndDietTypeAndActiveTrue(state.trim(), dietType, pageable)
//                    .map(FoodMapper::toResponse);
//        }
//
//        if (hasState) {
//            return foodRepository
//                    .findByStateIgnoreCaseAndActiveTrue(state.trim(), pageable)
//                    .map(FoodMapper::toResponse);
//        }
//
//        if (hasDietType) {
//            return foodRepository
//                    .findByDietTypeAndActiveTrue(dietType, pageable)
//                    .map(FoodMapper::toResponse);
//        }
//
//        return foodRepository
//                .findByActiveTrue(pageable)
//                .map(FoodMapper::toResponse);
//    }
public Page<FoodResponse> getFoods(
        String state,
        String city,
        DietType dietType,
        BudgetLevel budgetLevel,
        Integer minCalories,
        Integer maxCalories,
        String q,
        Pageable pageable
) {
    Specification<Food> spec = FoodSpecifications.isActive();

    if (state != null && !state.isBlank()) spec = spec.and(FoodSpecifications.stateEquals(state));
    if (city != null && !city.isBlank()) spec = spec.and(FoodSpecifications.cityEquals(city));
    if (dietType != null) spec = spec.and(FoodSpecifications.dietTypeEquals(dietType));
    if (budgetLevel != null) spec = spec.and(FoodSpecifications.budgetEquals(budgetLevel));
    if (minCalories != null || maxCalories != null) spec = spec.and(FoodSpecifications.caloriesBetween(minCalories, maxCalories));
    if (q != null && !q.isBlank()) spec = spec.and(FoodSpecifications.nameContains(q));

    return foodRepository.findAll(spec, pageable).map(FoodMapper::toResponse);
}
}
