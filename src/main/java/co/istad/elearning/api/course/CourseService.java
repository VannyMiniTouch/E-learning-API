package co.istad.elearning.api.course;


import co.istad.elearning.api.course.dto.CourseReqUpdateDto;
import co.istad.elearning.api.course.dto.CourseRequestDto;
import co.istad.elearning.api.course.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    public List<CourseResponseDto> findAllCourses();
    public CourseResponseDto findCourseByID(Long id);

    public void createCourse(CourseRequestDto courseRequestDto);

    public CourseResponseDto updateCourse(Long id, CourseReqUpdateDto courseReqUpdateDto);

    public void disableCourse(Long id);
    public void deleteCourse(Long id);
}
