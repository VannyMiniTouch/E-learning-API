package co.istad.elearning.api.instructor;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record InstructorEditionDto(
        String familyName,
        String givenName,
        String biography
) {
}
