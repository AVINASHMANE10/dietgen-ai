package com.dietgen.ai.user.dto.response;

import java.time.Instant;

public record UserResponse(
        Long id,
        String email,
        Boolean active,
        Instant createdAt
) {
}
