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

import my.edu.utem.ftmk.dad.project.model.Examination;
import my.edu.utem.ftmk.dad.project.repository.ExaminationRepository;

/**
 * REST controller for managing Examination resources.
 * @author Group 28
 *
 */

@RestController
@RequestMapping("/api/examinations")
public class ExaminationRestController {
	@Autowired
	private ExaminationRepository examinationRepository;
	
	/**
	 * Retrieves all examinations.
	 *
	 * @return the list of all examinations
	 */
	
	@GetMapping
	public List<Examination> getExaminations(){
		return examinationRepository.findAll();
	}
	
	/**
	 * Retrieves an examination by ID.
	 *
	 * @param examId the ID of the examination
	 * @return the examination with the specified ID
	 */
	
	@GetMapping("{examId}")
	public Examination getExamination(@PathVariable long examId){
		return examinationRepository.findById(examId).get();
	}
	
	/**
	 * Inserts a new examination.
	 *
	 * @param examination the examination object to be inserted
	 * @return the inserted examination object
	 */
	
	@PostMapping
	public Examination insertExamination(@RequestBody Examination examination) {
		return examinationRepository.save(examination);
	}
	
	/**
	 * Updates an existing examination.
	 *
	 * @param examination the examination object to be updated
	 * @return the updated examination object
	 */
	
	@PutMapping
	public Examination putExamination(@RequestBody Examination examination) {
		return examinationRepository.save(examination);
	}
	
	/**
	 * Deletes an examination by ID.
	 *
	 * @param examId the ID of the examination to be deleted
	 * @return the HTTP status indicating the result of the deletion operation
	 */
	
	@DeleteMapping("{examId}")
	public ResponseEntity<HttpStatus> deleteExamination(@PathVariable long examId){
		examinationRepository.deleteById(examId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
