package io.github.edgardobarriam.springgcpchallenge.controller;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
      return new ResponseEntity(HttpStatus.OK);
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping
  public List<CourseDTO> getCourses() {
    return courseService.getAllCourses();
  }
}
