package com.dietgen.ai.user.entity;

import com.dietgen.ai.user.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    private Long userId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_profiles_user"))
    private User user;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private Gender gender;

    @Column(nullable = false)
    private Double heightCm;

    @Column(nullable = false)
    private Double weightKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private GoalType goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActivityLevel activityLevel;

    @Column(nullable = false, length = 60)
    private String country;

    @Column(nullable = false, length = 60)
    private String state;

    @Column(nullable = false, length = 60)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DietType dietType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BudgetLevel budgetLevel;

    @Column(length = 500)
    private String allergies; // comma-separated for MVP

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
