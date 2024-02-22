package co.istad.elearning.api.course;

import co.istad.elearning.api.category.Category;
import co.istad.elearning.api.instructor.Instructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 150)
    @Column(nullable = false)
    private String title;
    private String thumbnail;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Boolean isFree;
    @Column(nullable = false)
    private Boolean isDeleted;

//    @Column(name = "cat_id")
//    private String catId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ins_id", referencedColumnName = "id")
    private Instructor instructor;

}
