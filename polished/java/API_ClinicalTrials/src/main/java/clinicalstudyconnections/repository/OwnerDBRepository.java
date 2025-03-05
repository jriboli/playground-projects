package clinicalstudyconnections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Owner;

public interface OwnerDBRepository extends JpaRepository<Owner, Long> {

}
