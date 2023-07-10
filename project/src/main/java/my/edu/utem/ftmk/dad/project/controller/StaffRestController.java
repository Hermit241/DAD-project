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

import my.edu.utem.ftmk.dad.project.model.Staff;
import my.edu.utem.ftmk.dad.project.repository.StaffRepository;

@RestController
@RequestMapping("/api/staffs")
public class StaffRestController {
	@Autowired
	private StaffRepository staffRepository;
	
	@GetMapping
	public List<Staff> getStaffs(){
		return staffRepository.findAll();
	}
	
	@GetMapping("{staffId}")
	public Staff getStaff(@PathVariable long staffId){
		return staffRepository.findById(staffId).get();
	}
	
	@PostMapping
	public Staff insertStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}
	
	@PutMapping
	public Staff putStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}
	
	@DeleteMapping("{staffId}")
	public ResponseEntity<HttpStatus> deleteStaff(@PathVariable long staffId){
		staffRepository.deleteById(staffId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
