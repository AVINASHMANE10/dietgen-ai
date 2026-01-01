package com.dietgen.ai.food.repository;

import com.dietgen.ai.food.entity.Food;
import com.dietgen.ai.user.entity.enums.DietType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {

    Page<Food> findByActiveTrue(Pageable pageable);

    Page<Food> findByStateIgnoreCaseAndActiveTrue(String state, Pageable pageable);

    Page<Food> findByDietTypeAndActiveTrue(DietType dietType, Pageable pageable);

    Page<Food> findByStateIgnoreCaseAndDietTypeAndActiveTrue(String state, DietType dietType, Pageable pageable);
}