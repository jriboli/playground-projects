package clinicalstudyconnections.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Specialty;

public interface ClinicalStudyDBRepository extends JpaRepository<ClinicalStudy, Long> {

	List<ClinicalStudy> findBySpecialty(Specialty specialty);
	
	@Query(value = "SELECT * FROM clinical_study.clinical_study WHERE study_status = ?1", nativeQuery = true)
	List<ClinicalStudy> findByStatus(int status);
}
