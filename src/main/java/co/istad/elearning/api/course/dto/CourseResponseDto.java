package co.istad.elearning.api.course.dto;

import co.istad.elearning.api.category.dto.CategoryResponseDto;
import co.istad.elearning.api.instructor.dto.InstructorResponseDto;
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
