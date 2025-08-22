package com.example.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String zipCode;

}
