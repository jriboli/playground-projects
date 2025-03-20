package pet.park.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.park.controller.model.ContributorData;
import pet.park.controller.model.PetParkData;
import pet.park.service.ParkService;

@RestController
@RequestMapping("/pet_park")
@Slf4j
public class ParkController {
	@Autowired
	private ParkService parkService;
	
	@PostMapping("/contributor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContributorData insertContributor(@RequestBody ContributorData contributorData) {
		log.info("Create contributor {}", contributorData);
		
		return parkService.saveContributor(contributorData);
	}
	
	@GetMapping("/contributor")
	public List<ContributorData> retrieveAllContributors() {
		log.info("Retrieve all contributors called.");
		return parkService.retrieveAllContributors();
	}
	
	// This links it to the TomCat
	@GetMapping("/contributor/{id}")
	public ContributorData retrieveContributorById(@PathVariable Long id) {
		log.info("Retrieve contributor with ID ={}", id);
		return parkService.retrieveContributorById(id);
	}
	
	@PutMapping("/contributor/{id}")
	public ContributorData updateContributor(@PathVariable Long id, @RequestBody ContributorData contributorData) {
		contributorData.setContributorId(id);
		log.info("Updating contributor {}", contributorData);
		return parkService.saveContributor(contributorData);
	}
	
	@DeleteMapping("/contributor")
	public void deleteAllContributors() {
		log.info("Attempting to delete all contributors");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}
	
	@DeleteMapping("/contributor/{id}")
	public Map<String, String> deleteContributor(@PathVariable Long id) {
		log.info("Deleting contributor with ID ={}", id);
		parkService.deleteContributor(id);
		
		return Map.of("message", "Deletion of contributor of ID=" + id + " was successful.");
	}
	
	@PostMapping("/contributor/{id}/park")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetParkData insertPetPark(@PathVariable Long id, @RequestBody PetParkData petParkData) {
		log.info("Creating park {} for contributor with ID={}", petParkData, id);
		
		return parkService.savePetPark(id, petParkData);
	}
	
	@PutMapping("/contributor/{id}/park/{parkId}")
	public PetParkData updatePetPark(@PathVariable Long id, @PathVariable Long parkId, @RequestBody PetParkData petParkData) {
		log.info("Updating petPark {}", petParkData);
		petParkData.setPetParkId(parkId);
		
		return parkService.savePetPark(id, petParkData);
	}
	
	// Still needs some work
	@GetMapping("/contributor/{id}/park")
	public List<PetParkData> getAllPetParksByContributor(@PathVariable Long id) {
		log.info("Getting petParks for Contributor with ID={}", id);
		
		return parkService.findPetParksByContributor(id);
	}
	
	@GetMapping("/contributor/{id}/park/{parkId}")
	public PetParkData getPetParkById(@PathVariable Long id, @PathVariable Long parkId) {
		log.info("Find petPark with ID={} for Contributor with ID={}", parkId, id);
		
		return parkService.findPetPark(id, parkId);
	}
}
