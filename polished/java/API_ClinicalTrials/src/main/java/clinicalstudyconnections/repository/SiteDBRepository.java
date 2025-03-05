package clinicalstudyconnections.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clinicalstudyconnections.entity.Site;

public interface SiteDBRepository extends JpaRepository<Site, Long> {

	List<Site> findSitesByOwner_OwnerId(Long ownerId);
	
}
