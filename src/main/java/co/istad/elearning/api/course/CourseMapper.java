package co.istad.elearning.api.course;


import co.istad.elearning.api.course.dto.CourseReqUpdateDto;
import co.istad.elearning.api.course.dto.CourseRequestDto;
import co.istad.elearning.api.course.dto.CourseResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    List<CourseResponseDto> toCoursesResDto(List<Course> all);
    Course fromCourseReqDto(CourseRequestDto courseRequestDto);
    CourseResponseDto toCourseResDto(Course course);
    void copyFromUpdateDto(@MappingTarget Course course, CourseReqUpdateDto courseReqUpdateDto);
}
