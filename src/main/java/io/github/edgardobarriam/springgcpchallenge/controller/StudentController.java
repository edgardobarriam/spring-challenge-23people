package io.github.edgardobarriam.springgcpchallenge.controller;

import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("students")
public class StudentController {
  
  private final StudentService studentService;
  
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }
  
  @GetMapping
  @ResponseBody
  public ResponseEntity<?> getStudentsPaginated(@RequestHeader(required = false, name = "PageNumber") Integer pageNumber ) {
    try {
      int pageNbr = pageNumber != null ? pageNumber : 0; // 0 = First Page
      return new ResponseEntity<>(studentService.getStudentsPaginated(pageNbr), HttpStatus.OK);
    } catch (NumberFormatException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("{id}")
  @ResponseBody
  public ResponseEntity<?> getStudent(@PathVariable int id) {
    try {
      return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping
  public ResponseEntity<?> saveNewStudent(@RequestBody StudentDTO studentDTO) {
    try {
      studentService.saveNewStudent(studentDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (BodyNotValidException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @PutMapping("{id}")
  public ResponseEntity<?> editStudent(@RequestBody StudentDTO studentDTO, @PathVariable int id) {
    try {
      studentService.editStudent(studentDTO, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (BodyNotValidException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteStudent(@PathVariable int id) {
    try {
      studentService.deleteStudent(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
