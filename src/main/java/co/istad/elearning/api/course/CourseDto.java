package co.istad.elearning.api.course;

import co.istad.elearning.api.category.CategoryDto;
import co.istad.elearning.api.instructor.InstructorDto;
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
