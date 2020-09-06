package io.github.edgardobarriam.springgcpchallenge.dto.mapper;

import io.github.edgardobarriam.springgcpchallenge.dto.StudentDTO;
import io.github.edgardobarriam.springgcpchallenge.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOMapper implements DTOMapper<Student, StudentDTO> {
  
  private final CourseDTOMapper courseDTOMapper;
  
  public StudentDTOMapper(CourseDTOMapper courseDTOMapper) {
    this.courseDTOMapper = courseDTOMapper;
  }
  
  @Override
  public StudentDTO toDTO(Student model) {
    return new StudentDTO (
      model.getId(),
      model.getRut(),
      model.getName(),
      model.getLastName(),
      model.getAge(),
      courseDTOMapper.toDTO(model.getCourse())
    );
  }
  
  @Override
  public Student toModel(StudentDTO dto) {
    return new Student(
      dto.getRut(),
      dto.getName(),
      dto.getLastName(),
      dto.getAge(),
      courseDTOMapper.toModel(dto.getCourse())
    );
  }
}
