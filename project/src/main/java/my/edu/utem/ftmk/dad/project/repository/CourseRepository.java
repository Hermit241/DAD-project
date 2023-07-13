package my.edu.utem.ftmk.dad.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Course;

/**
 * 
 * Repository interface for managing Course entities.
 * @author Group 28
 *
 */

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
