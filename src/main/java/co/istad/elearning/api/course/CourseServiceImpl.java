package co.istad.elearning.api.course;

import co.istad.elearning.api.category.Category;
import co.istad.elearning.api.category.CategoryRepository;
import co.istad.elearning.api.course.dto.CourseReqUpdateDto;
import co.istad.elearning.api.course.dto.CourseRequestDto;
import co.istad.elearning.api.course.dto.CourseResponseDto;
import co.istad.elearning.api.instructor.Instructor;
import co.istad.elearning.api.instructor.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponseDto> findAllCourses() {
        return courseMapper.toCoursesResDto(courseRepository.findAll());
    }

    @Override
    public void createCourse(CourseRequestDto courseRequestDto) {
        Course course = courseMapper.fromCourseReqDto(courseRequestDto);
        Category category = categoryRepository.findCategoriesById(courseRequestDto.cateId())
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There no Category with provide id, Please check or create new one category...!"
        ));

        Instructor instructor = instructorRepository.findInstructorById(courseRequestDto.insId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "There no Instructor with provide id, Please create new instructor first..!"
                ));
        course.setCategory(category);
        course.setInstructor(instructor);
        courseRepository.save(course);
    }

    @Override
    public CourseResponseDto findCourseByID(Long id) {
        Course course = courseRepository.findCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Invalid Course id"
                ));
        return courseMapper.toCourseResDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseReqUpdateDto courseReqUpdateDto) {
        Course course = courseRepository.findCourseById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Course has not been found"
        ));
        courseMapper.copyFromUpdateDto(course, courseReqUpdateDto);
        courseRepository.save(course);
        return this.findCourseByID(id);
    }

    @Transactional
    @Override
    public void disableCourse(Long id) {
        courseRepository.findCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found"
                ));
        courseRepository.disableCourseById(id);
    }

    public void deleteCourse(Long id){
        courseRepository.findCourseById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Course has not found"
        ));
        courseRepository.deleteById(id);
    }
}
