package co.istad.elearning.api.instructor.dto;

import lombok.Builder;

@Builder
public record InstructorResponseDto(
        String familyName,
        String givenName) {
}
