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

/**
 * Model class for Attendance
 * 
 * @author Group  28
 *
 */

@Entity
@Table(name="attendances")
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="attendtime")
	private Time attendTime;
	
	@Column(name="attendancestatus")
	private String attendanceStatus;
	
	@Column(name="attendanceinputtype")
	private String attendanceInputType;
	
	@ManyToOne
	@JoinColumn(name="examination")
	private Examination examination;
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the attendTime
	 */
	public Time getAttendTime() {
		return attendTime;
	}

	/**
	 * @param attendTime the attendTime to set
	 */
	public void setAttendTime(Time attendTime) {
		this.attendTime = attendTime;
	}

	/**
	 * @return the attendanceStatus
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	/**
	 * @param attendanceStatus the attendanceStatus to set
	 */
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * @return the attendanceInputType
	 */
	public String getAttendanceInputType() {
		return attendanceInputType;
	}

	/**
	 * @param attendanceInputType the attendanceInputType to set
	 */
	public void setAttendanceInputType(String attendanceInputType) {
		this.attendanceInputType = attendanceInputType;
	}

	/**
	 * @return the examination
	 */
	public Examination getExamination() {
		return examination;
	}

	/**
	 * @param examination the examination to set
	 */
	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
