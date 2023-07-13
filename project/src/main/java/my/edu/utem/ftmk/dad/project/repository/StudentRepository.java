package my.edu.utem.ftmk.dad.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Student;
/**
 * 
 * Repository interface for managing Student entities.
 * @author Group 28
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


	/**
	 * Retrieves a student by matriculation number.
	 *
	 * @param matricno the matriculation number of the student
	 * @return the student with the specified matriculation number
	 */
	@Query("SELECT s FROM Student s WHERE s.matricNo = ?1")
	Student findByMatricNo(String matricno);
	
<<<<<<< HEAD
	/**
	 * Retrieves a student by fingerprint ID.
	 *
	 * @param fingerprintid the fingerprint ID of the student
	 * @return the student with the specified fingerprint ID
	 */
	
	@Query("SELECT s FROM Student s WHERE s.fingerprintid = ?1")
=======
	@Query("SELECT s FROM Student s WHERE s.fingerprintId = ?1")
>>>>>>> branch 'master' of https://github.com/Hermit241/DAD-project.git
	Student findByFingerprint(String fingerprintid);
}
