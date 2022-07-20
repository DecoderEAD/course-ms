package com.ead.coursems.dto;

import com.ead.coursems.enums.CourseLevelEnum;
import com.ead.coursems.enums.CourseStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CourseDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String imageUrl;

    @NotNull
    private CourseStatusEnum courseStatus;

    @NotNull
    private UUID userInstructor;

    @NotNull
    private CourseLevelEnum courseLevel;
}
