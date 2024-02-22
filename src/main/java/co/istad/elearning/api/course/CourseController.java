package co.istad.elearning.api.course;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseResponseDto> getAllCourse() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponseDto getCourseById(@PathVariable Long id) {
        return courseService.findCourseByID(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
         courseService.createCourse(courseRequestDto);
    }

    @PutMapping("/{id}")
    public CourseResponseDto editCourse(@PathVariable Long id ,@RequestBody CourseReqUpdateDto courseReqUpdateDto) {
        return courseService.updateCourse(id, courseReqUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/disable")
    public void disableCourse(@PathVariable Long id) {
        courseService.disableCourse(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

}
