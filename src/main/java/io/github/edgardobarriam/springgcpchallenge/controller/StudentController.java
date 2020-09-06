package io.github.edgardobarriam.springgcpchallenge.controller;

import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("students")
public class StudentController {
  
  private final StudentService studentService;
  
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }
  
  @GetMapping
  public ResponseEntity<?> getStudentsPaginated(@RequestBody(required = false) String pageNumber) {
    try {
      int pageNbr = pageNumber != null ? Integer.parseInt(pageNumber) : 0; // 0 = First Page
      return new ResponseEntity<>(studentService.getStudentsPaginated(pageNbr), HttpStatus.OK);
    } catch (NumberFormatException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @PostMapping
  public ResponseEntity<?> saveNewStudent(@RequestBody StudentDTO studentDTO) {
    try {
      studentService.saveNewStudent(studentDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (BodyNotValidException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
