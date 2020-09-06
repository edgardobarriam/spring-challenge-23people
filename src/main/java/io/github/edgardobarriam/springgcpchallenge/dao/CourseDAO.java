package io.github.edgardobarriam.springgcpchallenge.dao;

import io.github.edgardobarriam.springgcpchallenge.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends PagingAndSortingRepository<Course, Integer> {
}
