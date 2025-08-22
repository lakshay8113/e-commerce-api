package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ isDeleted: false }")
    List<User> findAllUsers();

    @Query("{ id: ?0, isDeleted: false}")
    Optional<User> findUserById(String id);

}
