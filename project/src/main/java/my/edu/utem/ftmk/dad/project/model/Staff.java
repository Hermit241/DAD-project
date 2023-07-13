package my.edu.utem.ftmk.dad.project.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


/**
 * Model class for Staff
 * 
 * @author Group 28
 *
 */
@Entity
@Table(name="staffs")
public class Staff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="staffname")
	private String staffName;
	
	@OneToOne(mappedBy="chiefInvigilator")
	private Examination chiefExamInvigilator;
	
	@OneToOne(mappedBy="invigilator")
	private Examination examInvigilator;
	
	@OneToMany(mappedBy="advisor")
	private Set<Student> academicAdvisees;

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
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the academicAdvisees
	 */
	public Set<Student> getAcademicAdvisees() {
		return academicAdvisees;
	}

	/**
	 * @param academicAdvisees the academicAdvisees to set
	 */
	public void setAcademicAdvisees(Set<Student> academicAdvisees) {
		this.academicAdvisees = academicAdvisees;
	}
	
	
	
}
