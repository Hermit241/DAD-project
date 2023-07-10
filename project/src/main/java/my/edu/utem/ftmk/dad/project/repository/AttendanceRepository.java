package my.edu.utem.ftmk.dad.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query("SELECT a FROM Attendance a Where a.examination.id = ?1")
	List<Attendance> findAllByExaminationId(long examinationId);
}
