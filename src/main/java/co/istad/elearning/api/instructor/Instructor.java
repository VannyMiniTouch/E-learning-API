package co.istad.elearning.api.instructor;

import co.istad.elearning.api.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String familyName;
    private String givenName;
    private String profile;
    @Column(length = 30, unique = true, nullable = false)
    private String nationalIdCard;
    @Column(columnDefinition = "TEXT")
    private String biography;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private List<Course> courses;

}
