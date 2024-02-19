package com.lve2code.restdemo.rest;

import com.lve2code.restdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // define the @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" - return a list students
    @RequestMapping("/students")
    public List<Student> sayHello() {
        return students;
    }

    // define endpoint for "/students/{studentId}" - return student at index
    @RequestMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // check the studentId against list size
        if ((studentId >= students.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        StudentErrorResponse resp = new StudentErrorResponse();
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.setMessage(exception.getMessage());
        resp.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
        StudentErrorResponse resp = new StudentErrorResponse();
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.setMessage(exception.getMessage());
        resp.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
