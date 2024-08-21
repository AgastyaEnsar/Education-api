package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateUpdateTopicDto", description = "Parameters required to create/update a Topic")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateTopicDto {

    @ApiModelProperty(notes = "Topic Name", required = true)
    @NotBlank(message = "Topic Name is required")
    @Size(max = 100)
    private String name;

    @ApiModelProperty(notes = "Topic Category", required = true)
    @NotBlank(message = "Topic Category is required")
    @Size(max = 100)
    private String category;

    @ApiModelProperty(notes = "Topic Level", required = true)
    @NotBlank(message = "Topic Level is required")
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Invalid Topic Level. Allowed values are HIGH, MEDIUM, LOW")
    private String level;

    @ApiModelProperty(notes = "Topic ID", required = true)
    @NotBlank(message = "Topic ID is required")
    //@Pattern(regexp = Constants.UUID_PATTERN, message = "Invalid Topic ID")
    private String topicId;

}
