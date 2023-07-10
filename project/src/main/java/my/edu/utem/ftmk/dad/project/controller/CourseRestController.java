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

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	@GetMapping("{courseId}")
	public Course getCourse(@PathVariable long courseId){
		return courseRepository.findById(courseId).get();
	}
	
	@PostMapping
	public Course insertCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@PutMapping
	public Course putCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@DeleteMapping("{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable long courseId){
		courseRepository.deleteById(courseId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
