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
	private String studentName;
	
	@Column(name="matricno")
	private String matricNo;
	
	@Column(name="sessiongroup")
	private String sessionGroup;
	
	@Column(name="fingerprintid")
	private String fingerprintId;
	
	@ManyToOne
	@JoinColumn(name="advisor")
	private Staff advisor;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="student")
	@JsonManagedReference
	private Set<StudentCourse> studentCourseSet;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="student")
	private Set<Attendance> attendances;

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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the matricNo
	 */
	public String getMatricNo() {
		return matricNo;
	}

	/**
	 * @param matricNo the matricNo to set
	 */
	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	/**
	 * @return the sessionGroup
	 */
	public String getSessionGroup() {
		return sessionGroup;
	}

	/**
	 * @param sessionGroup the sessionGroup to set
	 */
	public void setSessionGroup(String sessionGroup) {
		this.sessionGroup = sessionGroup;
	}

	/**
	 * @return the fingerprintId
	 */
	public String getFingerprintId() {
		return fingerprintId;
	}

	/**
	 * @param fingerprintId the fingerprintId to set
	 */
	public void setFingerprintId(String fingerprintId) {
		this.fingerprintId = fingerprintId;
	}

	/**
	 * @return the advisor
	 */
	public Staff getAdvisor() {
		Staff staff = advisor;
		staff.setAcademicAdvisees(null);
		return staff;
	}

	/**
	 * @param advisor the advisor to set
	 */
	public void setAdvisor(Staff advisor) {
		this.advisor = advisor;
	}

	/**
	 * @return the studentCourseSet
	 */
	public Set<StudentCourse> getStudentCourseSet() {
		return studentCourseSet;
	}

	/**
	 * @param studentCourseSet the studentCourseSet to set
	 */
	public void setStudentCourseSet(Set<StudentCourse> studentCourseSet) {
		this.studentCourseSet = studentCourseSet;
	}
		
}
