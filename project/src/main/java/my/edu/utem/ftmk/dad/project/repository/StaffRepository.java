package my.edu.utem.ftmk.dad.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Staff;

/**
 * 
 * Repository interface for managing Staff entities.
 * @author Group 28
 *
 */

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
