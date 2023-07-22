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

import my.edu.utem.ftmk.dad.project.model.Student;
import my.edu.utem.ftmk.dad.project.repository.StudentRepository;

/**
 * REST controller for managing Student resources.
 * @author Group 28
 *
 */

@RestController
@RequestMapping("api/students")
public class StudentRestController {
	@Autowired
	private StudentRepository studentRepository;
	
	 /**
     * Retrieves all students.
     *
     * @return the list of all students
     */
	
	@GetMapping
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	 /**
     * Retrieves a student by matriculation number.
     *
     * @param matricno the matriculation number of the student
     * @return the student with the specified matriculation number
     */
	
	@GetMapping("/matricNo/{matricno}")
	public Student getStudentByMatricNo(@PathVariable String matricno){
		return studentRepository.findByMatricNo(matricno);
	}
	
	 /**
     * Retrieves a student by fingerprint.
     *
     * @param fingerprint the fingerprint of the student
     * @return the student with the specified fingerprint
     */
	
	@GetMapping("/fingerprint/{fingerprint}")
	public Student getStudentByFingerprint(@PathVariable String fingerprint){
		return studentRepository.findByFingerprint(fingerprint);
	}
	
	 /**
     * Inserts a new student.
     *
     * @param student the student object to be inserted
     * @return the inserted student object
     */
	
	@PostMapping
	public Student insertStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	 /**
     * Updates an existing student.
     *
     * @param student the student object to be updated
     * @return the updated student object
     */
	
	@PutMapping
	public Student putStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	 /**
     * Deletes a student by ID.
     *
     * @param studentId the ID of the student to be deleted
     * @return the HTTP status indicating the result of the deletion operation
     */
	
	@DeleteMapping("{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long studentId){
		studentRepository.deleteById(studentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
