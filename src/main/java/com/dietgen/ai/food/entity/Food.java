package com.dietgen.ai.food.entity;

import com.dietgen.ai.user.entity.enums.BudgetLevel;
import com.dietgen.ai.user.entity.enums.DietType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "foods",
        indexes = {
                @Index(name = "idx_food_state", columnList = "state"),
                @Index(name = "idx_food_diet_type", columnList = "dietType")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DietType dietType; // VEG, NON_VEG, EGG

    @Column(nullable = false, length = 60)
    private String state; // Maharashtra, Gujarat

    @Column(length = 60)
    private String city; // Optional: Pune, Nagpur

    @Column(nullable = false)
    private Integer caloriesPer100g;

    private Double protein; // g per 100g
    private Double carbs;   // g per 100g
    private Double fats;    // g per 100g

    @Enumerated(EnumType.STRING)
    private BudgetLevel budgetLevel; // LOW, MEDIUM, HIGH

    @Column(nullable = false)
    private Boolean active = true;
}
