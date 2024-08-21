package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ApiModel(value = "CreateUpdateSchoolDto", description = "Parameters required to create/update school")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateSchoolDto {

    @ApiModelProperty(notes = "School Name", required = true)
    @NotBlank(message = "School Name is required")
    @Size(max = 30)
    private String name;

    @ApiModelProperty(notes = "City", required = true)
    @NotBlank(message = "City is required")
    @Size(max = 30)
    private String city;

    @ApiModelProperty(notes = "Address", required = true)
    @NotBlank(message = "Address is required")
    @Size(max = 100)
    private String address;

    @ApiModelProperty(notes = "Zip Code", required = true)
    @NotBlank(message = "Zip Code is required")
    @Size(max = 10)
    private String zipcode;

    @ApiModelProperty(notes = "Phone Number", required = true)
    @NotBlank(message = "Phone Number is required")
    @Size(max = 15)
    private String phoneNumber;
}
