package com.project.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private int pinCode;

    private String city;

    private String state;

    private String apartmentOrSociety;

    private int apartmentOrSocietyNumber;
}
