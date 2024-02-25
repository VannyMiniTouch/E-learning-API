package co.istad.elearning.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserCreateDto(
        @NotBlank
        String username,
        @NotBlank
                @Email
        String email,
        @NotBlank
        String password,
        @NotEmpty
        Set<@NotBlank String> roleNames
) {
}
