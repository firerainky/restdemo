package com.lve2code.restdemo.rest;

import com.lve2code.restdemo.entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define endpoint for "/students" - return a list students
    @RequestMapping("/students")
    public List<Student> sayHello() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));
        return students;
    }
}
