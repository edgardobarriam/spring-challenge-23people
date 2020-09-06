package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dao.StudentDAO;
import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.dto.mapper.StudentDTOMapper;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;
import io.github.edgardobarriam.springgcpchallenge.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
 
  private final StudentDAO studentDAO;
  private final StudentDTOMapper studentDTOMapper;
  
  @Value("${pagination-size:10}")
  private int paginationSize;
  
  public StudentServiceImpl(StudentDAO studentDAO, StudentDTOMapper studentDTOMapper) {
    this.studentDAO = studentDAO;
    this.studentDTOMapper = studentDTOMapper;
  }
  
  @Override
  public List<StudentDTO> getStudentsPaginated(int pageNumber) {
    List<Student> studentsOnPage = studentDAO.findAll(PageRequest.of(pageNumber, paginationSize)).getContent();
    return studentsOnPage.stream().map(studentDTOMapper::toDTO).collect(Collectors.toList());
  }
  
  @Override
  public void saveNewStudent(StudentDTO studentDTO) throws BodyNotValidException {
    
    if (studentDTO.getAge() < 18) {
      throw new BodyNotValidException("Age must be greater than 18");
    }
    
    //TODO: VALIDAR RUT
    
    Student student = new Student(
      studentDTO.getRut(),
      studentDTO.getName(),
      studentDTO.getLastName(),
      studentDTO.getAge()
    );
    
    studentDAO.save(student);
  }
  
  @Override
  public StudentDTO getStudent(int id) {
    return null;
  }
  
  @Override
  public void editStudent(StudentDTO studentDTO) {
  
  }
  
  @Override
  public void deleteStudent(int id) {
  
  }
}
