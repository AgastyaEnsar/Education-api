package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import java.sql.Date;

@Entity(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {

    @Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
 
	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;
 
	@Column(name = "enrollment_date", nullable = false)
	private Date enrollmentDate;
 
    @Column(name = "disabled", nullable = false)
	private boolean disabled = false;
    
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "zipcode", nullable = false, length = 10)
    private String zipcode;

}
