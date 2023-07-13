package my.edu.utem.ftmk.dad.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Attendance;

/**
 * Repository interface for managing Attendance entities.
 * @author Group 28
 *
 */

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	/**
     * Retrieves all attendances for a specific examination.
     *
     * @param examinationId the ID of the examination
     * @return the list of attendances for the specified examination
     */
	
	@Query("SELECT a FROM Attendance a Where a.examination.id = ?1")
	List<Attendance> findAllByExaminationId(long examinationId);
}
