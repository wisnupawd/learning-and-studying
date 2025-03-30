package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Wisnu", "Prasojo");
        return ResponseEntity.ok().header("custom-header", "custom-value").body(student);
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Wisnu", "Prasojo"));
        students.add(new Student(2, "Wisnu", "Widianto"));
        return students;
    }

    // Spring Boot REST API with Path Variable
    @GetMapping("student/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students?id=1
    @GetMapping("students/query")
    public Student studentWithRequestParam(@RequestParam("id") int studentId,
                                           @RequestParam("first-name") String firstName,
                                           @RequestParam("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    // Spring boot REST API that handles HTTP POST Request
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        return new Student(student.getId(), student.getFirstName(), student.getLastName());
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
        return new Student(studentId, student.getFirstName(), student.getLastName());
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        return "Student deleted successfully";
    }

}
