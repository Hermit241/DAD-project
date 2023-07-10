package my.edu.utem.ftmk.dad.project.model;

import java.sql.Time;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="examinations")
public class Examination {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="venue")
	private String venue;
	@Column(name="sessiongroup")
	private String sessiongroup;
	@Column(name="examtime")
	private Time examtime;
	@OneToOne
	@JoinColumn(name="course")
	private Course course;
	@OneToOne
	@JoinColumn(name="chiefinvigilator")
	private Staff chiefInvigilator;
	@OneToOne
	@JoinColumn(name="invigilator")
	private Staff invigilator;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="examination")
	private Set<Attendance> attendances;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getSessiongroup() {
		return sessiongroup;
	}
	public void setSessiongroup(String sessiongroup) {
		this.sessiongroup = sessiongroup;
	}
	public Time getExamtime() {
		return examtime;
	}
	public void setExamtime(Time examtime) {
		this.examtime = examtime;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Staff getChiefInvigilator() {
		Staff staff = new Staff();
		staff.setId(chiefInvigilator.getId());
		staff.setStaffname(chiefInvigilator.getStaffname());
		return staff;
	}
	public void setChiefInvigilator(Staff chiefInvigilator) {
		this.chiefInvigilator = chiefInvigilator;
	}
	public Staff getInvigilator() {
		Staff staff = new Staff();
		staff.setId(invigilator.getId());
		staff.setStaffname(invigilator.getStaffname());
		return staff;
	}
	public void setInvigilator(Staff invigilator) {
		this.invigilator = invigilator;
	}

}
