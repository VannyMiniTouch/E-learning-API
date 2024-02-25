package co.istad.elearning.api.user.dto;

import lombok.Builder;

@Builder
public record UserResDto(
        String username,
        String email
) {
}
