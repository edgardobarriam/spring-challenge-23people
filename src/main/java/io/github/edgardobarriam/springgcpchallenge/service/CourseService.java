package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;

import java.util.List;

public interface CourseService {
  
  void saveNewCourse(CourseDTO courseDTO) throws BodyNotValidException;
  List<CourseDTO> getCoursesPaginated(int pageNumber);
  CourseDTO getCourse(int id);
  void editCourse(CourseDTO courseDTO, int id) throws BodyNotValidException;
  void deleteCourse(int id);
}
