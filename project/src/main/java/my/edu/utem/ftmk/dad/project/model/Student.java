package my.edu.utem.ftmk.dad.project.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="studentname")
	private String studentname;
	@Column(name="matricno")
	private String matricno;
	@Column(name="sessiongroup")
	private String sessiongroup;
	@Column(name="fingerprintid")
	private String fingerprintid;
	
	@ManyToOne
	@JoinColumn(name="advisor")
	private Staff advisor;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="student")
	@JsonManagedReference
	private Set<StudentCourse> studentCourseSet;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="student")
	private Set<Attendance> attendances;
	
	public Set<StudentCourse> getStudentCourseSet() {
		return studentCourseSet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getMatricno() {
		return matricno;
	}
	public void setMatricno(String matricno) {
		this.matricno = matricno;
	}
	public String getSessiongroup() {
		return sessiongroup;
	}
	public void setSessiongroup(String sessiongroup) {
		this.sessiongroup = sessiongroup;
	}
	public String getFingerprintid() {
		return fingerprintid;
	}
	public void setFingerprintid(String fingerprintid) {
		this.fingerprintid = fingerprintid;
	}
	public Staff getAdvisor() {
		Staff staff = new Staff();
		staff.setId(advisor.getId());
		staff.setStaffname(advisor.getStaffname());
		return staff;
	}
	public void setAdvisor(Staff advisor) {
		this.advisor = advisor;
	}
}
