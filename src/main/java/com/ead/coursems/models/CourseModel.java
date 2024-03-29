package com.ead.coursems.models;

import com.ead.coursems.enums.CourseLevelEnum;
import com.ead.coursems.enums.CourseStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@Data
@JsonInclude(NON_NULL)
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 250)
    private String description;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatusEnum courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevelEnum courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    @JsonProperty(access = WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = LAZY)
    @Fetch(SUBSELECT)
    private Set<ModuleModel> modules;

    @JsonProperty(access = WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = LAZY)
    private Set<CourseUserModel> coursesUsers;

    public CourseUserModel convertToCourseUserModel(UUID userId) {
        return new CourseUserModel(null, this, userId);
    }

}
