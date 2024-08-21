package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;
import java.util.Date;

@ApiModel(value = "CreateUpdateCourseDto", description = "Parameters required to create/update course")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateCourseDto {

    @ApiModelProperty(notes = "Name of the Course", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @ApiModelProperty(notes = "Description of the Course")
    private String description;

    @ApiModelProperty(notes = "Duration of the Course")
    @Size(max = 50, message = "Duration cannot exceed 50 characters")
    private String duration;

    @ApiModelProperty(notes = "Start Date of the Course", required = true)
    @NotNull(message = "Start Date is required")
    @FutureOrPresent(message = "Start Date should be in the present or future")
    private Date startDate;

    @ApiModelProperty(notes = "End Date of the Course", required = true)
    @NotNull(message = "End Date is required")
    @Future(message = "End Date should be in the future")
    private Date endDate;
}
