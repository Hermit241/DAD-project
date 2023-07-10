package my.edu.utem.ftmk.dad.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.project.model.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

}
