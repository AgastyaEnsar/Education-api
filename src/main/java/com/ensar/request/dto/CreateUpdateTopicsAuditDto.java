package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;

@ApiModel(value = "CreateUpdateTopicsAuditDto", description = "Parameters required to create/update topics audit")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateTopicsAuditDto {

    @ApiModelProperty(notes = "User ID", required = true)
    @NotBlank(message = "User ID is required")
    private String userId;

    @ApiModelProperty(notes = "Topic ID", required = true)
    @NotBlank(message = "Topic ID is required")
    private String topicId;

    @ApiModelProperty(notes = "Audit Result", required = true)
    @NotNull(message = "Result is required")
    private Result result;

    @ApiModelProperty(notes = "Audit Evidence")
    private String evidence;

    public enum Result { HIGH, MEDIUM, BASIC, NA, NO }
}
