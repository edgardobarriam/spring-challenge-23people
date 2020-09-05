package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;

import java.util.List;

public interface CourseService {
  
  boolean saveNewCourse(CourseDTO courseDTO);
  List<CourseDTO> getAllCourses();
  CourseDTO getCourse(int id);
}
