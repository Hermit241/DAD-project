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

@RestController
@RequestMapping("/api/attendances")
public class AttendanceRestController {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@GetMapping
	public List<Attendance> getAttendances(){
		return attendanceRepository.findAll();
	}
	
	@GetMapping("/examination/{examinationid}")
	public List<Attendance> getAttendance(@PathVariable long examinationid){
		return attendanceRepository.findAllByExaminationId(examinationid);
	}
	
	@PostMapping
	public Attendance insertAttendance(@RequestBody Attendance attendance) {
		Time time = new Time(System.currentTimeMillis());
		attendance.setAttendTime(time);
		return attendanceRepository.save(attendance);
	}
	
	@PutMapping
	public Attendance putAttendance(@RequestBody Attendance attendance) {
		return attendanceRepository.save(attendance);
	}
	
	@DeleteMapping("{attendanceId}")
	public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable long attendanceId){
		attendanceRepository.deleteById(attendanceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
