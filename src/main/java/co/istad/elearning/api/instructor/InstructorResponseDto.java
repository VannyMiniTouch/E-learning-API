package co.istad.elearning.api.instructor;

import lombok.Builder;

@Builder
public record InstructorResponseDto(
        String familyName,
        String givenName) {
}
