package co.istad.elearning.api.course.dto;

import co.istad.elearning.api.category.dto.CategoryDto;
import co.istad.elearning.api.instructor.dto.InstructorDto;
import lombok.Builder;

@Builder
public record CourseDto(
        Long id,
        String title,
        String description,
        String thumbnail,
        Boolean isFree,
        CategoryDto category,
        InstructorDto instructor
) {
}
