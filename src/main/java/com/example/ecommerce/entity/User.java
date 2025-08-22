package com.example.ecommerce.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("users")
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int phoneNo;

    private String email;

    private boolean isDeleted;

    private Date createdAt;

    private Date updatedAt;

    private List<Address> addresses;

}
