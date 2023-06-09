package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository<T> extends
        MongoRepository<Student, String>
{

    Optional<Student> findStudentByEmail(String email);

}
