package clinicalstudyconnections.repository;

import java.util.List;
import java.util.Optional;

import clinicalstudyconnections.entity.Owner;

public interface OwnerRepository {

	/**
	 * Returns all owners.
	 * @return The users in the system. If none, than an empty list is returned. 
	 */
	List<Owner> all();
	
	/**
	 * Retrieves an owner by its unique Id.
	 * @param ownerId. The unique Id of the owner.
	 * @return The owner if found, otherwise null.
	 */
	Optional<Owner> get(Long ownerId);
}
