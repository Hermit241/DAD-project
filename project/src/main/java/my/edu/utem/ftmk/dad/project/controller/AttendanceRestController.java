package my.edu.utem.ftmk.dad.project.controller;

import java.sql.Time;
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

import my.edu.utem.ftmk.dad.project.model.Attendance;
import my.edu.utem.ftmk.dad.project.repository.AttendanceRepository;



/**
 * 
 * REST controller for managing Attendance resources.
 * @author Group 28
 *
 */
@RestController
@RequestMapping("/api/attendances")
public class AttendanceRestController {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	/**
	 * Retrieves all attendances.
	 * 
	 * @return the list of the attendance
	 */
	
	@GetMapping
	public List<Attendance> getAttendances(){
		return attendanceRepository.findAll();
	}
	
	/**
	 * Retrieves attendances for specific examination.
	 * 
	 * @param examinationid is the ID of examination
	 * @return List of the attendance of specific examination
	 */
	
	@GetMapping("/examination/{examinationid}")
	public List<Attendance> getAttendance(@PathVariable long examinationid){
		return attendanceRepository.findAllByExaminationId(examinationid);
	}
	
	/**
	 * Inserts a new attendance record.
	 *
	 * @param attendance the attendance object to be inserted
	 * @return the inserted attendance object
	 */
	
	@PostMapping
	public Attendance insertAttendance(@RequestBody Attendance attendance) {
		Time time = new Time(System.currentTimeMillis());
		attendance.setAttendtime(time);
		return attendanceRepository.save(attendance);
	}
	
	/**
	  * Updates an existing attendance record.
	 *
	 * @param attendance the attendance object to be updated
	 * @return the updated attendance object
	 */
	
	@PutMapping
	public Attendance putAttendance(@RequestBody Attendance attendance) {
		return attendanceRepository.save(attendance);
	}
	
	/**
	 * Deletes an attendance record by ID.
	 *
	 * @param attendanceId the ID of the attendance record to be deleted
	 * @return the HTTP status indicating the result of the deletion operation
	 */
	
	@DeleteMapping("{attendanceId}")
	public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable long attendanceId){
		attendanceRepository.deleteById(attendanceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
