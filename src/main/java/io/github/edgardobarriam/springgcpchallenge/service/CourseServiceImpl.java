package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dao.CourseDAO;
import io.github.edgardobarriam.springgcpchallenge.dto.CourseDTO;
import io.github.edgardobarriam.springgcpchallenge.dto.mapper.CourseDTOMapper;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
  
    if (courseCodeIsNotValid(course.getCode())) {
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
  
  @Override
  public CourseDTO getCourse(int id) throws NoSuchElementException {
    Course course = courseDAO.findById(id).orElseThrow();
    return courseDTOMapper.toDTO(course);
  }
  
  @Override
  public void editCourse(CourseDTO dto, int id) throws NoSuchElementException, BodyNotValidException {
    
    if (courseCodeIsNotValid(dto.getCode())) {
      throw new BodyNotValidException("Course code length cannot be greater than 4");
    }
    
    courseDAO.findById(id).map(course -> {
      if (dto.getName() != null) course.setName(dto.getName());
      if (dto.getCode() != null) course.setCode(dto.getCode());
      return courseDAO.save(course);
    }).orElseThrow();
  }
  
  @Override
  public void deleteCourse(int id) throws NoSuchElementException{
    Course courseToBeDeleted = courseDAO.findById(id).orElseThrow();
    courseDAO.delete(courseToBeDeleted);
  }
  
  private boolean courseCodeIsNotValid(String courseCode) {
    return  courseCode != null && courseCode.length() > 4;
  }
}
