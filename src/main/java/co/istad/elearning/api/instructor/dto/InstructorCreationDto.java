package co.istad.elearning.api.instructor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record InstructorCreationDto(
        @NotBlank
        String familyName,
        @NotBlank
        String givenName,
        @NotBlank
        @Size(min = 6, max = 10)
        String nationalIdCard,
        String biography
) {
}
