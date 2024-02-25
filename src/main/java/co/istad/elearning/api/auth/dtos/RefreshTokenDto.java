package co.istad.elearning.api.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(
        @NotBlank
        String refreshToken
) {
}
