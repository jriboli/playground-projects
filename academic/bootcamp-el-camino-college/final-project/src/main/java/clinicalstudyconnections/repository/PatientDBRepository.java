package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Patient;

public interface PatientDBRepository extends JpaRepository<Patient, Long> {

}
