package my.edu.utem.ftmk.dad.project.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.project.model.Attendance;
import my.edu.utem.ftmk.dad.project.model.Examination;
import my.edu.utem.ftmk.dad.project.model.Student;

@Controller
public class AttendanceMenuController {

	@GetMapping("/attendance/list")
	public String getAttendance(Model model){
		
		String uri = "http://localhost:8080/attendanceapp/api/attendances";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Attendance[]> response =
				restTemplate.getForEntity(uri, Attendance[].class);
		
		Attendance attendances[] = response.getBody();
		
		List<Attendance> attendancelist = Arrays.asList(attendances);
		
		model.addAttribute("attendances", attendancelist);
		//System.out.println(attendancelist.get(1).getAttendtime() + " - " + attendancelist.get(1).getAttendancestatus());
		return "attendancemenu";
	}
	
	@GetMapping("/attendance/matriccardreader")
	public String displayrMatricCardReader(Model model){
		
		Attendance attendance = new Attendance();
		attendance.setAttendanceInputType("matric card reader");
		
		model.addAttribute("examinations", getExaminations());
		model.addAttribute("attendance", attendance);
		
		return "matriccardreader";
	}
	
	@GetMapping("/attendance/fingerprintreader")
	public String displayFingerprintReader(Model model){

		Attendance attendance = new Attendance();
		attendance.setAttendanceInputType("fingerprint reader");
		
		model.addAttribute("examinations", getExaminations());
		model.addAttribute("attendance", attendance);
		
		return "fingerprintreader";
	}
	
	private List<Examination> getExaminations(){
		String uri = "http://localhost:8080/attendanceapp/api/examinations";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Examination[]> response =
				restTemplate.getForEntity(uri, Examination[].class);
		
		Examination examinations[] = response.getBody();
		
		return Arrays.asList(examinations);
	}
	
	/*
	 * A relic of bad code
	 * 
	 * 
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
	*/
	
	/**
	 * 
	 * Processes the Post request from the frontend to save the attendance of the 
	 * student.
	 * 
	 * @param attendance ModelAttribute of the attendance Object
	 * @param model Model object to send to the "recorded attendance" view
	 * @return 
	 * If the provided matricno or fingerprint id does not belong to any student
	 * in the database, it will redirect back to the last html view.
	 * Otherwise, it will redirect to the "recorded attendance" view.
	 * 
	 */
	
	@PostMapping("/attendance/save")
	public String saveAttendance(@ModelAttribute("attendance") Attendance attendance, Model model) {
		
		String attendanceUri = "http://localhost:8080/attendanceapp/api/attendances";
		String examUri = "http://localhost:8080/attendanceapp/api/examinations/"
		+ attendance.getExamination().getId();
		String studentUri = "";
		String returnPath = "";
		
		if (attendance.getAttendanceInputType().
				compareTo("matric card reader") == 0) {
			studentUri = "http://localhost:8080/attendanceapp/api/students/matricNo/" 
				+ attendance.getStudent().getMatricNo();
			
			returnPath = "redirect:/attendance/matriccardreader";
		}
		else if (attendance.getAttendanceInputType().
				compareTo("fingerprint reader") == 0) {
			studentUri = "http://localhost:8080/attendanceapp/api/students/fingerprint/" 
					+ attendance.getStudent().getFingerprintId();
			returnPath = "redirect:/attendance/fingerprintreader";
		}
			
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Student> studentResponse =
				restTemplate.getForEntity(studentUri, Student.class);
		
		if (!studentResponse.hasBody()) {
			System.out.println("No such student.");
			return returnPath;
		}
		

		ResponseEntity<Examination> examResponse = 
				restTemplate.getForEntity(examUri, Examination.class);
		
		Student student = studentResponse.getBody();
		Examination examination = examResponse.getBody();
		attendance.setStudent(student);
		attendance.setExamination(examination);
		
		ResponseEntity<Attendance> attendanceResponse = 
				restTemplate.postForEntity(
						attendanceUri, attendance, Attendance.class);
		attendance = attendanceResponse.getBody();
		attendance.setAttendanceStatus(
				validateAttendance(attendance.getExamination(), attendance));
		restTemplate.put(attendanceUri, attendance, Attendance.class);
		
		System.out.println(attendance.getAttendanceInputType());
		System.out.println(attendanceResponse);

		model.addAttribute("attendance", attendance); 

		return "recordedattendance";
	}
	
	private String validateAttendance(Examination exam, Attendance attendance) {
		String attendanceStatus = "On-Time";

		long timeDiff = attendance.getAttendTime().getTime() 
				- exam.getExamTime().getTime();
		long lateTime = 10 * 60 * 1000;
		
		/* 
		 * Debugging print command, can be ignored
		 * 
		System.out.println(attendance.getAttendtime().getTime() +"|" + attendance.getAttendtime() 
		+ " - " + exam.getExamtime().getTime() + "|" + exam.getExamtime());
		System.out.println(timeDiff + " " + lateTime);
		*/
		
		if (timeDiff > lateTime) {
			attendanceStatus = "Late";
		}
				
		return attendanceStatus;
	}
	
}
