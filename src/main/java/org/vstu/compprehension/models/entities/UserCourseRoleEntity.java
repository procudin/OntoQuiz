package org.vstu.compprehension.models.entities;

import org.vstu.compprehension.models.entities.EnumData.CourseRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "UserCourseRole")
public class UserCourseRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private CourseRole courseRole;
}
