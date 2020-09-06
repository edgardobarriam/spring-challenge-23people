package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dao.CourseDAO;
import io.github.edgardobarriam.springgcpchallenge.dao.StudentDAO;
import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.dto.mapper.StudentDTOMapper;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.model.Student;
import io.github.edgardobarriam.springgcpchallenge.util.RUTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
 
  private final CourseDAO courseDAO;
  private final StudentDAO studentDAO;
  private final StudentDTOMapper studentDTOMapper;
  
  @Value("${pagination-size:10}")
  private int paginationSize;
  
  public StudentServiceImpl(StudentDAO studentDAO, StudentDTOMapper studentDTOMapper, CourseDAO courseDAO) {
    this.studentDAO = studentDAO;
    this.studentDTOMapper = studentDTOMapper;
    this.courseDAO = courseDAO;
  }
  
  @Override
  public List<StudentDTO> getStudentsPaginated(int pageNumber) {
    List<Student> studentsOnPage = studentDAO.findAll(PageRequest.of(pageNumber, paginationSize)).getContent();
    return studentsOnPage.stream().map(studentDTOMapper::toDTO).collect(Collectors.toList());
  }
  
  @Override
  public void saveNewStudent(StudentDTO studentDTO) throws BodyNotValidException {
    
    validateStudent(studentDTO);
    
    Student student = new Student(
      RUTUtils.cleanRut(studentDTO.getRut()),
      studentDTO.getName(),
      studentDTO.getLastName(),
      studentDTO.getAge()
    );
    
    studentDAO.save(student);
  }
  
  @Override
  public StudentDTO getStudent(int id) throws NoSuchElementException {
    Student student = studentDAO.findById(id).orElseThrow();
    return studentDTOMapper.toDTO(student);
  }
  
  @Override
  public void editStudent(StudentDTO studentDTO, int id) throws BodyNotValidException {
    validateStudent(studentDTO);
    
    studentDAO.findById(id).map(student -> {
      if (RUTUtils.isValid(studentDTO.getRut())) student.setRut(RUTUtils.cleanRut(studentDTO.getRut()));
      if (studentDTO.getName() != null) student.setName(studentDTO.getName());
      if (studentDTO.getLastName() != null) student.setLastName(studentDTO.getLastName());
      if (studentDTO.getAge() != null) student.setAge(studentDTO.getAge());
      
      if (studentDTO.getCourse() == null || studentDTO.getCourse().getId() == null) {
        student.setCourse(null);
      } else {
        student.setCourse(courseDAO.findById(studentDTO.getCourse().getId()).orElseThrow());
      }
      
      return studentDAO.save(student);
    }).orElseThrow();
  }
  
  @Override
  public void deleteStudent(int id) throws NoSuchElementException {
    Student studentToBeDeleted = studentDAO.findById(id).orElseThrow();
    studentDAO.delete(studentToBeDeleted);
  }
  
  private void validateStudent(StudentDTO studentDTO) throws BodyNotValidException {
    if (studentDTO.getAge() < 18) {
      throw new BodyNotValidException("Age must be greater than 18");
    } else if (!RUTUtils.isValid(studentDTO.getRut())) {
      throw new BodyNotValidException("RUT is not valid");
    }
  }
}
