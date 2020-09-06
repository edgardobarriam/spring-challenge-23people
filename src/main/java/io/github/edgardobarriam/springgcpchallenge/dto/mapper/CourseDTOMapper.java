package io.github.edgardobarriam.springgcpchallenge.dto.mapper;

import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseDTOMapper implements DTOMapper<Course, CourseDTO> {
  
  @Override
  public CourseDTO toDTO(Course model) {
    if (model == null) {
      return null;
    } else {
      return new CourseDTO(
        model.getId(),
        model.getName(),
        model.getCode()
      );
    }
  }
  
  @Override
  public Course toModel(CourseDTO dto) {
    return new Course(
      dto.getId(),
      dto.getName(),
      dto.getCode()
    );
  }
}
