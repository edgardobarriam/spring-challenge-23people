package io.github.edgardobarriam.springgcpchallenge.dao;

import io.github.edgardobarriam.springgcpchallenge.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
}
