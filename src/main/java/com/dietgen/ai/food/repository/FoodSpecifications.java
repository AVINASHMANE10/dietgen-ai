package com.dietgen.ai.food.repository;

import com.dietgen.ai.food.entity.Food;
import com.dietgen.ai.user.entity.enums.BudgetLevel;
import com.dietgen.ai.user.entity.enums.DietType;
import org.springframework.data.jpa.domain.Specification;

public class FoodSpecifications {

    private FoodSpecifications() {}

    public static Specification<Food> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("active"));
    }

    public static Specification<Food> stateEquals(String state) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("state")), state.trim().toLowerCase());
    }

    public static Specification<Food> cityEquals(String city) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("city")), city.trim().toLowerCase());
    }

    public static Specification<Food> dietTypeEquals(DietType dietType) {
        return (root, query, cb) -> cb.equal(root.get("dietType"), dietType);
    }

    public static Specification<Food> budgetEquals(BudgetLevel budgetLevel) {
        return (root, query, cb) -> cb.equal(root.get("budgetLevel"), budgetLevel);
    }

    public static Specification<Food> caloriesBetween(Integer min, Integer max) {
        return (root, query, cb) -> {
            var path = root.get("caloriesPer100g").as(Integer.class);

            if (min != null && max != null) return cb.between(path, min, max);
            if (min != null) return cb.greaterThanOrEqualTo(path, min);
            if (max != null) return cb.lessThanOrEqualTo(path, max);

            return cb.conjunction();
        };
    }

    public static Specification<Food> nameContains(String q) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + q.trim().toLowerCase() + "%");
    }
}
