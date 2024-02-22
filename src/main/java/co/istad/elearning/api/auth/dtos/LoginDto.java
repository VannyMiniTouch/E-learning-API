package co.istad.elearning.api.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
