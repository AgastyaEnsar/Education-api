package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateQuestionDto", description = "Parameters required to create/update question")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateQuestionDto {

    @ApiModelProperty(notes = "Question Text", required = true)
    @NotBlank(message = "Question Text is required")
    @Size(max = 255)
    private String questionText;

    @ApiModelProperty(notes = "Question Type", required = true)
    @NotBlank(message = "Question Type is required")
    @Size(max = 50)
    private String questionType;

    @ApiModelProperty(notes = "Marks for the question", required = true)
    @NotNull(message = "Marks are required")
    private Integer marks;

    @ApiModelProperty(notes = "Difficulty Level of the question", required = true)
    @NotBlank(message = "Difficulty Level is required")
    @Size(max = 50)
    private String difficultyLevel;

    @ApiModelProperty(notes = "Category Name of the question", required = true)
    @NotBlank(message = "Category Name is required")
    @Size(max = 50)
    private String categoryName;
}
