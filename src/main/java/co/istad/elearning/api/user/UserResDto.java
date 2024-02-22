package co.istad.elearning.api.user;

import lombok.Builder;

@Builder
public record UserResDto(
        String username,
        String email
) {
}
