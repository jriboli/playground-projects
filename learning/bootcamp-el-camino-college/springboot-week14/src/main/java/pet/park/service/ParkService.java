package pet.park.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.park.controller.model.ContributorData;
import pet.park.controller.model.PetParkData;
import pet.park.dao.AmenityDao;
import pet.park.dao.ContributorDao;
import pet.park.dao.PetParkDao;
import pet.park.entity.Amenity;
import pet.park.entity.Contributor;
import pet.park.entity.PetPark;

@Service
public class ParkService {

	@Autowired
	private ContributorDao contributorDao;

	@Autowired
	private AmenityDao amenityDao;

	@Autowired
	private PetParkDao petParkDao;

	@Transactional(readOnly = false)
	public ContributorData saveContributor(ContributorData contributorData) {
		Long contributorId = contributorData.getContributorId();
		Contributor contributor = findOfCreateContributor(contributorId, contributorData.getContributorEmail());

		setFieldsInContributor(contributor, contributorData);
		return new ContributorData(contributorDao.save(contributor));
	}

	private void setFieldsInContributor(Contributor contributor, ContributorData contributorData) {
		contributor.setContributorEmail(contributorData.getContributorEmail());
		contributor.setContributorName(contributorData.getContributorName());

	}

	private Contributor findOfCreateContributor(Long contributorId, String contributorEmail) {
		Contributor contributor;
		if (Objects.isNull(contributorId)) {
			Optional<Contributor> opContrib = contributorDao.findByContributorEmail(contributorEmail);

			if (opContrib.isPresent()) {
				throw new DuplicateKeyException("Contributor with email " + contributorEmail + " already exists");
			}
			contributor = new Contributor();
		} else {
			contributor = findContributorById(contributorId);
		}

		return contributor;
	}

	private Contributor findContributorById(Long contributorId) {

		return contributorDao.findById(contributorId).orElseThrow(
				() -> new NoSuchElementException("Contributor with ID=" + contributorId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
		List<Contributor> contributors = contributorDao.findAll();
		List<ContributorData> response = new LinkedList<>();

		for (Contributor contributor : contributors) {
			response.add(new ContributorData(contributor));
		}

		return response;

		// Second way to get same data
//		return contributorDao.findAll()
//			.stream()
//			.map(cont -> new ContributorData(cont))
//			.toList();
	}

	@Transactional(readOnly = true)
	public ContributorData retrieveContributorById(Long id) {
		Contributor contributor = findContributorById(id);
		// Dont need this, can just return
		// ContributorData response = new ContributorData(contributor);

		return new ContributorData(contributor);
	}

	// What does this annotation do again - ???
	@Transactional(readOnly = false)
	public void deleteContributor(Long id) {
		Contributor contributor = findContributorById(id);

		contributorDao.deleteById(id);
	}

	@Transactional(readOnly = false)
	public PetParkData savePetPark(Long id, PetParkData petParkData) {
		Contributor contributor = findContributorById(id);

		Set<Amenity> amenities = amenityDao.findAllByAmenityIn(petParkData.getAmenities());

		PetPark petPark = findOrCreatePetPark(petParkData.getPetParkId());
		
		setPetParkFields(petPark, petParkData);
		
		petPark.setContributor(contributor);
		contributor.getPetParks().add(petPark);
		
		for(Amenity amenity : amenities) {
			amenity.getPetParks().add(petPark);
			petPark.getAmenities().add(amenity);
		}
		
		PetPark dbPetPark = petParkDao.save(petPark);
		return new PetParkData(dbPetPark);
	}

	private void setPetParkFields(PetPark petPark, PetParkData petParkData) {
		petPark.setCountry(petParkData.getCountry());
		petPark.setDirections(petParkData.getDirections());
		petPark.setGeoLocation(petParkData.getGeoLocation());
		petPark.setParkName(petParkData.getParkName());
		petPark.setPetParkId(petParkData.getPetParkId());
		petPark.setStateOrProvince(petParkData.getStateOrProvince());		
	}

	private PetPark findOrCreatePetPark(Long petParkId) {
		PetPark petPark;

		if (Objects.isNull(petParkId)) {
			petPark = new PetPark();
		} else {
			petPark = findPetParkById(petParkId);
		}

		return petPark;
	}

	private PetPark findPetParkById(Long petParkId) {
		return petParkDao.findById(petParkId)
				.orElseThrow(() -> new NoSuchElementException("No petPark with ID=" + petParkId + " exists."));
	}

	public List<PetParkData> findPetParksByContributor(Long id) {
		return (List<PetParkData>) findPetParkById(id);
	}

	public PetParkData findPetPark(Long id, Long parkId) {
		PetPark petPark = findPetParkById(parkId);
		
		if(petPark.getContributor().getContributorId() != id) {
			throw new NoSuchElementException("PetPark exists but not for that Contributor");
		}
		
		return new PetParkData(petPark); 
	}

}
