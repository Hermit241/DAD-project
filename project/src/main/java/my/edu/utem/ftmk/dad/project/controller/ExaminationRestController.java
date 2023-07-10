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

@RestController
@RequestMapping("/api/examinations")
public class ExaminationRestController {
	@Autowired
	private ExaminationRepository examinationRepository;
	
	@GetMapping
	public List<Examination> getExaminations(){
		return examinationRepository.findAll();
	}
	
	@GetMapping("{examId}")
	public Examination getExamination(@PathVariable long examId){
		return examinationRepository.findById(examId).get();
	}
	
	@PostMapping
	public Examination insertExamination(@RequestBody Examination examination) {
		return examinationRepository.save(examination);
	}
	
	@PutMapping
	public Examination putExamination(@RequestBody Examination examination) {
		return examinationRepository.save(examination);
	}
	
	@DeleteMapping("{examId}")
	public ResponseEntity<HttpStatus> deleteExamination(@PathVariable long examId){
		examinationRepository.deleteById(examId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
