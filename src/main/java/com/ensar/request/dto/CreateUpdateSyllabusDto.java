package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;
import com.ensar.entity.Course;
import com.ensar.entity.Trainer;

@ApiModel(value = "CreateUpdateSyllabusDto", description = "Parameters required to create/update syllabus")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateSyllabusDto {

    @ApiModelProperty(notes = "Content of the Syllabus", required = true)
    @NotBlank(message = "Content is required")
    private String content;

    @ApiModelProperty(notes = "Resource Link of the Syllabus", required = true)
    @NotBlank(message = "Resource Link is required")
    @Size(max = 255, message = "Resource Link cannot exceed 255 characters")
    private String resourceLink;

    @ApiModelProperty(notes = "Course entity associated with the Syllabus", required = true)
    @NotNull(message = "Course is required")
    private Course course;

    @ApiModelProperty(notes = "Course Name associated with the Syllabus", required = true)
    @NotBlank(message = "Course Name is required")
    @Size(max = 100, message = "Course Name cannot exceed 100 characters")
    private String courseName;

    @ApiModelProperty(notes = "Description of the Syllabus", required = true)
    @NotBlank(message = "Description is required")
    private String description;

    @ApiModelProperty(notes = "Semester of the Syllabus", required = true)
    @NotBlank(message = "Semester is required")
    @Size(max = 20, message = "Semester cannot exceed 20 characters")
    private String semester;

    @ApiModelProperty(notes = "Academic Year of the Syllabus", required = true)
    @NotBlank(message = "Academic Year is required")
    @Size(max = 9, message = "Academic Year cannot exceed 9 characters")
    private String academicYear;

    @ApiModelProperty(notes = "Trainer entity associated with the Syllabus", required = true)
    @NotNull(message = "Trainer is required")
    private Trainer trainer;
}
