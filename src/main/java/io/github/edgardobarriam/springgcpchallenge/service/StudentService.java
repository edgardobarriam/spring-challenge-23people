package io.github.edgardobarriam.springgcpchallenge.service;

import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.exception.BodyNotValidException;

import java.util.List;

public interface StudentService {
  
  List<StudentDTO> getStudentsPaginated(int pageNumber);
  void saveNewStudent(StudentDTO studentDTO) throws BodyNotValidException;
  StudentDTO getStudent(int id);
  void editStudent(StudentDTO studentDTO, int id) throws BodyNotValidException;
  void deleteStudent(int id);
}
