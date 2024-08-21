package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateCategoryDto", description = "Parameters required to create or update a category")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateCategoryDto {

    @ApiModelProperty(notes = "Name of the category", example = "Mathematics", required = true)
    @NotBlank(message = "Category name is required")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    private String categoryName;

    @ApiModelProperty(notes = "Description of the category", example = "This category includes all mathematics related courses.", required = true)
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
}
