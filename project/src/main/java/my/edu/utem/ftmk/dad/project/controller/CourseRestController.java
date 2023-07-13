package my.edu.utem.ftmk.dad.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.edu.utem.ftmk.dad.project.model.Course;
import my.edu.utem.ftmk.dad.project.repository.CourseRepository;

/**
 * * REST controller for managing Course resources.
 * @author Group 28
 */

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {
	@Autowired
	private CourseRepository courseRepository;
	
	/**
	 * Retrieves all courses.
	 *
	 * @return the list of all courses
	 */
	
	@GetMapping
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	/**
	 * Retrieves a course by ID.
	 *
	 * @param courseId the ID of the course
	 * @return the course with the specified ID
	 */
	
	@GetMapping("{courseId}")
	public Course getCourse(@PathVariable long courseId){
		return courseRepository.findById(courseId).get();
	}
	
	/**
	 * Inserts a new course.
	 *
	 * @param course the course object to be inserted
	 * @return the inserted course object
	 */
	
	@PostMapping
	public Course insertCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	/**
	 * Updates an existing course.
	 *
	 * @param course the course object to be updated
	 * @return the updated course object
	 */
	
	@PutMapping
	public Course putCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	/**
	 * Deletes a course by ID.
	 *
	 * @param courseId the ID of the course to be deleted
	 * @return the HTTP status indicating the result of the deletion operation
	 */
	
	@DeleteMapping("{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable long courseId){
		courseRepository.deleteById(courseId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
