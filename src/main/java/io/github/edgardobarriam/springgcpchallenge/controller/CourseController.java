package io.github.edgardobarriam.springgcpchallenge.controller;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("courses")
public class CourseController {
  
  final CourseService courseService;
  
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }
  
  @PostMapping
  public ResponseEntity saveNewCourse(@RequestBody CourseDTO inputCourseDTO) {
    boolean saveOk = courseService.saveNewCourse(inputCourseDTO);
    
    if (saveOk) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping
  @ResponseBody
  public List<CourseDTO> getCourses() {
    return courseService.getAllCourses();
  }
  
  @GetMapping("{id}")
  @ResponseBody
  public ResponseEntity getCourse(@PathVariable int id) {
    try {
      return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
