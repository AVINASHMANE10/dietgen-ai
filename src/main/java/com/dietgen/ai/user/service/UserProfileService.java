package com.dietgen.ai.user.service;

import com.dietgen.ai.common.exception.ResourceNotFoundException;
import com.dietgen.ai.user.dto.request.CreateOrUpdateProfileRequest;
import com.dietgen.ai.user.entity.User;
import com.dietgen.ai.user.entity.UserProfile;
import com.dietgen.ai.user.repository.UserProfileRepository;
import com.dietgen.ai.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserProfile upsert(Long userId, CreateOrUpdateProfileRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->  new ResourceNotFoundException("User not found: id=" + userId));

        UserProfile profile = userProfileRepository.findById(userId)
                .orElse(UserProfile.builder().user(user).build());

        profile.setAge(req.age());
        profile.setGender(req.gender());
        profile.setHeightCm(req.heightCm());
        profile.setWeightKg(req.weightKg());
        profile.setGoal(req.goal());
        profile.setActivityLevel(req.activityLevel());
        profile.setCountry(req.country());
        profile.setState(req.state());
        profile.setCity(req.city());
        profile.setDietType(req.dietType());
        profile.setBudgetLevel(req.budgetLevel());
        profile.setAllergies(req.allergies());

        return userProfileRepository.save(profile);
    }
}
