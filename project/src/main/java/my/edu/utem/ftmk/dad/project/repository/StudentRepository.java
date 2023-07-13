package my.edu.utem.ftmk.dad.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * 
	 * @param matricno
	 * @return
	 */
	@Query("SELECT s FROM Student s WHERE s.matricNo = ?1")
	Student findByMatricNo(String matricno);
	
	@Query("SELECT s FROM Student s WHERE s.fingerprintId = ?1")
	Student findByFingerprint(String fingerprintid);
}
