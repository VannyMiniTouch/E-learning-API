package co.istad.elearning.api.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseById(Long id);

    @Modifying
    @Query("""
                    UPDATE Course c SET c.isDeleted = true WHERE c.id = :id
            """)
    void disableCourseById(Long id);
}
