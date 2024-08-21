package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.*;
import java.util.Date;

@ApiModel(value = "CreateUpdateTrainerDto", description = "Parameters required to create/update trainer")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateTrainerDto {

    @ApiModelProperty(notes = "Name of the Trainer", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 45, message = "Name cannot exceed 45 characters")
    private String name;

    @ApiModelProperty(notes = "Company of the Trainer", required = true)
    @NotBlank(message = "Company is required")
    @Size(max = 45, message = "Company cannot exceed 45 characters")
    private String company;

    @ApiModelProperty(notes = "Department of the Trainer")
    @Size(max = 45, message = "Department cannot exceed 45 characters")
    private String department;

    @ApiModelProperty(notes = "Specialization of the Trainer")
    @Size(max = 100, message = "Specialization cannot exceed 100 characters")
    private String specialization;

    @ApiModelProperty(notes = "Email of the Trainer")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @ApiModelProperty(notes = "Phone Number of the Trainer")
    @Size(max = 15, message = "Phone Number cannot exceed 15 characters")
    private String phoneNumber;

    @ApiModelProperty(notes = "Address of the Trainer")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @ApiModelProperty(notes = "Date of Birth of the Trainer")
    @Past(message = "Date of Birth should be in the past")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "Gender of the Trainer")
    private String gender;

    @ApiModelProperty(notes = "Experience of the Trainer")
    @Min(value = 0, message = "Experience should be a positive number")
    private Integer experience;

    @ApiModelProperty(notes = "Bio of the Trainer")
    private String bio;

    @ApiModelProperty(notes = "Disabled Status of the Trainer")
    private boolean disabled = false;
}
