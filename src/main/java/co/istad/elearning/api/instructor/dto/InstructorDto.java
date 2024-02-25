package co.istad.elearning.api.instructor.dto;

import lombok.Builder;

@Builder
public record InstructorDto(Integer id,
                            String familyName,
                            String givenName) {
}
