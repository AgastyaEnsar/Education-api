package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@ApiModel(value = "CreateUpdateProjectDto", description = "Parameters required to create/update project")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateProjectDto {

    @ApiModelProperty(notes = "Project Name", required = true)
    @NotBlank(message = "Project Name is required")
    @Size(max = 100, message = "Project Name cannot exceed 100 characters")
    private String name;

    @ApiModelProperty(notes = "Project Description")
    @Size(max = 5000, message = "Project Description cannot exceed 5000 characters")
    private String description;

    @ApiModelProperty(notes = "Project Start Date", required = true)
    @NotNull(message = "Start Date is required")
    private Date startDate;

    @ApiModelProperty(notes = "Project End Date")
    private Date endDate;

    @ApiModelProperty(notes = "Project Manager ID", required = true)
    @NotBlank(message = "Manager ID is required")
    private String managerId;
}
