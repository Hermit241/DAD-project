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

/**
 * Model class for Examination
 * 
 * @author Group 28
 *
 */

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
	private String sessionGroup;
	
	@Column(name="examtime")
	private Time examTime;
	
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
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
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
	 * @return the examTime
	 */
	public Time getExamTime() {
		return examTime;
	}

	/**
	 * @param examTime the examTime to set
	 */
	public void setExamTime(Time examTime) {
		this.examTime = examTime;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the chiefInvigilator
	 */
	public Staff getChiefInvigilator() {
		return chiefInvigilator;
	}

	/**
	 * @param chiefInvigilator the chiefInvigilator to set
	 */
	public void setChiefInvigilator(Staff chiefInvigilator) {
		this.chiefInvigilator = chiefInvigilator;
	}

	/**
	 * @return the invigilator
	 */
	public Staff getInvigilator() {
		return invigilator;
	}

	/**
	 * @param invigilator the invigilator to set
	 */
	public void setInvigilator(Staff invigilator) {
		this.invigilator = invigilator;
	}

	
	
}
