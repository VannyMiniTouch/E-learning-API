package co.istad.elearning.api.course;

import co.istad.elearning.api.category.CategoryResponseDto;
import co.istad.elearning.api.instructor.InstructorResponseDto;
import lombok.Builder;

@Builder
public record CourseResponseDto(
        String title,
        String thumbnail,
        String description,
        Boolean isFree,
        Boolean isDeleted,
        CategoryResponseDto category,
        InstructorResponseDto instructor
) {
}
