package io.github.edgardobarriam.springgcpchallenge.dao;

import io.github.edgardobarriam.springgcpchallenge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Integer> {
}
