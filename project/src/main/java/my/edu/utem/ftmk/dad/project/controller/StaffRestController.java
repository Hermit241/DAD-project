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

/**
 * REST controller for managing Staff resources.
 * @author group 28
 *
 */

@RestController
@RequestMapping("/api/staffs")
public class StaffRestController {
	@Autowired
	private StaffRepository staffRepository;
	
	@GetMapping
	public List<Staff> getStaffs(){
		return staffRepository.findAll();
	}
	
	/**
     * Retrieves a staff by ID.
     *
     * @param staffId the ID of the staff
     * @return the staff with the specified ID
     */
	
	@GetMapping("{staffId}")
	public Staff getStaff(@PathVariable long staffId){
		return staffRepository.findById(staffId).get();
	}
	
	 /**
     * Inserts a new staff.
     *
     * @param staff the staff object to be inserted
     * @return the inserted staff object
     */
	
	@PostMapping
	public Staff insertStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}
	
	/**
     * Updates an existing staff.
     *
     * @param staff the staff object to be updated
     * @return the updated staff object
     */
	
	@PutMapping
	public Staff putStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}
	
	/**
     * Deletes a staff by ID.
     *
     * @param staffId the ID of the staff to be deleted
     * @return the HTTP status indicating the result of the deletion operation
     */
	
	@DeleteMapping("{staffId}")
	public ResponseEntity<HttpStatus> deleteStaff(@PathVariable long staffId){
		staffRepository.deleteById(staffId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
