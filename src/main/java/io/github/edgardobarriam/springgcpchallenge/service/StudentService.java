package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;

import java.util.List;

public interface StudentService {
  
  List<StudentDTO> getStudentsPaginated(int pageNumber);
  void saveNewStudent(StudentDTO studentDTO) throws BodyNotValidException;
  StudentDTO getStudent(int id);
  void editStudent(StudentDTO studentDTO);
  void deleteStudent(int id);
}
