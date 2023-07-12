package my.edu.utem.ftmk.dad.project.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.project.model.Attendance;
import my.edu.utem.ftmk.dad.project.model.Examination;
import my.edu.utem.ftmk.dad.project.model.Student;

@Controller
public class AttendanceMenuController {
//test
	@GetMapping("/attendance/list")
	public String getAttendance(Model model){
		
		String uri = "http://localhost:8080/attendanceapp/api/attendances";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Attendance[]> response = restTemplate.getForEntity(uri, Attendance[].class);
		
		Attendance attendances[] = response.getBody();
		
		List<Attendance> attendancelist = Arrays.asList(attendances);
		
		model.addAttribute("attendances", attendancelist);
		//System.out.println(attendancelist.get(1).getAttendtime() + " - " + attendancelist.get(1).getAttendancestatus());
		return "attendancemenu";
	}
	
	@GetMapping("/attendance/matriccardreader")
	public String displayrMatricCardReader(Model model){
		
		model.addAttribute("examinations", getExaminations());
		
		return "matriccardreader";
	}
	
	@GetMapping("/attendance/fingerprintreader")
	public String displayFingerprintReader(Model model){
		
		model.addAttribute("examinations", getExaminations());
		
		return "fingerprintreader";
	}
	
	private List<Examination> getExaminations(){
		String uri = "http://localhost:8080/attendanceapp/api/examinations";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Examination[]> response = restTemplate.getForEntity(uri, Examination[].class);
		
		Examination examinations[] = response.getBody();
		
		return Arrays.asList(examinations);
	}
	
	@RequestMapping("/attendance/save/matricno/{matricno}/{exam}")
	public String getStudentFromMatricNo(@RequestParam String matricno, @RequestParam String exam, Model model) {
		String studentUri = "http://localhost:8080/attendanceapp/api/students/matricNo/" + matricno;
		String examUri = "http://localhost:8080/attendanceapp/api/examinations/" + exam;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student> studentResponse = restTemplate.getForEntity(studentUri, Student.class);
		
		if (!studentResponse.hasBody()) {
			System.out.println("No such student.");
			return "redirect:/attendance/matriccardreader";
		}
		
		ResponseEntity<Examination> examResponse = restTemplate.getForEntity(examUri, Examination.class);
		Examination examination = examResponse.getBody();
		Student student = studentResponse.getBody();
		Attendance attendance = saveAttendance(student, examination, "matric card reader");
		
		model.addAttribute("attendance", attendance); 
		model.addAttribute("examtime", attendance.getExamination().getExamtime().toString());
		model.addAttribute("attendtime", attendance.getAttendtime().toString());
		model.addAttribute("attendancestatus", attendance.getAttendancestatus());
		
		return "recordedattendance";
	}
	
	@RequestMapping("/attendance/save/fingerprint/{fingerprint}/{exam}")
	public String getStudentFromFingerprint(@RequestParam String fingerprint, @RequestParam String exam, Model model) {
		String studentUri = "http://localhost:8080/attendanceapp/api/students/fingerprint/" + fingerprint;
		String examUri = "http://localhost:8080/attendanceapp/api/examinations/" + exam;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student> studentResponse = restTemplate.getForEntity(studentUri, Student.class);
		
		if (!studentResponse.hasBody()) {
			System.out.println("No such student.");
			return "redirect:/attendance/fingerprintreader";
		}
		
		ResponseEntity<Examination> examResponse = restTemplate.getForEntity(examUri, Examination.class);
		Examination examination = examResponse.getBody();
		Student student = studentResponse.getBody();
		Attendance attendance = saveAttendance(student, examination, "fingerprint scanner");
		
		model.addAttribute("attendance", attendance); 
		model.addAttribute("examtime", attendance.getExamination().getExamtime().toString());
		model.addAttribute("attendtime", attendance.getAttendtime().toString());
		model.addAttribute("attendancestatus", attendance.getAttendancestatus());
		
		return "recordedattendance";
	}
	
	public Attendance saveAttendance(Student student, Examination examination, String inputType) {
		
		String attendanceUri = "http://localhost:8080/attendanceapp/api/attendances";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Attendance attendance = new Attendance();
		attendance.setExamination(examination);
		attendance.setStudent(student);
		attendance.setAttendanceInputType(inputType);
		
		ResponseEntity<Attendance> attendanceResponse = 
				restTemplate.postForEntity(attendanceUri, attendance, Attendance.class);
		attendance = attendanceResponse.getBody();
		attendance.setAttendancestatus(validateAttendance(examination, attendance));
		restTemplate.put(attendanceUri, attendance, Attendance.class);
		
		System.out.println(attendance.getAttendanceInputType());
		System.out.println(attendanceResponse);

		return attendance;
	}
	
	private String validateAttendance(Examination exam, Attendance attendance) {
		String attendanceStatus = "On-Time";

		long timeDiff =attendance.getAttendtime().getTime() - exam.getExamtime().getTime();
		long lateTime = 10 * 60 * 1000;
		System.out.println(attendance.getAttendtime().getTime() +"|" + attendance.getAttendtime() 
		+ " - " + exam.getExamtime().getTime() + "|" + exam.getExamtime());
		System.out.println(timeDiff + " " + lateTime);
		if (timeDiff > lateTime) {
			attendanceStatus = "Late";
		}
				
		return attendanceStatus;
	}
	
}
