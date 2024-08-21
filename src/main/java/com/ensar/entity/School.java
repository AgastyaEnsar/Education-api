package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "school")
@Data
@EqualsAndHashCode(callSuper = true)
public class School extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
