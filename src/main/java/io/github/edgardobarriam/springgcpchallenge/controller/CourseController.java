package io.github.edgardobarriam.springgcpchallenge.controller;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("courses")
public class CourseController {
  
  final CourseService courseService;
  
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }
  
  @PostMapping
  public ResponseEntity<?> saveNewCourse(@RequestBody CourseDTO inputCourseDTO) {
    
    try {
      courseService.saveNewCourse(inputCourseDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (BodyNotValidException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
  }
  
  @GetMapping
  @ResponseBody
  public ResponseEntity<?> getCourses(@RequestBody(required = false) String pageNumber) {
    try {
      int pageNbr = pageNumber != null ? Integer.parseInt(pageNumber) : 0; // 0 = First Page
      return new ResponseEntity<>(courseService.getCoursesPaginated(pageNbr), HttpStatus.OK);
    } catch (NumberFormatException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("{id}")
  @ResponseBody
  public ResponseEntity<?> getCourse(@PathVariable int id) {
    try {
      return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PutMapping("{id}")
  public ResponseEntity<?> editCourse(@RequestBody CourseDTO courseDTO, @PathVariable int id) {
    try {
      courseService.editCourse(courseDTO, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (BodyNotValidException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteCourse(@PathVariable int id) {
    try {
      courseService.deleteCourse(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
