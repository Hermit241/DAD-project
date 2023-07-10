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

@Entity
@Table(name="staffs")
public class Staff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="staffname")
	private String staffname;
	
	@OneToOne(mappedBy="chiefInvigilator")
	private Examination chiefExamInvigilator;
	@OneToOne(mappedBy="invigilator")
	private Examination examInvigilator;
	
	@OneToMany(mappedBy="advisor")
	private Set<Student> academicAdvisees;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public Set<Student> getAcademicAdvisees() {
		return academicAdvisees;
	}
	public void setAcademicAdvisees(Set<Student> academicAdvisees) {
		this.academicAdvisees = academicAdvisees;
	}
}
