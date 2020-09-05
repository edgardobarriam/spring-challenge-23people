package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dao.CourseDAO;
import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.dto.mapper.CourseDTOMapper;
import io.github.edgardobarriam.springgcpchallenge.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
  
  final CourseDTOMapper courseDTOMapper;
  final CourseDAO courseDAO;
  
  public CourseServiceImpl(CourseDTOMapper courseDTOMapper, CourseDAO courseDAO) {
    this.courseDTOMapper = courseDTOMapper;
    this.courseDAO = courseDAO;
  }
  
  @Override
  public boolean saveNewCourse(CourseDTO courseDTO) {
    Course course = courseDTOMapper.toModel(courseDTO);
  
    if (course.getCode().length() > 4) {
      return false;
    }
  
    courseDAO.save(course);
    return true;
  }
  
  @Override
  public List<CourseDTO> getAllCourses() {
    List<Course> allCourses = courseDAO.findAll();
    
    return allCourses.stream().map(modelCourse -> new CourseDTO(
      modelCourse.getId(),
      modelCourse.getName(),
      modelCourse.getCode()
    )).collect(Collectors.toList());
  }
}
