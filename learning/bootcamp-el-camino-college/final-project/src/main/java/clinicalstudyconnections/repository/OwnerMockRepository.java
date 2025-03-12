package clinicalstudyconnections.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import clinicalstudyconnections.entity.Owner;

@Repository
public class OwnerMockRepository implements OwnerRepository {

	List<Owner> owners = new LinkedList<>();
	
	public OwnerMockRepository() {
		owners.add(new Owner((long) 1, "Rocket", "Raccoon", "The Guardians", null));
		owners.add(new Owner((long) 2, "Peter", "Quill", "The Guardians", null));
	}
	
	@Override
	public List<Owner> all() {
		return owners;
	}

	@Override
	public Optional<Owner> get(Long ownerId) {
		return Optional.of(owners.stream()
				.filter((ownerData) -> ownerData.getOwnerId().equals(ownerId))
				.findFirst()
				.orElse(null));
	}

}
