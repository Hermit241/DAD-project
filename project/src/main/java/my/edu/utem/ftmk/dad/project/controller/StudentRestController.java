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

@RestController
@RequestMapping("api/students")
public class StudentRestController {
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/matricNo/{matricno}")
	public Student getStudentByAdvisor(@PathVariable String matricno){
		return studentRepository.findByMatricNo(matricno);
	}
	
	@GetMapping("/fingerprint/{fingerprint}")
	public Student getStudentByFingerprint(@PathVariable String fingerprint){
		return studentRepository.findByFingerprint(fingerprint);
	}
	
	@PostMapping
	public Student insertStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PutMapping
	public Student putStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@DeleteMapping("{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long studentId){
		studentRepository.deleteById(studentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
