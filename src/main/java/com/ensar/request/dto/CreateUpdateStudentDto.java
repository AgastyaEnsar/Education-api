package com.ensar.request.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.sql.Date;

@ApiModel(value = "CreateUpdateStudentDto", description = "Parameters required to create/update student")
@Accessors(chain = true)
@Setter
@Getter
public class CreateUpdateStudentDto {

    @ApiModelProperty(notes = "First Name of the Student", required = true)
    @NotBlank(message = "First Name is required")
    @Size(max = 50, message = "First Name cannot exceed 50 characters")
    private String firstName;

    @ApiModelProperty(notes = "Last Name of the Student", required = true)
    @NotBlank(message = "Last Name is required")
    @Size(max = 50, message = "Last Name cannot exceed 50 characters")
    private String lastName;

    @ApiModelProperty(notes = "Email of the Student", required = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @ApiModelProperty(notes = "Enrollment Date of the Student", required = true)
    @NotNull(message = "Enrollment Date is required")
    private Date enrollmentDate;

//     @ApiModelProperty(notes = "Disabled Status of the Student")
//     private boolean disabled = false;
    
    @ApiModelProperty(notes = "Date of Birth of the Student", required = true)
    @NotNull(message = "Date of Birth is required")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "Phone Number of the Student", required = true)
    @NotBlank(message = "Phone Number is required")
    // @Pattern(regexp = "^$|^(\\+?\\d{1,2}\\s?)?((\\(\\d{3}\\))|\\d{3})[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Student Phone Number should be in a recognized format")
    @Size(max = 15, message = "Phone Number cannot exceed 15 characters")
    private String phoneNumber;

    @ApiModelProperty(notes = "Address of the Student", required = true)
    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @ApiModelProperty(notes = "City of the Student", required = true)
    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @ApiModelProperty(notes = "Zipcode of the Student", required = true)
    @NotBlank(message = "Zipcode is required")
    @Size(max = 10, message = "Zipcode cannot exceed 10 characters")
    private String zipcode;
}
