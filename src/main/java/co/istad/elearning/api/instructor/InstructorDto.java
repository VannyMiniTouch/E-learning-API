package co.istad.elearning.api.instructor;

import lombok.Builder;

@Builder
public record InstructorDto(Integer id,
                            String familyName,
                            String givenName) {
}
