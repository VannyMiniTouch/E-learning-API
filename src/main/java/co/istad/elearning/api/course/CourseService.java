package co.istad.elearning.api.course;


import java.util.List;

public interface CourseService {

    public List<CourseResponseDto> findAllCourses();
    public CourseResponseDto findCourseByID(Long id);

    public void createCourse(CourseRequestDto courseRequestDto);

    public CourseResponseDto updateCourse(Long id, CourseReqUpdateDto courseReqUpdateDto);

    public void disableCourse(Long id);
    public void deleteCourse(Long id);
}
