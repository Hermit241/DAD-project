package my.edu.utem.ftmk.dad.project.model;

import java.sql.Time;

/**
 * This classs
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="attendances")
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	
	private int id;
	@Column(name="attendtime")
	private Time attendtime;
	// private Time attendTime
	
	@Column(name="attendancestatus")
	private String attendancestatus;
	
	@Column(name="attendanceinputtype")
	private String attendanceInputType;
	
	@ManyToOne
	@JoinColumn(name="examination")
	private Examination examination;
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getAttendtime() {
		return attendtime;
	}
	public void setAttendtime(Time attendtime) {
		this.attendtime = attendtime;
	}
	public Examination getExamination() {
		return examination;
	}
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getAttendancestatus() {
		return attendancestatus;
	}
	public void setAttendancestatus(String attendancestatus) {
		this.attendancestatus = attendancestatus;
	}
	public String getAttendanceInputType() {
		return attendanceInputType;
	}
	public void setAttendanceInputType(String attendanceInputType) {
		this.attendanceInputType = attendanceInputType;
	}
	
}
