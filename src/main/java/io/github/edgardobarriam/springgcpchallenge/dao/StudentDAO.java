package io.github.edgardobarriam.springgcpchallenge.dao;

import io.github.edgardobarriam.springgcpchallenge.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends PagingAndSortingRepository<Student, Integer> {
}
