package com.ead.coursems.models;


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
@Table(name = "TB_MODULES")
public class ModuleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID moduleId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;

    @JsonProperty(access = WRITE_ONLY)
    @ManyToOne(fetch = LAZY, optional = false)
    private CourseModel course;

    @JsonProperty(access = WRITE_ONLY)
    @OneToMany(mappedBy = "module", fetch = LAZY)
    @Fetch(SUBSELECT)
    private Set<LessonModel> lessons;
}
