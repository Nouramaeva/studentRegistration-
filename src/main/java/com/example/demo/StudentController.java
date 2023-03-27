package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")

// controller --> service --> repository
// controller (for url, the class that interact with the client)
// service which is the link between the controller and the repository layer
// repository (that talk to the database)

public class StudentController {
    private StudentService studentService;
    @GetMapping
    public List<Student> fetchAllStudents(){

        return studentService.getAllStudents();
    }
}
