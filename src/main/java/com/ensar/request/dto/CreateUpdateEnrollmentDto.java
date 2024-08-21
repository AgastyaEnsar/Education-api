package com.ensar.request.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ApiModel(value = "CreateUpdateEnrollmentDto", description = "Parameters required to create/update enrollment")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateEnrollmentDto {

    @ApiModelProperty(notes = "Enrollment Name", required = true)
    @NotBlank(message = "Enrollment Name is required")
    @Size(max = 50)
    private String enrollmentName;

    @ApiModelProperty(notes = "Remarks", required = true)
    @NotBlank(message = "Remarks is required")
    @Size(max = 50)
    private String remarks;

    @ApiModelProperty(notes = "Enrollment Date", required = true)
    @NotNull(message = "Enrollment Date is required")
    private Date enrollmentDate;

    @ApiModelProperty(notes = "Last Modified Date")
    @NotNull(message = "Last Modified Date is required")
    private Date lastModifiedDate;

    @ApiModelProperty(notes = "Status", required = true)
    @NotNull(message = "Status is required")
    private EnrollmentStatus status;

    public enum EnrollmentStatus {
        enrolled,
        withdrawn,
        completed
    }
}
