package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Specialty;

public interface SpecialtyDBRepository extends JpaRepository<Specialty, Long> {

}
