package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;

@ApiModel(value = "CreateUpdateLearningPlanDto", description = "Parameters required to create/update learning plan")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateLearningPlanDto {

    @ApiModelProperty(notes = "Learning Plan Title", required = true)
    @NotBlank(message = "Learning Plan Title is required")
    @Size(max = 50)
    private String title;

    @ApiModelProperty(notes = "Learning Plan Description")
    private String description;
}
