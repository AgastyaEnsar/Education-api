package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;

@ApiModel(value = "CreateUpdateStatusDto", description = "Parameters required to create/update status")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateStatusDto {

    @ApiModelProperty(notes = "Name of the Status", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @ApiModelProperty(notes = "Description of the Status", required = true)
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
}
